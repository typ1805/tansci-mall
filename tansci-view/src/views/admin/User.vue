<template>
    <div class="user">
        <el-card>
            <Table :data="tableData" :column="tableTitle" :operation="{show:true, width: mobile?80:160,}" :page="page" :loading="loading"
                @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange">
                <template #search>
                    <div><el-button type="primary" @click="onAddUser">添加用户</el-button></div>
                    <div><el-input v-model="searchForm.nickname" placeholder="请输入用户名称"></el-input></div>
                    <div><el-button @click="onRefresh" icon="RefreshRight" circle></el-button></div>
                    <div><el-button @click="onSearch" type="primary" icon="Search">查询</el-button></div>
                </template>
                <template #column="scope">
                    <el-button @click="onEdit(scope)" type="text" style="color:var(--edit)">编辑</el-button>
                    <el-button @click="onRole(scope)" type="text" style="color:var(--role)">权限</el-button>
                    <el-button @click="onDelete(scope)" type="text" style="color:var(--delete)">删除</el-button>
                </template>
            </Table>
            <el-dialog :title="userTitle" v-model="userVisible" :show-close="false" :width="mobile? '100%':'50%'">
                <el-form :model="userForm" :rules="rules" ref="userRuleForm" status-icon label-width="100px">
                    <el-form-item prop="username" label="用户名" :rules="[
                        {required: true, message: '请输入名称', trigger: 'blur'},
                        {pattern: /^[a-zA-Z]\w{4,17}$/, message: '以字母开头，长度在5~18之间，只能包含字母、数字', trigger: 'blur'}]">
                        <el-input v-if="operate==0" v-model="userForm.username" placeholder="请输入名称"></el-input>
                        <el-input v-if="operate==1" disabled v-model="userForm.username" placeholder="请输入名称"></el-input>
                    </el-form-item>
                    <el-form-item prop="nickname" label="昵称" :rules="[{required: true, message: '请输入昵称', trigger: 'blur'}]">
                        <el-input v-model="userForm.nickname" placeholder="请输入昵称"></el-input>
                    </el-form-item>
                    <el-form-item prop="type" label="类型" :rules="[{required: true, message: '请选择类型', trigger: 'change'}]">
                        <el-radio-group v-model="userForm.type">
                            <el-radio :label="1">管理员</el-radio>
                            <el-radio :label="2">普通用户</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item prop="gender" label="性别" :rules="[{required: true, message: '请选择性别', trigger: 'change'}]">
                        <el-radio-group v-model="userForm.gender">
                            <el-radio :label="0">男</el-radio>
                            <el-radio :label="1">女</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item prop="phone" label="手机号" :rules="[
                        {required: true, message: '请输入手机号', trigger: 'blur'},
                        {pattern: /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/, message: '请输入正确的手机号', trigger: 'blur'}]">
                        <el-input v-model="userForm.phone" placeholder="请输入手机号"></el-input>
                    </el-form-item>
                    <el-form-item v-show="operate == 0" prop="password" label="密码" :rules="[
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {pattern: /^[a-zA-Z]\w{5,17}$/, message: '以字母开头，长度在6~18之间，只能包含字母、数字和下划线', trigger: 'blur'}]">
                        <el-input v-model="userForm.password" type="password" placeholder="请输入密码"></el-input>
                    </el-form-item>
                    <el-form-item v-show="operate == 0" prop="rePassword" label="确认密码" :rules="[{validator: validatePass, trigger: 'blur'}]">
                        <el-input v-model="userForm.rePassword" type="password" placeholder="请输入确认密码"></el-input>
                    </el-form-item>
                    <el-form-item prop="birthday" label="出生日期">
                        <el-date-picker v-model="userForm.birthday" type="date" placeholder="选择日期" style="width: 100%;"></el-date-picker>
                    </el-form-item>
                    <el-form-item prop="email" label="邮箱地址">
                        <el-input v-model="userForm.email" placeholder="请输入邮箱地址"></el-input>
                    </el-form-item>
                    <el-form-item prop="address" label="地址">
                        <el-input v-model="userForm.address" placeholder="请输入地址"></el-input>
                    </el-form-item>
                </el-form>
                <template #footer>
                    <span class="dialog-footer">
                    <el-button @click="userVisible = false">取消</el-button>
                    <el-button type="primary" @click="onSubmit">提交</el-button>
                    </span>
                </template>
            </el-dialog>
            <el-dialog title="菜单权限" v-model="menuVisible" :show-close="false" width="30%">
                <el-tree :data="menuData" show-checkbox node-key="id" ref="menuTree" 
                    :default-checked-keys="checkedKeys"
                    :props="{children: 'children', label: 'chineseName'}"
                    empty-text="暂无菜单">
                </el-tree>
                <template #footer>
                    <span class="dialog-footer">
                        <el-button @click="menuVisible = false">取消</el-button>
                        <el-button type="primary" @click="onMenuSubmit" :loading="menuLoading">提交</el-button>
                    </span>
                </template>
            </el-dialog>
        </el-card>
    </div>
</template>
<script setup>
    import {onMounted, reactive, ref, unref, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from 'element-plus'
    import Table from '@/components/common/Table.vue'
    import {dateFormat,isMobile,getCheckTreeIds}from '@/utils/utils'
    import {userPage, addUser, updateUser, delUser, menuList, userMenuSave} from '@/api/systemApi'

    const userRuleForm = ref(null)
    const menuTree = ref(null)
    const state = reactive({
        mobile: false,
        searchForm:{
            nickname: null,
        },
        loading: false,
        page: {
            current: 1,
            size: 10,
            total: 1,
        },
        tableTitle: [
            {prop:'',label:'',fixed:'left'},
            {prop:'username',label:'名称'},
            {prop:'nickname',label:'昵称'},
            {prop:'type',alias:'typeName',label:'类型'},
            {prop:'gender',alias:'genderName',label:'性别'},
            {prop:'birthday',label:'出生日期'},
            {prop:'address',label:'地址'},
            {prop:'phone',label:'手机号'},
            {prop:'openId',label:'微信唯一标识'},
            {prop:'email',label:'邮箱'},
            {prop:'updateTime',label:'更新时间'},
            {prop:'createTime',label:'创建时间'},
            {prop:'remarks',label:'备注'}
        ],
        tableData:[],
        userVisible: false,
        userTitle: '添加用户',
        operate: 0,
        userForm:{
            id:'',
            username:'',
            nickname:'',
            type:'',
            gender:'',
            birthday:'',
            address:'',
            phone:'',
            email:'',
            password:'',
            rePassword:''
        },
        menuData: [],
        menuLoading: false,
        menuVisible: false,
        menuForm: {
            userId: ''
        },
        checkedKeys:[],
    })

    const {
        mobile,searchForm,loading,page,tableHeight,tableTitle,tableData,
        userVisible,userTitle,operate,userForm,menuData,menuVisible,menuForm,menuLoading,checkedKeys
    } = toRefs(state)

    onMounted(() => {
        if(isMobile()){
            state.mobile = true;
        }
        onUserPage();
    })

    // 初始化数据
    const onUserPage = () =>{
        state.loading = true;
        userPage(Object.assign(state.page,state.searchForm)).then(res=>{
            state.loading = false;
            state.tableData = res.result.records;
            state.page.current = res.result.current;
            state.page.size = res.result.size;
            state.page.total = res.result.total;
        })
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onUserPage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onUserPage();
    }
    const onRefresh = () =>{
        state.searchForm = {
            nickname: null,
        }
        onUserPage();
    }
    const onSearch = () =>{
        onUserPage();
    }

    // 菜单权限
    const onRole = (val) =>{
        state.menuData = [];
        state.checkedKeys = [];
        menuList({userId: val.column.row.id,status: 1, types: '0,1'}).then(res=>{
            if(res){
                state.menuData = res.result;
                state.checkedKeys = getCheckTreeIds(res.result);
                state.menuForm.userId = val.column.row.id;
                state.menuVisible = true;
            }
        })
    }

    const onMenuSubmit = () =>{
        const keys = unref(menuTree);
        if(keys.getCheckedKeys().length == 0){
            ElMessage.error("请先选择菜单！");
            return false;
        }
        state.menuLoading = true;
        let param = {
            userId: state.menuForm.userId,
            menuIds: keys.getCheckedKeys()
        }
        userMenuSave(param).then(res=>{
            if(res){
                ElMessage.success("操作成功！");
                state.menuLoading = false;
                state.menuVisible = false;
            }
        })
    }

    // 编辑
    const onEdit = (val) =>{
        state.userTitle = "修改用户";
        state.operate = 1;
        state.userForm = {
            id: val.column.row.id,
            username: val.column.row.username,
            nickname: val.column.row.nickname,
            type: Number(val.column.row.type),
            gender: Number(val.column.row.gender),
            birthday: val.column.row.birthday,
            address: val.column.row.address,
            phone: val.column.row.phone,
            email: val.column.row.email,
            password: 'p123456',
            rePassword: 'p123456',
        }
        state.userVisible = true;
    }

    // 删除
    const onDelete = (val) =>{
        ElMessageBox.confirm('此操作将永久删除该文件, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            let param = {
                id: val.column.row.id
            }
            delUser(param).then(res=>{
                if(res){
                    ElMessage.success('删除成功!');
                    onUserPage();
                }
            })
        }).catch(e=>{
            console.log(e)
        })
    }

    // 添加用户信息
    const onAddUser = () =>{
        state.userTitle = "添加用户";
        state.operate = 0;
        state.userForm = {
            id:'',
            username:'',
            nickname:'',
            type:'',
            gender:'',
            birthday:'',
            address:'',
            phone:'',
            email:'',
            password:'',
            rePassword:''
        }
        state.userVisible = true;
    }

    const validatePass = (rule, value, callback) => {
        if (value === '') {
            callback(new Error('请输入确认密码'));
        } else if (value !== state.userForm.password) {
            callback(new Error('两次输入密码不一致!'));
        } else {
            callback();
        }
    }

    const onSubmit = async () =>{
        const form = unref(userRuleForm);
        if (!form) return;
        await form.validate();
        if(state.userForm.birthday instanceof Date){
            state.userForm.birthday = dateFormat(state.userForm.birthday);
        }

        if(state.operate == 0){
            addUser(state.userForm).then(res=>{
                if(res){
                    ElMessage.success({
                        message: '添加成功！',
                        type: 'success'
                    });
                    onUserPage();
                    state.userVisible = false;
                }
            });
        } else {
            state.userForm.password = null;
            updateUser(state.userForm).then(res=>{
                if(res){
                    ElMessage.success({
                        message: '修改成功！',
                        type: 'success'
                    });
                    onUserPage();
                    state.userVisible = false;
                }
            });
        }
    }
</script>
<style lang="scss" scoped>
</style>