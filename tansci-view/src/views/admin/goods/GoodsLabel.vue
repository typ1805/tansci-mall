<template>
  <div class="goods-label">
    <el-card>
      <Table :data="tableData" :column="tableTitle" :operation="{show:true, width: 160,}" :page="page" :loading="loading"
        @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange">
        <template #search>
            <div><el-button type="primary" @click="onAdd">添加标签</el-button></div>
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
    <el-dialog title="标签管理" v-model="goodsLabelVisible" width="40%" :show-close="false">
        <el-form :model="goodsLabelForm" :rules="rules" ref="goodsLabelFormRef" label-position="right" label-width="100px">
            <el-form-item label="名称" prop="name" :rules="[{required: true, message: '请输入名称', trigger: 'blur'}]">
                <el-input v-model="goodsLabelForm.name" placeholder="请输入名称" style="width:100%"></el-input>
            </el-form-item>
            <el-form-item prop="type" label="类型" :rules="[{required: true, message: '请选择类型', trigger: 'change'}]">
                <el-radio-group v-model="goodsLabelForm.type">
                    <el-radio v-for="item in typeList" :key="item" :label="item.value">
                      <el-tag :type="item.value" effect="dark" round>{{item.label}}</el-tag>
                    </el-radio>
                </el-radio-group>
            </el-form-item>
        </el-form>
        <template #footer>
            <el-button @click="goodsLabelVisible = false">取 消</el-button>
            <el-button type="primary" @click="onSubmit(goodsLabelFormRef)">确 定</el-button>
        </template>
    </el-dialog>
  </div>
</template>
<script setup>
    import {onMounted, reactive, ref, unref, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from "element-plus"
    import Table from '@/components/common/Table.vue'
    import {goodsLabelPage,goodsLabelSave,goodsLabelUpdate,goodsLabelDel} from "@/api/admin/goodsLabel"

    const goodsLabelFormRef = ref(null)
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
          {prop:'name',label:'名称'},
          {prop:'type',label:'类型',type:'tag',option:{type:'info',size:'small',effect:'plain'}},
          {prop:'updateTime',label:'更新时间'},
          {prop:'createTime',label:'创建时间'},
        ],
        tableData:[],
        operate: 0,
        typeList: [
          {value:'primary',label:'primary'},
          {value:'success',label:'success'},
          {value:'warning',label:'warning'},
          {value:'danger',label:'danger'},
          {value:'info',label:'info'},
        ],
        goodsLabelVisible: false,
        goodsLabelForm:{
          id:'',
          name:'',
          type: '',
        },
    })

    const {
        searchForm,loading,page,tableTitle,tableData,operate,typeList,goodsLabelVisible,goodsLabelForm,
    } = toRefs(state)

    onMounted(() => {
        onGoodsLabelPage();
    })

    const onGoodsLabelPage = () =>{
        state.loading = true;
        goodsLabelPage(Object.assign(state.page,state.searchForm)).then(res=>{
            state.loading = false;
            state.tableData = res.result.records;
            state.page.current = res.result.current;
            state.page.size = res.result.size;
            state.page.total = res.result.total;
        })
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onGoodsLabelPage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onGoodsLabelPage();
    }
    const onRefresh = () =>{
        state.searchForm = {
            name: null,
        }
        onGoodsLabelPage();
    }
    const onSearch = () =>{
        onGoodsLabelPage();
    }

    // 编辑
    const onEdit = (val) =>{
        state.operate = 1;
        state.goodsLabelForm = {
            id: val.column.row.id,
            name: val.column.row.name,
            type: val.column.row.type,
        }
        state.goodsLabelVisible = true;
    }

    // 删除
    const onDelete = (val) =>{
        ElMessageBox.confirm('此操作将永久删除, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            goodsLabelDel({id: val.column.row.id}).then(res=>{
                if(res){
                    ElMessage.success('删除成功!');
                    onGoodsLabelPage();
                }
            })
        }).catch(e=>{
            console.log(e)
        })
    }

    // 添加用户信息
    const onAdd = () =>{
        state.operate = 0;
        state.goodsLabelForm = {
            goodsId:'',
            name:'',
            type: '',
        }
        state.goodsLabelVisible = true;
    }
    const onSubmit = async () =>{
        const form = unref(goodsLabelFormRef);
        if (!form) return;
        await form.validate();

        if(state.operate == 0){
            goodsLabelSave(state.goodsLabelForm).then(res=>{
                if(res){
                    ElMessage.success({
                        message: '添加成功！',
                        type: 'success'
                    });
                    onGoodsLabelPage();
                    state.goodsLabelVisible = false;
                }
            });
        } else {
            goodsLabelUpdate(state.goodsLabelForm).then(res=>{
                if(res){
                    ElMessage.success({
                        message: '修改成功！',
                        type: 'success'
                    });
                    onGoodsLabelPage();
                    state.goodsLabelVisible = false;
                }
            });
        }
    }
</script>
<style lang="scss" scoped>
</style>