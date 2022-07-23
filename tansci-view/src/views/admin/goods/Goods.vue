<template>
    <div class="goods">
        <el-card>
            <Table :data="tableData" :column="tableTitle" :operation="{show:true, width: 160,}" :page="page" :loading="loading"
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
            <el-drawer :title="title" v-model="addVisible" :show-close="false" size="80%">
                <el-form :model="addForm" :rules="rules" ref="addRuleForm" status-icon label-width="100px">
                    <el-row>
                        <el-col :span="12">
                            <el-form-item prop="name" label="商品名称" :rules="[{required: true, message: '请输入名称', trigger: 'blur'}]">
                                <el-input v-model="addForm.name" placeholder="请输入名称" maxlength="10" show-word-limit></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item prop="price" label="商品价格" :rules="[{required: true, message: '请输入价格', trigger: 'blur'}]">
                                <el-input-number v-model="addForm.price" placeholder="请输入价格" :min="1" controls-position="right" style="width: 100%;"></el-input-number>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item prop="intro" label="商品简介" :rules="[{required: true, message: '请输入简介', trigger: 'blur'}]">
                                <el-input v-model="addForm.intro" placeholder="请输入介绍" maxlength="50" show-word-limit></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item prop="stock" label="商品库存" :rules="[{required: true, message: '请输入库存', trigger: 'blur'}]">
                                <el-input-number v-model="addForm.stock" placeholder="请输入库存" :min="10" controls-position="right" style="width: 100%;"></el-input-number>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item prop="classify" label="商品分类" :rules="[{required: true, message: '请输入销量', trigger: 'change'}]">
                                <el-cascader v-model="addForm.classify" :options="classifyList" :props="{value:'id',label:'name',children:'children',emitPath:false,}" :show-all-levels="false" placeholder="请选择分类" style="width: 100%;"/>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item prop="sales" label="商品销量" :rules="[{required: true, message: '请输入销量', trigger: 'blur'}]">
                                <el-input-number v-model="addForm.sales" placeholder="请输入销量" :min="0" controls-position="right" style="width: 100%;"></el-input-number>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item prop="status" label="商品状态" :rules="[{required: true, message: '请选择状态', trigger: 'change'}]">
                                <el-radio-group v-model="addForm.status">
                                    <el-radio v-for="item in statusList" :key="item" :label="item.value">{{item.label}}</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item prop="labels" label="商品标签" :rules="[{required: true, message: '请选择标签', trigger: 'change'}]">
                                <el-select v-model="addForm.labels" multiple placeholder="请选择标签" style="width: 100%;">
                                    <el-option v-for="item in labelList" :key="item.id" :label="item.name" :value="item.id"/>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col>
                            <el-form-item prop="coverImg" label="商品主图" :rules="[{required: true, message: '请上传图片', trigger: 'change'}]">
                                <el-upload drag multiple :action="uploadAaction" 
                                    :headers="{Authorization: 'Bearer ' + token}"
                                    :file-list="addForm.coverImg!=''?[{name:addForm.coverImg,url:addForm.coverImg}]:[]" 
                                    :on-success="onImageSuccess" :on-remove="onImageDel" :before-upload="onBeforeImageUpload" :on-preview="onPreview"
                                    :limit="1" list-type="picture-card" style="width: 100%;">
                                </el-upload>
                                <div style="color: #909399;">只能上传jpg、png格式的图片，最多支持1张，且不超过200KB。</div>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col>
                            <el-form-item prop="imageList" label="轮播图片" :rules="[{required: true, message: '请上传图片', trigger: 'change'}]">
                                <el-upload drag multiple :action="uploadAaction" 
                                    :headers="{Authorization: 'Bearer ' + token}" 
                                    :file-list="addForm._imageList" 
                                    :on-success="onImageListSuccess" :on-remove="onImageListDel" :before-upload="onBeforeImageUpload" :on-preview="onPreview"
                                    list-type="picture-card" :limit="5" style="width: 100%;">
                                </el-upload>
                                <div style="color: #909399;">只能上传jpg、png格式的图片，最多支持5张，最少1张，张且不超过200KB。</div>
                            </el-form-item>
                        </el-col>
                    </el-row> 
                    <el-row>
                        <el-col>
                            <el-form-item prop="details" label="详情" :rules="[{required: true, message: '请输入详情', trigger: 'blur'}]">
                                <Toolbar style="width: 100%; border: 1px solid #dcdfe6;" :editor="editorRef" :defaultConfig="weConfig.toolbarConfig" :mode="weConfig.mode" />
                                <Editor style="height: 500px; width: 100%; border: 1px solid #dcdfe6"
                                    v-model="addForm.details" :defaultConfig="weConfig.editorConfig" :mode="weConfig.mode"
                                    @onChange="onHandleChange" @onCreated="onHandleCreated" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col>
                            <el-form-item>
                                <el-button type="primary" @click="onSubmit" style="width: 100px;">提交</el-button>
                            </el-form-item>
                        </el-col>
                    </el-row> 
                </el-form>
            </el-drawer>
            <el-dialog v-model="imageVisible" title="商品图预览">
                <el-image style="width: 100%; height: 100%;" :src="imageUrl" fit="fit" />
            </el-dialog>
        </el-card>
    </div>
</template>
<script setup>
    import {onMounted, onBeforeUnmount, reactive, shallowRef, ref, toRefs, unref} from 'vue'
    import {ElMessage, ElMessageBox} from "element-plus"
    import {useTokenStore} from '@/store/settings'
    import Table from '@/components/common/Table.vue'
    import {goodsPage, addGoods, updateGoods, delGoods,goodsImageList} from "@/api/admin/goods"
    import {goodsLabelList} from '@/api/admin/goodsLabel'
    import {goodsClassifyList} from '@/api/admin/goodsClassify'
    import {uploadImage, delFile} from "@/api/admin/upload"

    import '@wangeditor/editor/dist/css/style.css'
    import {Editor,Toolbar} from "@wangeditor/editor-for-vue"
    const editorRef = shallowRef() // 编辑器实例，必须用 shallowRef
    const weConfig = {
        mode: 'default',
        toolbarConfig:{},
        editorConfig:{
            placeholder: '请输入内容...',
            MENU_CONF: {},
        },

        // 记录上传的图片
        imageList:[],
    }

    const tokenStore = useTokenStore();
    const addRuleForm = ref(null)
    
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
            {prop:'coverImg',label:'主图',type:'image'},
            {prop:'intro',label:'简介'},
            {prop:'status',alias:'statusName',label:'状态',type:'tag',option:{type:'info',size:'small',effect:'plain'}},
            {prop:'classify',alias:'classifyName',label:'分类'},
            {prop:'price',label:'价格',type:'price'},
            {prop:'stock',label:'库存'},
            {prop:'sales',label:'销量'},
            // {prop:'labels',alias:'labelList',label:'标签'},
            {prop:'userId',alias:'userName',label:'商户ID'},
            {prop:'shopId',alias:'shopName',label:'店铺ID'},
            {prop:'updateTime',label:'更新时间'},
            {prop:'createTime',label:'创建时间'},
            // {prop:'remarks',label:'备注'}
        ],
        tableData:[],
        statusList:[
            {label:'未上架',value:0},
            {label:'已上架',value:1},
            {label:'已下架',value:2},
        ],
        labelList: [], // 商品标签
        classifyList:[], // 商品分类
        title: '添加商品',
        operate: 0,
        addVisible: false,
        addForm:{
            goodsId:'',
            name:'',
            intro: '',
            status:'',
            classify:'',
            price:'',
            coverImg:'',
            stock:'',
            sales:'',
            labels:'',
            details:'',
            imageList:[],
            _imageList:[],
            remarks:'',
        },
        token: tokenStore.getToken,
        uploadAaction: '/tansci/upload/uploadImage',
        imageUrl: '',
        imageVisible: false,
    })

    const {
        searchForm,loading,page,tableTitle,tableData,title,operate,addForm,addVisible,token,uploadAaction,
        statusList,labelList,classifyList,imageUrl,imageVisible,
    } = toRefs(state)

    // 组件销毁时，也及时销毁编辑器
    onBeforeUnmount(() => {
        const editor = editorRef.value;
        if (editor == null) return;
        editor.destroy();
    })

    onMounted(() => {
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

    // 商品标签
    const onGoodsLabelList = () =>{
        goodsLabelList({}).then(res=>{
            state.labelList = res.result
        })
    }

    // 商品分类
    const onGoodsClassifyList = () =>{
        goodsClassifyList({}).then(res=>{
            state.classifyList = res.result
        })
    }

    // 编辑
    const onEdit = (val) =>{
        state.title = "修改商品";
        state.operate = 1;
        state.addForm = {
            goodsId: val.column.row.goodsId,
            name: val.column.row.name,
            intro: val.column.row.intro,
            status: val.column.row.status,
            classify: val.column.row.classify,
            price: val.column.row.price,
            coverImg: val.column.row.coverImg,
            stock: val.column.row.stock,
            sales: val.column.row.sales,
            labels: val.column.row.labels?val.column.row.labels.split(','):'',
            details: val.column.row.details,
            remarks: val.column.row.remarks,
        }
        onGoodsClassifyList();
        onGoodsLabelList();
        // 获取商品图片
        goodsImageList({goodsId: val.column.row.goodsId}).then(res=>{
            state.addForm.imageList = res.result;
            state.addForm._imageList = res.result;
            state.addVisible = true;
        });
    }

    // 删除
    const onDelete = (val) =>{
        ElMessageBox.confirm('此操作将永久删除, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            delGoods({goodsId: val.column.row.goodsId}).then(res=>{
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
            goodsId:'',
            name:'',
            intro: '',
            status:'',
            classify:'',
            price:'',
            coverImg:'',
            stock:'',
            sales:'',
            labels:'',
            details:'',
            imageList:[],
            _imageList:[],
            remarks:'',
        }
        onGoodsClassifyList();
        onGoodsLabelList();
        state.addVisible = true;
    }
    const onSubmit = async () =>{
        const form = unref(addRuleForm);
        if (!form) return;
        await form.validate();

        // 删除详情中多余的图片
        onGetImage();

        // 标签格式化
        let labelList = [];
        state.addForm.labels.forEach(item=>{
            labelList.push({id: item})
        })
        state.addForm.labelList = labelList;
        state.addForm.labels = null;

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

    // =========富文本编辑器============
    // 上传图片的配置
    weConfig.editorConfig.MENU_CONF["uploadImage"] = {
        /**
         * file: 即选中的文件
         * insertFn: 最后插入图片函数
         */
        async customUpload(file, insertFn) {
            console.log(file)
            let form  = new FormData(); 
            form.append('file', file)
            uploadImage(form).then(res=>{
                if(res){
                    // 最后插入图片
                    insertFn(res.result.path, null, null);
                    // 记录上传的图片
                    weConfig.imageList.push(res.result.path);
                }
            })
        },
    }

    // 上传时对比数据删除多余的图片
    const onGetImage = () => {
        // 获取编辑器中的图片
        const images = editorRef.value.getElemsByType("image");
        var srcList = images.map((item)=>{
            return item.src;
        });

        // 上传成功的图片
        let _imageList = weConfig.imageList;

        let imgList = _imageList.filter(item => !srcList.includes(item));
        imgList.forEach(item => {
            let fileName = item.substr(item.lastIndexOf('/')+1);
            delFile({fileName: fileName}).then(res=>{
                console.log(res);
            });
        });

        // 重新赋值
        weConfig.imageList = srcList;
    }

    const onHandleChange = (editor) => {
        const contentStr = JSON.stringify(editor.children);
        const html = editor.getHtml();
        console.log(html)
    }
    
    // 记录 editor 实例
    const onHandleCreated = (editor) => {
        editorRef.value = editor;
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
    // 单个文件
    const onImageSuccess = (res) =>{
        state.addForm.coverImg = res.result.path;
    }
    const onImageDel = () =>{
        let path = state.addForm.coverImg;
        let fileName = path.substr(path.lastIndexOf('/')+1);
        delFile({fileName: fileName}).then(res=>{
            if(res){
                ElMessage.success("图片删除成功！");
            }
        });
    }
    // 多文件
    const onImageListSuccess = (res,file,files) =>{
        state.addForm.imageList.push({
            name: res.result.newName,
            url: res.result.path
        });
    }
    const onImageListDel = (file,files) =>{
        state.addForm._imageList = files;
        let name = file.response.result.newName;
        delFile({fileName: name}).then(res=>{
            if(res){
                let imgList = state.addForm.imageList;
                imgList.forEach((value,index)=>{
                    if(value.name == name){
                        imgList.splice(index,1)
                    }
                })
                state.addForm.imageList = imgList;
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
    .goods{
        .el-upload{
            width: 100% !important;
        }
        .el-upload-dragger{
            width: 100% !important;
        }
    }
</style>