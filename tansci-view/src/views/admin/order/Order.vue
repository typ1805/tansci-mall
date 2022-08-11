<template>
    <div class="order">
        <el-card>
            <Table :data="tableData" :column="tableTitle" :operation="{show:true, width:160,}" :page="page" :loading="loading"
                @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange">
                <template #search>
                    <div><el-input v-model="searchForm.orderId" placeholder="请输入订单ID"></el-input></div>
                    <div><el-button @click="onRefresh" icon="RefreshRight" circle></el-button></div>
                    <div><el-button @click="onSearch" type="primary" icon="Search">查询</el-button></div>
                </template>
                <template #column="scope">
                    <el-button @click="onEdit(scope)" type="text" style="color:var(--edit)">编辑</el-button>
                    <el-button @click="onDelete(scope)" type="text" style="color:var(--delete)">删除</el-button>
                </template>
            </Table>
        </el-card>
        <el-dialog v-model="orderStatusVisible" title="更新订单状态" width="30%" :show-close="false">
            <el-form :model="orderStatusForm" :rules="rules" ref="orderStatusFormRef" label-position="right" label-width="100px">
                <el-form-item label="订单状态" prop="orderStatus" :rules="[{required: true, message: '请选择订单状态', trigger: 'change'}]">
                    <el-select v-model="orderStatusForm.orderStatus" placeholder="请选择订单状态" style="width:100%;">
                        <el-option v-for="item in orderStatusList" :key="item" :label="item.dicLabel" :value="item.dicValue"/>
                    </el-select>
                </el-form-item>
            </el-form>
            <template #footer>
                <el-button @click="orderStatusVisible = false">取消</el-button>
                <el-button type="primary" @click="onSubmit(orderStatusFormRef)">确 定</el-button>
            </template>
        </el-dialog>
    </div>
</template>
<script setup>
    import {onMounted, reactive, ref, unref, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from "element-plus"
    import Table from '@/components/common/Table.vue'
    import {orderPage, updateOrder, delOrder} from '@/api/admin/order'
    import {getGroupNameByList} from '@/api/admin/dic'

    const orderStatusFormRef = ref(null)
    const state = reactive({
        searchForm:{
            orderId: null,
        },
        loading: false,
        page: {
            current: 1,
            size: 10,
            total: 1,
        },
        tableTitle: [
            {prop:'orderId',label:'订单ID'},
            {prop:'orderNo',label:'订单号'},
            {prop:'price',label:'订单金额',type:'price'},
            {prop:'discount',label:'优惠金额',type:'price'},
            {prop:'payStatus',alias:'payStatusName',label:'支付状态',type:'tag',option:{type:'info',size:'small',effect:'plain'}},
            {prop:'orderStatus',alias:'orderStatusName',label:'订单状态',type:'tag',option:{type:'info',size:'small',effect:'plain'}},
            {prop:'payType',alias:'payTypeName',label:'支付类型',type:'tag',option:{type:'warning',size:'small',effect:'plain'}},
            {prop:'payTime',label:'支付时间'},
            {prop:'userName',label:'商户名称'},
            {prop:'updateTime',label:'更新时间'},
            {prop:'createTime',label:'创建时间'},
            {prop:'remarks',label:'备注'}
        ],
        tableData:[],
        orderStatusVisible: false,
        orderStatusForm:{
            orderId: null,
            orderStatus: null,
        },
        orderStatusList:[],
    })

    const {
        searchForm,loading,page,tableTitle,tableData,orderStatusVisible,orderStatusList,orderStatusForm
    } = toRefs(state)

    onMounted(() => {
        onOrderPage();
    })

    const onOrderPage = () =>{
        state.loading = true;
        orderPage(Object.assign(state.page,state.searchForm)).then(res=>{
            state.loading = false;
            state.tableData = res.result.records;
            state.page.current = res.result.current;
            state.page.size = res.result.size;
            state.page.total = res.result.total;
        })
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onOrderPage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onOrderPage();
    }
    const onRefresh = () =>{
        state.searchForm = {
            orderId: null,
        }
        onOrderPage();
    }
    const onSearch = () =>{
        onOrderPage();
    }

    const onOrderStatusList = () =>{
        getGroupNameByList({groupName:'order_status'}).then(res=>{
            state.orderStatusList = res.result;
        })
    }

    // 编辑状态
    const onEdit = (val) =>{
        onOrderStatusList();
        state.orderStatusForm = {
            orderId: val.column.row.orderId,
            orderStatus: val.column.row.orderStatus,
        }
        state.orderStatusVisible = true;
    }

    const onSubmit = async () =>{
        const form = unref(orderStatusFormRef);
        if (!form) return;
        await form.validate();

        updateOrder(state.orderStatusForm).then(res=>{
            if(res){
                ElMessage.success("操作成功！");
                onOrderPage();
                state.orderStatusVisible = false;
            }
        })
    }

    // 删除
    const onDelete = (val) =>{
        ElMessageBox.confirm('此操作将永久删除, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            delOrder({id: val.column.row.id}).then(res=>{
                if(res){
                    ElMessage.success('删除成功!');
                    onOrderPage();
                }
            })
        }).catch(e=>{
            console.log(e)
        })
    }
</script>
<style lang="scss" scoped>
</style>