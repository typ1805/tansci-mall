<template>
    <div class="config-goods">
        <el-card>
        <Table :data="tableData" :column="tableTitle" :operation="{show:true, width: 160,}" :page="page" :loading="loading"
            @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange">
            <template #search>
                <div><el-button type="info" @click="onAdd">添加</el-button></div>
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
        <el-dialog title="活动商品" v-model="addVisible" width="40%" :show-close="false">
            <el-form :model="addForm" :rules="rules" ref="addFormRef" label-position="right" label-width="100px">
                <el-form-item prop="name" label="名称" :rules="[{required: true, message: '请输入名称', trigger: 'blur'}]">
                    <el-input v-model="addForm.name" placeholder="请输入名称" style="width:100%"></el-input>
                </el-form-item>
                <el-form-item prop="type" label="类型" :rules="[{required: true, message: '请选择类型', trigger: 'change'}]">
                    <el-radio-group v-model="addForm.type">
                        <el-radio :label="1">秒杀</el-radio>
                        <el-radio :label="2">排行榜</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item prop="goodsId" label="商品" :rules="[{required: true, message: '请选择商品', trigger: 'change'}]">
                    <el-select v-model="addForm.goodsId" filterable remote placeholder="请选商品" :remote-method="onGoodsPage" :loading="goodsLoading" style="width:100%">
                        <el-option v-for="goods in goodsList" :key="goods" :label="goods.name" :value="goods.goodsId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item prop="sort" label="排序" :rules="[{required: true, message: '排序', trigger: 'blur'}]">
                    <el-input-number v-model="addForm.sort" :min="0" :max="999" style="width:100%"></el-input-number>
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
    import {configGoodsPage,saveConfigGoods,updateConfigGoods,delConfigGoods} from "@/api/admin/configGoods"
    import {goodsPage} from "@/api/admin/goods"

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
            {prop:'name',label:'配置描述'},
            {prop:'type',alias:'typeName',label:'类型',type:'tag',option:{size:'small',effect:'plain',typeList:[
                    {value:2,label:'warning'},
                    {value:1,label:'info'},
                ]}},
            {prop:'goodsId',label:'商品编码'},
            {prop:'updateTime',label:'更新时间'},
            {prop:'createTime',label:'创建时间'},
        ],
        tableData:[],
        operate: 0,
        goodsLoading: false,
        goodsList:[],
        addVisible: false,
        addForm:{
            id:'',
            name:'',
            type: '',
            goodsId: '',
            sort: null,
        },
    })

    const {
        searchForm,loading,page,tableTitle,tableData,operate,goodsLoading,goodsList,addVisible,addForm,
    } = toRefs(state)

    onMounted(() => {
        onConfigGoodsPage();
    })

    const onConfigGoodsPage = () =>{
        state.loading = true;
        configGoodsPage(Object.assign(state.page,state.searchForm)).then(res=>{
            state.loading = false;
            state.tableData = res.result.records;
            state.page.current = res.result.current;
            state.page.size = res.result.size;
            state.page.total = res.result.total;
        })
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onConfigGoodsPage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onConfigGoodsPage();
    }
    const onRefresh = () =>{
        state.searchForm = {
            name: null,
        }
        onConfigGoodsPage();
    }
    const onSearch = () =>{
        onConfigGoodsPage();
    }

    const onGoodsPage = (val) =>{
        let param = {name: val};
        let gPage = {current: 1,size: 10,total: 1};
        state.goodsLoading = true;
        goodsPage(Object.assign(gPage, param)).then(res=>{
            state.goodsLoading = false;
            state.goodsList = res.result.records;
        })
    }

    // 编辑
    const onEdit = (val) =>{
        state.operate = 1;
        state.addForm = {
            id: val.column.row.id,
            name: val.column.row.name,
            type: val.column.row.type,
            goodsId: val.column.row.goodsId,
            sort: val.column.row.sort,
        }
        onGoodsPage();
        state.addVisible = true;
    }

    // 删除
    const onDelete = (val) =>{
        ElMessageBox.confirm('此操作将永久删除, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            delConfigGoods({id: val.column.row.id}).then(res=>{
                if(res){
                    ElMessage.success('删除成功!');
                    onConfigGoodsPage();
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
            type: '',
            goodsId: '',
            sort: 1,
        }
        onGoodsPage();
        state.addVisible = true;
    }
    const onSubmit = async () =>{
        const form = unref(addFormRef);
        if (!form) return;
        await form.validate();

        if(state.operate == 0){
            saveConfigGoods(state.addForm).then(res=>{
                if(res){
                    ElMessage.success({
                        message: '添加成功！',
                        type: 'success'
                    });
                    onConfigGoodsPage();
                    state.addVisible = false;
                }
            });
        } else {
            updateConfigGoods(state.addForm).then(res=>{
                if(res){
                    ElMessage.success({
                        message: '修改成功！',
                        type: 'success'
                    });
                    onConfigGoodsPage();
                    state.addVisible = false;
                }
            });
        }
    }
</script>
<style lang="scss" scoped>
</style>