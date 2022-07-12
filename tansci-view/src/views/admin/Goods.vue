<template>
    <div class="goods">
        <el-card>
            <Table :data="tableData" :column="tableTitle" :operation="{show:true, width: mobile?80:160,}" :page="page" :loading="loading"
                @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange">
                <template #search>
                    <div><el-button type="primary" @click="onAdd">添加商品</el-button></div>
                    <div><el-input v-model="searchForm.name" placeholder="请输入商品名称"></el-input></div>
                    <div><el-button @click="onRefresh" icon="RefreshRight" circle></el-button></div>
                    <div><el-button @click="onSearch" type="primary" icon="Search">查询</el-button></div>
                </template>
                <template #column="scope">
                    <el-button @click="onEdit(scope)" type="text" style="color:var(--edit)">编辑</el-button>
                    <el-button @click="onDelete(scope)" type="text" style="color:var(--delete)">删除</el-button>
                </template>
            </Table>
            <el-dialog :title="title" v-model="addVisible" :show-close="false" :width="mobile? '100%':'50%'">
                <el-form :model="addForm" :rules="rules" ref="addRuleForm" status-icon label-width="100px">
                    <el-form-item prop="name" label="名称" :rules="[{required: true, message: '请输入名称', trigger: 'blur'}]">
                        <el-input v-model="addForm.name" placeholder="请输入名称"></el-input>
                    </el-form-item>
                    <el-form-item prop="code" label="编号" :rules="[{required: true, message: '请输入编号', trigger: 'blur'}]">
                        <el-input v-model="addForm.code" placeholder="请输入编号"></el-input>
                    </el-form-item>
                    <el-form-item prop="model" label="型号">
                        <el-input v-model="addForm.model" placeholder="请输入型号"></el-input>
                    </el-form-item>
                    <el-form-item prop="status" label="状态" :rules="[{required: true, message: '请选择状态', trigger: 'change'}]">
                        <el-radio-group v-model="addForm.status">
                            <el-radio :label="0">未上架</el-radio>
                            <el-radio :label="1">已上架</el-radio>
                            <el-radio :label="2">已下架</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item prop="price" label="价格" :rules="[{required: true, message: '请输入价格', trigger: 'blur'}]">
                        <el-input-number v-model="addForm.price" placeholder="请输入价格" :min="1" style="width: 100%;"></el-input-number>
                    </el-form-item>
                    <el-form-item prop="evaluate" label="好评量" :rules="[{required: true, message: '请输入价格', trigger: 'blur'}]">
                        <el-input-number v-model="addForm.evaluate" placeholder="请输入好评数量" :min="1" style="width: 100%;"></el-input-number>
                    </el-form-item>
                    <el-form-item prop="stock" label="库存" :rules="[{required: true, message: '请输入库存', trigger: 'blur'}]">
                        <el-input-number v-model="addForm.stock" placeholder="请输入库存" :min="1" style="width: 100%;"></el-input-number>
                    </el-form-item>
                    <el-form-item prop="imagePath" label="图片" :rules="[{required: true, message: '请上传图片', trigger: 'change'}]">
                        <el-upload drag multiple :action="uploadAaction" :headers="{Authorization: 'Bearer ' + token}" :file-list="addForm.imagePath!=''?[{url:addForm.imagePath}]:[]" :on-success="onImageSuccess" :on-remove="onImageDel" :before-upload="onBeforeImageUpload" style="width: 100%;">
                            <el-icon class="el-icon--upload"><upload-filled /></el-icon>
                            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                            <template #tip>
                                <div class="el-upload__tip">只能上传jpg、png格式的图片，且不超过200KB。</div>
                            </template>
                        </el-upload>
                    </el-form-item>
                    <el-form-item prop="details" label="详情">
                        <el-input v-model="addForm.details" type="textarea" :rows="2" placeholder="请输入详情"></el-input>
                    </el-form-item>
                </el-form>
                <template #footer>
                    <span class="dialog-footer">
                    <el-button @click="addVisible = false">取消</el-button>
                    <el-button type="primary" @click="onSubmit">提交</el-button>
                    </span>
                </template>
            </el-dialog>
        </el-card>
    </div>
</template>
<script setup>
    import {onMounted, reactive, ref, toRefs, unref} from 'vue'
    import Table from '@/components/common/Table.vue'
    import {goodsPage, addGoods, updateGoods, delGoods, delFile} from "@/api/systemApi";
    import {ElMessage, ElMessageBox} from "element-plus";
    import {useTokenStore} from '@/store/settings'
    import {isMobile} from '@/utils/utils'

    const tokenStore = useTokenStore();
    const addRuleForm = ref(null)
    const state = reactive({
        mobile: false,
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
            {prop:'code',label:'编号'},
            {prop:'model',label:'型号'},
            {prop:'price',label:'价格'},
            {prop:'evaluate',label:'好评数量'},
            {prop:'details',label:'详情'},
            {prop:'status',alias:'statusName',label:'状态',type:'tag',option:{type:'info',size:'small',effect:'plain'}},
            {prop:'stock',label:'库存'},
            {prop:'userName',label:'商户'},
            {prop:'updateTime',label:'更新时间'},
            {prop:'createTime',label:'创建时间'},
            {prop:'remarks',label:'备注'}
        ],
        tableData:[],
        title: '添加商品',
        operate: 0,
        addVisible: false,
        addForm:{
            id:'',
            name:'',
            code: '',
            model:'',
            status:'',
            price:'',
            imagePath:'',
            evaluate:'',
            details:'',
            stock:'',
            remarks:'',
        },
        token: tokenStore.getToken,
        uploadAaction: '/vending/upload/uploadImage',
    })

    const {
        mobile,searchForm,loading,page,tableTitle,tableData,title,operate,addForm,addVisible,token,uploadAaction
    } = toRefs(state)

    onMounted(() => {
        if(isMobile()){
            state.mobile = true;
        }
        onGoodsPage();
    })

    const onGoodsPage = () =>{
        state.loading = true;
        goodsPage(Object.assign(state.page,state.searchForm)).then(res=>{
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
    const onRefresh = () =>{
        state.searchForm = {
            name: null,
        }
        onGoodsPage();
    }
    const onSearch = () =>{
        onGoodsPage();
    }

    // 编辑
    const onEdit = (val) =>{
        state.title = "修改商品";
        state.operate = 1;
        state.addForm = {
            id: val.column.row.id,
            name: val.column.row.name,
            code: val.column.row.code,
            model: val.column.row.model,
            status: val.column.row.status,
            price: val.column.row.price,
            imagePath: val.column.row.imagePath,
            evaluate: val.column.row.evaluate,
            details: val.column.row.details,
            stock: val.column.row.stock,
            remarks: val.column.row.remarks,
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
            delGoods({id: val.column.row.id}).then(res=>{
                if(res){
                    ElMessage.success('删除成功!');
                    onGoodsPage();
                }
            })
        }).catch(e=>{
            console.log(e)
        })
    }

    // 添加用户信息
    const onAdd = () =>{
        state.title = "添加商品";
        state.operate = 0;
        state.addForm = {
            id:'',
            name:'',
            code: '',
            model:'',
            status:'',
            price:'',
            imagePath:'',
            evaluate:'',
            details:'',
            stock:'',
            remarks:'',
        }
        state.addVisible = true;
    }
    const onSubmit = async () =>{
        const form = unref(addRuleForm);
        if (!form) return;
        await form.validate();

        if(state.operate == 0){
            addGoods(state.addForm).then(res=>{
                if(res){
                    ElMessage.success({
                        message: '添加成功！',
                        type: 'success'
                    });
                    onGoodsPage();
                    state.addVisible = false;
                }
            });
        } else {
            updateGoods(state.addForm).then(res=>{
                if(res){
                    ElMessage.success({
                        message: '修改成功！',
                        type: 'success'
                    });
                    onGoodsPage();
                    state.addVisible = false;
                }
            });
        }
    }

    const onImageSuccess = (res) =>{
        state.addForm.imagePath = res.result.path;
    }
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
    const onImageDel = () =>{
        let path = state.addForm.imagePath;
        let fileName = path.substr(path.lastIndexOf('/')+1);
        delFile({fileName: fileName}).then(res=>{
            if(res){
                ElMessage.success("图片删除成功！");
            }
        });
    }
</script>
<style lang="scss" scoped>
    .goods{
        .el-upload{
            width: 100% !important;
        }
        .el-upload-dragger{
            width: 100% !important;
        }
    }
</style>