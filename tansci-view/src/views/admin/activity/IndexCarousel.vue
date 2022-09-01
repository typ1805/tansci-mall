<template>
    <div class="index-carousel">
        <el-card>
        <Table :data="tableData" :column="tableTitle" :operation="{show:true, width: 160,}" :page="page" :loading="loading"
            @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange" @onSwitchChange="onSwitchChange">
            <template #search>
                <div><el-button type="info" @click="onAdd">添加</el-button></div>
                <div><el-button @click="onRefresh" icon="RefreshRight" circle></el-button></div>
            </template>
            <template #column="scope">
                <el-button @click="onEdit(scope)" type="text" style="color:var(--edit)">编辑</el-button>
                <el-button @click="onDelete(scope)" type="text" style="color:var(--delete)">删除</el-button>
            </template>
        </Table>
        </el-card>
        <el-dialog title="首页轮播管理" v-model="addVisible" width="40%" :show-close="false">
            <el-form :model="addForm" :rules="rules" ref="addFormRef" label-position="right" label-width="100px">
                <el-form-item prop="url" label="链接地址" :rules="[{required: true, message: '请输入链接地址', trigger: 'blur'}]">
                    <el-input v-model="addForm.url" placeholder="请输入链接地址" style="width:100%"></el-input>
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
                <el-form-item prop="imageUrl" label="图片" :rules="[{required: true, message: '请上传图片', trigger: 'blur'}]">
                    <el-upload drag multiple :action="uploadAaction" 
                        :headers="{Authorization: 'Bearer ' + token}"
                        :file-list="addForm.imageUrl!=''?[{name:addForm.imageUrl,url:addForm.imageUrl}]:[]" 
                        :on-success="onImageSuccess" :on-remove="onImageDel" :before-upload="onBeforeImageUpload" :on-preview="onPreview"
                        :limit="1" list-type="picture-card" style="width: 100%;">
                    </el-upload>
                    <div style="color: #909399;">只能上传jpg、png格式的图片，最多支持1张，且不超过200KB。</div>
                </el-form-item>
            </el-form>
            <template #footer>
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" @click="onSubmit(addFormRef)">确 定</el-button>
            </template>
        </el-dialog>
        <el-dialog v-model="imageVisible" title="商品图预览">
            <el-image style="width: 100%; height: 100%;" :src="imageUrl" fit="fit" />
        </el-dialog>
    </div>
</template>
<script setup>
    import {onMounted, reactive, ref, unref, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from "element-plus"
    import Table from '@/components/common/Table.vue'
    import {useTokenStore} from '@/store/settings'
    import {indexCarouselPage,saveIndexCarousel,updateIndexCarousel,delIndexCarousel} from "@/api/admin/indexCarousel"
    import {delFile} from "@/api/admin/upload"

    const tokenStore = useTokenStore();
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
            {prop:'url',label:'链接地址'},
            {prop:'imageUrl',label:'图片地址',type:'image'},
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
            url:'',
            imageUrl: '',
            status: 0,
            sort: 0,
        },
        token: tokenStore.getToken,
        uploadAaction: '/tansci/upload/uploadImage',
        imageUrl: '',
        imageVisible: false,
    })

    const {
        searchForm,loading,page,tableTitle,tableData,operate,addVisible,addForm,token,uploadAaction,imageUrl,imageVisible
    } = toRefs(state)

    onMounted(() => {
        onIndexCarouselPage();
    })

    const onIndexCarouselPage = () =>{
        state.loading = true;
        indexCarouselPage(state.page).then(res=>{
            state.loading = false;
            state.tableData = res.result.records;
            state.page.current = res.result.current;
            state.page.size = res.result.size;
            state.page.total = res.result.total;
        })
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onIndexCarouselPage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onIndexCarouselPage();
    }
    const onRefresh = () =>{
        onIndexCarouselPage();
    }

    const onSwitchChange = (row) =>{
        updateIndexCarousel({
            id: row.id,
            status: row.status
        }).then(res=>{
            if(res){
                ElMessage.success('操作成功！');
                onIndexCarouselPage();
            }
        });
    }

    // 编辑
    const onEdit = (val) =>{
        state.operate = 1;
        state.addForm = {
            id: val.column.row.id,
            url: val.column.row.url,
            imageUrl: val.column.row.imageUrl,
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
            delIndexCarousel({id: val.column.row.id}).then(res=>{
                if(res){
                    ElMessage.success('删除成功!');
                    onIndexCarouselPage();
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
            url:'',
            imageUrl: '',
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
            saveIndexCarousel(state.addForm).then(res=>{
                if(res){
                    ElMessage.success({
                        message: '添加成功！',
                        type: 'success'
                    });
                    onIndexCarouselPage();
                    state.addVisible = false;
                }
            });
        } else {
            updateIndexCarousel(state.addForm).then(res=>{
                if(res){
                    ElMessage.success({
                        message: '修改成功！',
                        type: 'success'
                    });
                    onIndexCarouselPage();
                    state.addVisible = false;
                }
            });
        }
    }

    // =========上传文件===========
    const onBeforeImageUpload = (file) =>{
        let testmsg = file.name.substring(file.name.lastIndexOf('.') + 1)
        let jpg = testmsg === 'jpg';
        let png = testmsg === 'png';
        let jpeg = testmsg === 'jpeg';
        if (!jpg && !png && !jpeg) {
            ElMessage.error('上传图片只能是jpg、png格式!');
            return false;
        }

        let isLt2M = file.size / 1024 < 200;
        if (!isLt2M) {
            ElMessage.error('上传图片大小不能超过200KB!');
            return false;
        }
    }
    const onImageSuccess = (res) =>{
        state.addForm.imageUrl = res.result.path;
    }
    const onImageDel = () =>{
        let path = state.addForm.imageUrl;
        let fileName = path.substr(path.lastIndexOf('/')+1);
        delFile({fileName: fileName}).then(res=>{
            if(res){
                ElMessage.success("图片删除成功！");
            }
        });
    }
    // 预览图片
    const onPreview = (file) =>{
        state.imageUrl = file.url;
        state.imageVisible = true;
    }
</script>
<style lang="scss" scoped>
</style>