<template>
    <div class="goods-order">
        <el-card>
            <Table :data="tableData" :column="tableTitle" :operation="{show:true, width: 160,}" :page="page" :loading="loading"
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
        <el-dialog v-model="statusVisible" title="更新订单状态" width="30%" :show-close="false">
            <el-form :model="statusForm" :rules="rules" ref="statusFormRef" label-position="right" label-width="100px">
                <el-form-item label="订单状态" prop="status" :rules="[{required: true, message: '请选择订单状态', trigger: 'change'}]">
                    <el-select v-model="statusForm.status" placeholder="请选择订单状态" style="width:100%;">
                        <el-option v-for="item in statusList" :key="item" :label="item.dicLabel" :value="item.dicValue"/>
                    </el-select>
                </el-form-item>
            </el-form>
            <template #footer>
                <el-button @click="statusVisible = false">取消</el-button>
                <el-button type="primary" @click="onSubmit(statusFormRef)">确 定</el-button>
            </template>
        </el-dialog>
    </div>
</template>
<script setup>
    import {onMounted, reactive, ref, unref, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from "element-plus"
    import Table from '@/components/common/Table.vue'
    import {goodsOrderPage, updateGoodsOrder, delGoodsOrder} from "@/api/admin/goodsOrder"
    import {getGroupNameByList} from '@/api/admin/dic'

    const statusFormRef = ref(null)
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
            {prop:'goodsOrderId',label:'商品订单号'},
            {prop:'orderId',label:'订单号'},
            {prop:'goodsNum',label:'商品数量'},
            {prop:'status',alias:'statusName',label:'订单状态',type:'tag',option:{size:'small',effect:'plain',typeList:[
                    {value:0,label:'primary'},
                    {value:1,label:'success'},
                    {value:2,label:'danger'},
                    {value:3,label:'warning'},
                ]}},
            {prop:'payTime',label:'支付时间'},
            {prop:'price',label:'订单金额',type:'price'},
            {prop:'userName',label:'商户名称'},
            {prop:'updateTime',label:'更新时间'},
            {prop:'createTime',label:'创建时间'},
        ],
        tableData:[],
        statusVisible: false,
        statusForm:{
            goodsOrderId: null,
            status: null,
        },
        statusList:[],
    })

    const {
        searchForm,loading,page,tableTitle,tableData,statusVisible,statusForm,statusList
    } = toRefs(state)

    onMounted(() => {
        onGoodsOrderPage();
    })

    const onGoodsOrderPage = () =>{
        state.loading = true;
        goodsOrderPage(Object.assign(state.page,state.searchForm)).then(res=>{
            state.loading = false;
            state.tableData = res.result.records;
            state.page.current = res.result.current;
            state.page.size = res.result.size;
            state.page.total = res.result.total;
        })
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onGoodsOrderPage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onGoodsOrderPage();
    }
    const onRefresh = () =>{
        state.searchForm = {
            orderId: null,
        }
        onGoodsOrderPage();
    }
    const onSearch = () =>{
        onGoodsOrderPage();
    }

    const onStatusList = () =>{
        getGroupNameByList({groupName:'goods_order_status'}).then(res=>{
            state.statusList = res.result;
        })
    }

    // 编辑状态
    const onEdit = (val) =>{
        onStatusList();
        state.statusForm = {
            goodsOrderId: val.column.row.goodsOrderId,
            status: val.column.row.status,
        }
        state.statusVisible = true;
    }

    const onSubmit = async () =>{
        const form = unref(statusFormRef);
        if (!form) return;
        await form.validate();

        updateGoodsOrder(state.statusForm).then(res=>{
            if(res){
                ElMessage.success("操作成功！");
                onGoodsOrderPage();
                state.statusVisible = false;
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
            delGoodsOrder({id: val.column.row.goodsOrderId}).then(res=>{
                if(res){
                    ElMessage.success('删除成功!');
                    onGoodsOrderPage();
                }
            })
        }).catch(e=>{
            console.log(e)
        })
    }
</script>
<style lang="scss" scoped>
</style>