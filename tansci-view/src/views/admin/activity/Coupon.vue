<template>
    <div class="coupon">
        <el-card>
        <Table :data="tableData" :column="tableTitle" :operation="{show:true, width: 160,}" :page="page" :loading="loading"
            @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange">
            <template #search>
                <div><el-button type="primary" @click="onAdd">添加</el-button></div>
                <div><el-input v-model="searchForm.name" placeholder="请输入名称"></el-input></div>
                <div><el-button @click="onRefresh" icon="RefreshRight" circle></el-button></div>
                <div><el-button @click="onSearch" type="primary" icon="Search">查询</el-button></div>
            </template>
            <template #column="scope">
                <el-button @click="onEdit(scope)" type="text" style="color:var(--edit)">编辑</el-button>
                <el-button @click="onDelete(scope)" type="text" style="color:var(--delete)">删除</el-button>
            </template>
        </Table>
        </el-card>
        <el-dialog title="优惠券管理" v-model="addVisible" width="40%" :show-close="false">
            <el-form :model="addForm" :rules="rules" ref="addFormRef" label-position="right" label-width="100px">
                <el-form-item prop="name" label="名称" :rules="[{required: true, message: '请输入名称', trigger: 'blur'}]">
                    <el-input v-model="addForm.name" placeholder="请输入名称" style="width:100%"></el-input>
                </el-form-item>
                <el-form-item prop="type" label="类型" :rules="[{required: true, message: '请选择类型', trigger: 'change'}]">
                    <el-radio-group v-model="addForm.type">
                        <el-radio :label="0">平台券</el-radio>
                        <el-radio :label="1">店铺券</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item prop="price" label="优惠金额" :rules="[{required: true, message: '请输入优惠金额', trigger: 'blur'}]">
                    <el-input-number v-model="addForm.price" :min="0" :max="9999" style="width:100%"></el-input-number>
                </el-form-item>
                <el-form-item prop="norm" label="限额" :rules="[{required: true, message: '请输入限额', trigger: 'blur'}]">
                    <el-input-number v-model="addForm.norm" :min="0" :max="9999" style="width:100%"></el-input-number>
                </el-form-item>
                <el-form-item prop="type" label="店铺" :rules="[{required: true, message: '请选择店铺', trigger: 'change'}]">
                    <el-select v-model="addForm.shopId" filterable remote placeholder="请选择店铺" :remote-method="onShopPage" :loading="goodsLoading" style="width:100%">
                        <el-option v-for="shop in shopList" :key="shop" :label="shop.name" :value="shop.shopId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="有效时间" prop="dates" :rules="[{required: true, message: '请选择有效时间', trigger: 'change'}]">
                    <el-date-picker v-model="addForm.dates" type="datetimerange" range-separator="至" start-placeholder="开始时间" end-placeholder="结束时间" style="width:100%"/>
                </el-form-item>
                <el-form-item prop="remarks" label="描述" :rules="[{required: true, message: '请输入描述', trigger: 'blur'}]">
                    <el-input v-model="addForm.remarks" placeholder="请输入描述" style="width:100%"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" @click="onSubmit(addFormRef)">确 定</el-button>
            </template>
        </el-dialog>
    </div>
</template>
<script setup>
    import {onMounted, reactive, ref, unref, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from "element-plus"
    import Table from '@/components/common/Table.vue'
    import {dateTimeFormat}from '@/utils/utils'
    import {couponPage,saveCoupon,updateCoupon,delCoupon} from "@/api/admin/coupon"
    import {shopPage} from "@/api/admin/shop"

    const addFormRef = ref(null)
    const state = reactive({
        searchForm:{
            name: null,
        },
        loading: false,
        page: {
            current: 1,
            size: 10,
            total: 1,
        },
        tableTitle: [
            {prop:'',label:'',fixed:'left'},
            {prop:'name',align:'left',label:'名称'},
            {prop:'price',label:'优惠金额',type:'price'},
            {prop:'norm',label:'限额',type:'price'},
            {prop:'status',alias:'statusName',label:'状态',type:'tag',option:{type:'info',size:'small',effect:'plain'}},
            {prop:'type',alias:'typeName',label:'类型',type:'tag',option:{type:'info',size:'small',effect:'plain'}},
            {prop:'startTime',label:'开始时间'},
            {prop:'endTime',label:'结束时间'},
            {prop:'shopId',alias:'shopName',label:'店铺'},
            {prop:'userId',alias:'username',label:'商户'},
            {prop:'updateTime',label:'更新时间'},
            {prop:'createTime',label:'创建时间'},
            {prop:'remarks',label:'描述'},
        ],
        tableData:[],
        operate: 0,
        sLoading: false,
        shopList:[],
        addVisible: false,
        addForm:{
            id:'',
            name:'',
            price: null,
            norm: null,
            status: 0,
            type: null,
            dates: null,
            startTime: null,
            endTime: null,
            shopId: '',
            remarks: '',
        },
    })

    const {
        searchForm,loading,page,tableTitle,tableData,operate,sLoading,shopList,addVisible,addForm,
    } = toRefs(state)

    onMounted(() => {
        onCouponPage();
    })

    const onCouponPage = () =>{
        state.loading = true;
        couponPage(Object.assign(state.page,state.searchForm)).then(res=>{
            state.loading = false;
            state.tableData = res.result.records;
            state.page.current = res.result.current;
            state.page.size = res.result.size;
            state.page.total = res.result.total;
        })
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onCouponPage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onCouponPage();
    }
    const onRefresh = () =>{
        state.searchForm = {
            name: null,
        }
        onCouponPage();
    }
    const onSearch = () =>{
        onCouponPage();
    }

    const onShopPage = (val) =>{
        let param = {name: val};
        let sPage = {current: 1,size: 10,total: 1};
        state.sLoading = true;
        shopPage(Object.assign(sPage, param)).then(res=>{
            state.sLoading = false;
            state.shopList = res.result.records;
        })
    }

    // 编辑
    const onEdit = (val) =>{
        state.operate = 1;
        state.addForm = {
            id: val.column.row.id,
            name: val.column.row.name,
            price: val.column.row.price,
            norm: val.column.row.norm,
            status: val.column.row.status,
            type: val.column.row.type,
            dates: [val.column.row.startTime,val.column.row.endTime],
            startTime: val.column.row.startTime,
            endTime: val.column.row.endTime,
            shopId: val.column.row.shopId,
            remarks: val.column.row.remarks,
        }
        onShopPage();
        state.addVisible = true;
    }

    // 删除
    const onDelete = (val) =>{
        ElMessageBox.confirm('此操作将永久删除, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            delCoupon({id: val.column.row.id}).then(res=>{
                if(res){
                    ElMessage.success('删除成功!');
                    onCouponPage();
                }
            })
        }).catch(e=>{
            console.log(e)
        })
    }

    // 添加用户信息
    const onAdd = () =>{
        state.operate = 0;
        state.addForm = {
            id:'',
            name:'',
            price: null,
            norm: null,
            status: 0,
            type: null,
            dates: null,
            startTime: null,
            endTime: null,
            shopId: '',
            remarks: '',
        }
        onShopPage();
        state.addVisible = true;
    }
    const onSubmit = async () =>{
        const form = unref(addFormRef);
        if (!form) return;
        await form.validate();

        state.addForm.startTime = dateTimeFormat(state.addForm.dates[0]);
        state.addForm.endTime = dateTimeFormat(state.addForm.dates[1]);
        if(state.operate == 0){
            saveCoupon(state.addForm).then(res=>{
                if(res){
                    ElMessage.success({
                        message: '添加成功！',
                        type: 'success'
                    });
                    onCouponPage();
                    state.addVisible = false;
                }
            });
        } else {
            updateCoupon(state.addForm).then(res=>{
                if(res){
                    ElMessage.success({
                        message: '修改成功！',
                        type: 'success'
                    });
                    onCouponPage();
                    state.addVisible = false;
                }
            });
        }
    }
</script>
<style lang="scss" scoped>
</style>