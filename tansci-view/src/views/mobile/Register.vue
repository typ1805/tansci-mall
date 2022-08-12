<template>
    <div class="register-container">
        <div class="register-header">
            <div>注册账号</div>
        </div>
        <div class="register-main">
            <el-card :shadow="shadow" :body-style="{padding: '3rem'}">
                <el-form :model="userForm" :rules="rules" label-position="right" label-width="auto" ref="userFormRef">
                    <el-form-item prop="username" label="账号" :rules="[{required: true,message: '请输入账号',trigger: 'blur'}]">
                        <el-input v-model="userForm.username" placeholder="请输入账号" style="width: 100%;"></el-input>
                    </el-form-item>
                    <el-form-item prop="password" label="密码" :rules="[{required: true,message: '请输入密码',trigger: 'blur'}]">
                        <el-input v-model="userForm.password" type="password" show-password placeholder="请输入密码" style="width: 100%;"></el-input>
                    </el-form-item>
                    <el-form-item prop="phone" label="手机号" :rules="[
                        {required: true, message: '请输入手机号', trigger: 'blur'},
                        {pattern: /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/, message: '请输入正确的手机号', trigger: 'blur'}]">
                        <el-input v-model="userForm.phone" placeholder="请输入手机号"></el-input>
                    </el-form-item>
                    <el-form-item prop="idCard" label="身份证" :rules="[
                        {required: true, message: '请输入身份证号码', trigger: 'blur'},
                        {pattern: /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/, message: '请输入正确的身份证号码', trigger: 'blur'}]">
                        <el-input v-model="userForm.idCard" type="password" show-password placeholder="请输入身份证号码"></el-input>
                    </el-form-item>
                    <el-form-item prop="email" label="邮箱" :rules="[
                        {required: true, message: '请输入邮箱地址', trigger: 'blur'},
                        {pattern: /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.(com|cn|net)$/, message: '请输入正确的邮箱地址', trigger: 'blur'}]">
                        <el-input v-model="userForm.email" placeholder="请输入邮箱地址"></el-input>
                    </el-form-item>
                    <div style="padding-bottom: 2rem">
                        <el-button @click="onLogin" type="primary" link>已有账号，去登录。</el-button>
                    </div>
                    <div style="padding-bottom: 1rem">
                        <el-button @click="onSubmit" type="primary" size="large" round style="width: 100%;">提交</el-button>
                    </div>
                </el-form>
            </el-card>
        </div>
    </div>
</template>
<script setup>
    import {reactive, ref, unref, toRefs} from 'vue'
    import {useRouter} from 'vue-router'

    const router = useRouter()
    const userFormRef = ref(null)
    const state = reactive({
        shadow: 'always',
        userForm: {
            username: '',
            password: '',
            id_card: '',
            phone: '',
            email: '',
        },
    })

    const {
        shadow,userForm
    } = toRefs(state)

    const onSubmit = async () =>{
        const form = unref(userFormRef)
        if (!form) return;
        await form.validate()

        console.log(state.userForm)

        // 注册成功去登录
        onLogin();
    }

    const onLogin = () =>{
        router.push({path:'/app/appLogin'})
    }

</script>
<style lang="scss">
    .register-container{
        height: 100%;
        .register-header{
            text-align: center;
            color: #fff;
            font-size: 20px;
            padding: 4rem 0;
            background: var(--theme);
        }
        .register-main{
            .el-card{
                margin: -2rem 2rem 0 2rem;
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
            .el-input.is-focus .el-input__wrapper{
                border: none !important;
                box-shadow: none !important;
            }
            .el-input.is-disabled .el-input__wrapper{
                background: #ffffff !important;
            }
            .el-input__suffix{
                display: none !important;
            }
        }
    }
</style>