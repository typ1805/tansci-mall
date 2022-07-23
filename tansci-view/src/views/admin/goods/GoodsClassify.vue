<template>
    <div class="goods-classify">
        <el-card class="classify-tree" shadow="never">
            <el-radio-group @change="onOperateChange" v-model="operate" style="margin-bottom: 15px;">
                <el-radio-button :label="1">添加</el-radio-button>
                <el-radio-button :label="2">编辑</el-radio-button>
                <el-radio-button :label="3">删除</el-radio-button>
            </el-radio-group>
            <el-tree :data="treeData" :props="{children: 'children', label: 'name'}" accordion highlight-current @node-click="onNodeClick"></el-tree>
        </el-card>
        <el-card class="classify-detail" shadow="never">
            <Table :data="tableData" :column="tableTitle" :page="page" :loading="loading"
                @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange">
            </Table>
        </el-card>
        <el-dialog title="分类管理" v-model="goodsClassifyVisible" width="40%" :show-close="false">
            <el-form :model="goodsClassifyForm" :rules="rules" ref="goodsClassifyFormRef" label-position="right" label-width="100px">
                <el-form-item label="名称" prop="name" :rules="[{required: true, message: '名称不能为空', trigger: 'blur'}]">
                    <el-input v-model="goodsClassifyForm.name" placeholder="请输入名称" style="width:100%"></el-input>
                </el-form-item>
                <el-form-item label="排序" prop="sort" :rules="[{required: true, message: '排序不能为空', trigger: 'blur'}]">
                    <el-input-number v-model="goodsClassifyForm.sort" :min="0" :max="999" style="width:100%"></el-input-number>
                </el-form-item>
            </el-form>
            <template #footer>
                <el-button @click="goodsClassifyVisible = false">取 消</el-button>
                <el-button type="primary" @click="onSubmit(goodsClassifyFormRef)">确 定</el-button>
            </template>
        </el-dialog>
    </div>
</template>
<script setup>
    import {onMounted, reactive, ref, unref, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from "element-plus"
    import Table from '@/components/common/Table.vue'
    import {goodsPage} from "@/api/admin/goods"
    import {goodsClassifyList,goodsClassifySave,goodsClassifyUpdate,goodsClassifyDel} from '@/api/admin/goodsClassify'

    const goodsClassifyFormRef = ref(null);
    const state = reactive({
        operate: 0,
        treeData:[],
        loading: false,
        page: {
          current: 1,
          size: 10,
          total: 1,
        },
        tableTitle: [
            {prop:'name',label:'名称'},
            {prop:'coverImg',label:'主图'},
            {prop:'intro',label:'简介'},
            {prop:'status',alias:'statusName',label:'状态',type:'tag',option:{type:'info',size:'small',effect:'plain'}},
            {prop:'classify',alias:'classifyName',label:'分类'},
            {prop:'price',label:'价格'},
            {prop:'stock',label:'库存'},
            {prop:'sales',label:'销量'},
            {prop:'userId',alias:'userName',label:'商户ID'},
            {prop:'shopId',alias:'shopName',label:'店铺ID'},
            {prop:'updateTime',label:'更新时间'},
            {prop:'createTime',label:'创建时间'},
        ],
        tableData:[],
        goodsClassifyVisible: false,
        goodsClassifyForm: {
            id: '',
            parentId: '0',
            name:'',
            sort: 0,
        },
    })

    const {
        operate,treeData,loading,page,tableTitle,tableData,goodsClassifyVisible,goodsClassifyForm,
    } = toRefs(state)

    onMounted(() => {
        onGoodsClassify();
    })

    const onGoodsClassify = () =>{
        goodsClassifyList({}).then(res=>{
            if(res){
                state.treeData = res.result;
            }
        })
    }

    const onOperateChange = (val) =>{
        if(val == 1){
            state.operate = 1;
            let parentId = state.goodsClassifyForm.id;
            state.goodsClassifyForm = {
                id: '',
                parentId: parentId?parentId:'0',
                name:'',
                sort: 0,
            }
            state.goodsClassifyVisible = true;
        } else if(val == 2) {
            state.operate = 2;
            state.goodsClassifyVisible = true;
        } else {
            if(!state.goodsClassifyForm.id){
                ElMessage.warning("请选择要删除的分类！")
                return false;
            }
            state.operate = 0;
            ElMessageBox.confirm('此操作将永久删除该菜单, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                goodsClassifyDel({id: state.goodsClassifyForm.id}).then(res=>{
                    if(res){
                        ElMessage.success("删除成功!");
                        onGoodsClassify();
                    }
                })
            }).catch(e=>{
                console.log(e)
            })
        }
    }

    const onSubmit = async () =>{
        const form = unref(goodsClassifyFormRef);
        if (!form) return;
        await form.validate();

        if(state.operate == 1){
            goodsClassifySave(state.goodsClassifyForm).then(res=>{
                if(res){
                    ElMessage.success("添加成功！");
                    state.operate = 0;
                    onGoodsClassify();
                    state.goodsClassifyVisible = false;
                }
            })
        } else if (state.operate == 2) {
            goodsClassifyUpdate(state.goodsClassifyForm).then(res=>{
                if(res){
                    ElMessage.success("更新成功！");
                    state.operate = 0;
                    onGoodsClassify();
                    state.goodsClassifyVisible = false;
                }
            })
        }
    }

    const onNodeClick = (data) =>{
        state.goodsClassifyForm = {
            id: data.id,
            parentId: data.parentId,
            name: data.name,
            sort: data.sort,
        }
        onGoodsPage(data.id);
    }

    const onGoodsPage = (classify) =>{
        state.loading = true;
        goodsPage(Object.assign(state.page,{classify:classify})).then(res=>{
            state.loading = false;
            state.tableData = res.result.records;
            state.page.current = res.result.current;
            state.page.size = res.result.size;
            state.page.total = res.result.total;
        })
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onGoodsPage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onGoodsPage();
    }
</script>
<style lang="scss" scoped>
  .goods-classify{
    display: flex;
        padding-bottom: 4rem;
        .classify-tree{
            min-width: 300px;
            .el-tree-node:focus>.el-tree-node__content{
                background-color: #fff !important;
                color: var(--theme) !important;
            }
            .el-tree--highlight-current .el-tree-node.is-current > .el-tree-node__content { 
                background-color: #fff !important;
                color: var(--theme) !important;
            }
        }
        .classify-detail{
            margin-left: 0.2rem;
            width: 100%;
        }
  }
</style>