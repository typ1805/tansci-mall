<template>
    <div class="index-carousel">
        <el-card>
        <Table :data="tableData" :column="tableTitle" :operation="{show:true, width: 160,}" :page="page" :loading="loading"
            @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange" @onSwitchChange="onSwitchChange">
            <template #search>
                <div><el-button type="primary" @click="onAdd">添加</el-button></div>
                <div><el-button @click="onRefresh" icon="RefreshRight" circle></el-button></div>
            </template>
            <template #column="scope">
                <el-button @click="onEdit(scope)" type="text" style="color:var(--edit)">编辑</el-button>
                <el-button @click="onDelete(scope)" type="text" style="color:var(--delete)">删除</el-button>
            </template>
        </Table>
        </el-card>
        <el-dialog title="首页标签管理" v-model="addVisible" width="40%" :show-close="false">
            <el-form :model="addForm" :rules="rules" ref="addFormRef" label-position="right" label-width="100px">
                <el-form-item prop="name" label="标签名称" :rules="[{required: true, message: '请输入标签名称', trigger: 'blur'}]">
                    <el-input v-model="addForm.name" placeholder="请输入标签名称" style="width:100%"></el-input>
                </el-form-item>
                <el-form-item prop="url" label="链接地址" :rules="[{required: true, message: '请输入链接地址', trigger: 'blur'}]">
                    <el-input v-model="addForm.url" placeholder="请输入链接地址" style="width:100%"></el-input>
                </el-form-item>
                <el-form-item prop="icon" label="图标" :rules="[{required: true, message: '请输入图标', trigger: 'blur'}]">
                    <el-input v-model="addForm.icon" placeholder="请输入图标" style="width:100%"></el-input>
                </el-form-item>
                <el-form-item prop="color" label="色值" :rules="[{required: true, message: '请输入色值', trigger: 'blur'}]">
                    <el-input v-model="addForm.color" placeholder="请输入色值" style="width:100%"></el-input>
                </el-form-item>
                <el-form-item prop="status" label="状态" :rules="[{required: true, message: '请选择状态', trigger: 'change'}]">
                    <el-radio-group v-model="addForm.status">
                        <el-radio :label="0">正常</el-radio>
                        <el-radio :label="1">下线</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item prop="sort" label="排序" :rules="[{required: true, message: '请输入排序', trigger: 'blur'}]">
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
    import {indexConfigPage,saveIndexConfig,updateIndexConfig,delIndexConfig} from "@/api/admin/indexConfig"

    const addFormRef = ref(null)
    const state = reactive({
        loading: false,
        page: {
            current: 1,
            size: 10,
            total: 1,
        },
        tableTitle: [
            {prop:'',label:'',fixed:'left'},
            {prop:'name',label:'标签名称'},
            {prop:'url',label:'链接地址'},
            {prop:'icon',label:'图标'},
            {prop:'color',label:'色值'},
            {prop:'status',alias:'statusName',label:'状态',type:'switch',
                option:{
                    activeValue:1,activeColor:'#13ce66',activeText:'正常',
                    inactiveValue:0,inactiveColor:'#ff4949',inactiveText:'下线',
                    inlinePrompt: false,size:'large'
                }
            },
            {prop:'sort',label:'排序'},
            {prop:'updateTime',label:'更新时间'},
            {prop:'createTime',label:'创建时间'},
        ],
        tableData:[],
        operate: 0,
        addVisible: false,
        addForm:{
            id:'',
            name:'',
            url:'',
            icon: '',
            color: '',
            status: 0,
            sort: 0,
        },
    })

    const {
        searchForm,loading,page,tableTitle,tableData,operate,addVisible,addForm
    } = toRefs(state)

    onMounted(() => {
        onIndexConfigPage();
    })

    const onIndexConfigPage = () =>{
        state.loading = true;
        indexConfigPage(state.page).then(res=>{
            state.loading = false;
            state.tableData = res.result.records;
            state.page.current = res.result.current;
            state.page.size = res.result.size;
            state.page.total = res.result.total;
        })
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onIndexConfigPage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onIndexConfigPage();
    }
    const onRefresh = () =>{
        onIndexConfigPage();
    }

    const onSwitchChange = (row) =>{
        updateIndexConfig({
            id: row.id,
            status: row.status
        }).then(res=>{
            if(res){
                ElMessage.success('操作成功！');
                onIndexConfigPage();
            }
        });
    }

    // 编辑
    const onEdit = (val) =>{
        state.operate = 1;
        state.addForm = {
            id: val.column.row.id,
            name: val.column.row.name,
            url: val.column.row.url,
            icon: val.column.row.icon,
            color: val.column.row.color,
            status: val.column.row.status,
            sort: val.column.row.sort,
        }
        state.addVisible = true;
    }

    // 删除
    const onDelete = (val) =>{
        ElMessageBox.confirm('此操作将永久删除, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            delIndexConfig({id: val.column.row.id}).then(res=>{
                if(res){
                    ElMessage.success('删除成功!');
                    onIndexConfigPage();
                }
            })
        }).catch(e=>{
            console.log(e)
        })
    }

    // 添加
    const onAdd = () =>{
        state.operate = 0;
        state.addForm = {
            id:'',
            name:'',
            url:'',
            icon: '',
            color: '',
            status: 0,
            sort: 0,
        }
        state.addVisible = true;
    }
    const onSubmit = async () =>{
        const form = unref(addFormRef);
        if (!form) return;
        await form.validate();

        if(state.operate == 0){
            saveIndexConfig(state.addForm).then(res=>{
                if(res){
                    ElMessage.success({
                        message: '添加成功！',
                        type: 'success'
                    });
                    onIndexConfigPage();
                    state.addVisible = false;
                }
            });
        } else {
            updateIndexConfig(state.addForm).then(res=>{
                if(res){
                    ElMessage.success({
                        message: '修改成功！',
                        type: 'success'
                    });
                    onIndexConfigPage();
                    state.addVisible = false;
                }
            });
        }
    }
</script>
<style lang="scss" scoped>
</style>