<template>
    <div class="account-settings">
        <div class="account-settings-header">
            <el-page-header @back="toBack" icon="ArrowLeft" title="返回">
                <template #content>
                    <span class="content">账户设置</span>
                </template>
            </el-page-header>
        </div>
        <div class="account-settings-main">
            <el-scrollbar :height="defaultHeight">
                <el-card :shadow="shadow">
                    <el-form :model="userForm" :rules="rules" ref="userFormRef" label-width="80px">
						<el-form-item prop="username" label="用户名称" :rules="[{required: true,message: '请输入用户名',trigger: 'blur'}]">
							<el-input v-model="userForm.username" disabled></el-input>
						</el-form-item>
                        <el-divider/>
                        <el-form-item prop="nickname" label="用户昵称" :rules="[{required: true, message: '请输入昵称', trigger: 'blur'}]">
                            <el-input v-model="userForm.nickname" placeholder="请输入昵称"></el-input>
                        </el-form-item>
                        <el-divider/>
                        <el-form-item prop="phone" label="联系方式" :rules="[
                            {required: true, message: '请输入手机号', trigger: 'blur'},
                            {pattern: /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/, message: '请输入正确的手机号', trigger: 'blur'}]">
                            <el-input v-model="userForm.phone" placeholder="请输入手机号"></el-input>
                        </el-form-item>
                        <el-divider/>
                        <el-form-item prop="idCard" label="身份证号" :rules="[
                            {required: true, message: '请输入身份证号码', trigger: 'blur'},
                            {pattern: /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/, message: '请输入正确的身份证号码', trigger: 'blur'}]">
                            <el-input v-model="userForm.idCard" type="password" show-password placeholder="请输入身份证号码"></el-input>
                        </el-form-item>
                        <el-divider/>
                        <el-form-item prop="email" label="邮箱地址" :rules="[
                            {pattern: /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.(com|cn|net)$/, message: '请输入正确的邮箱地址', trigger: 'blur'}]">
                            <el-input v-model="userForm.email" placeholder="请输入邮箱地址"></el-input>
                        </el-form-item>
                        <el-divider/>
                        <el-form-item prop="birthday" label="出生日期">
                            <el-date-picker v-model="userForm.birthday" type="date" placeholder="选择日期" style="width: 100%;"></el-date-picker>
                        </el-form-item>
                        <el-divider/>
                        <el-form-item prop="gender" label="性别" :rules="[{required: true, message: '请选择性别', trigger: 'change'}]">
                            <el-radio-group v-model="userForm.gender">
                                <el-radio :label="0">男</el-radio>
                                <el-radio :label="1">女</el-radio>
                            </el-radio-group>
                        </el-form-item>
                        <el-divider/>
						<div style="padding-top: 0.4rem;">
                            <el-button @click="onSubmit" round style="width: 100%;">保存</el-button>
                        </div>
					</el-form>
                </el-card>
                <div style="margin: 4rem 0.2rem 0 0.2rem;">
                    <el-button @click="onLogout" type="danger" size="large" round style="width: 100%;">退出</el-button>
                </div>
            </el-scrollbar>
        </div>
    </div>
</template>
<script setup>
    import {onBeforeMount, onMounted, reactive, ref, unref, toRefs} from 'vue'
    import {useRouter} from 'vue-router'

    const router = useRouter()
    const userFormRef = ref(null)
    const state = reactive({
        shadow: 'never',
        defaultHeight: null,
        userForm: {
            id:'',
            username:'',
            nickname:'',
            gender:'',
            birthday:'',
            phone:'',
            idCard:'',
            email:'',
        },
    })

    const {
        shadow,defaultHeight,userForm
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 40 + "px";
    })

    onMounted(()=>{
        onAccountSettings();
    })

    const onAccountSettings = () =>{
        // session 中获取用户信息

        state.userForm = {
            id: 'u1001',
            username: 'tansci',
            nickname: '小平',
            gender: 1,
            birthday: '2008-05-12',
            phone: '18893814526',
            idCard: '12154124745787154',
            email: 'tansci@qq.com',
        }
    }

    const onSubmit = async () =>{
        const form = unref(userFormRef);
        if (!form) return;
        await form.validate();
        if(state.userForm.birthday instanceof Date){
            state.userForm.birthday = dateFormat(state.userForm.birthday);
        }

        // updateUser(state.userForm).then(res=>{
        //     if(res){
        //         ElMessage.success({
        //             message: '设置成功！',
        //             type: 'success'
        //         });
        //        toBack();
        //     }
        // });
    }

    const onLogout = () =>{

    }

    const toBack = () =>{
        router.go(-1)
    }

</script>
<style lang="scss">
    .account-settings{
        .account-settings-header{
            padding: 0.4rem 0;
            .content{
                font-size: 16px;
                padding-left: 5rem;
            }
        }
        .account-settings-main{
            .el-scrollbar{
                background: #FAFAFA;
                padding: 0.4rem 0.2rem;
            }
            .el-card{
                margin-bottom: 0.4rem;
                border-radius: 8px;
            }

            .el-input__wrapper{
                border: none !important;
                box-shadow: none !important;
            }
            .el-input__inner{
                border: none !important;
                box-shadow: none !important;
                width: 100%;
            }
            .el-input.is-disabled .el-input__wrapper{
                background: #ffffff !important;
            }
            .el-divider--horizontal{
                margin: 4px 0;
            }
        }
    }
</style>

