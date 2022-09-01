<template>
    <div class="login-container">
        <div class="login-header">
            <div>账号登录</div>
        </div>
        <div class="login-main">
            <el-card :shadow="shadow" :body-style="{padding: '3rem'}">
                <el-form :model="userForm" :rules="rules" label-width="auto" ref="userFormRef">
                    <el-form-item prop="username" label="账号" :rules="[{required: true,message: '请输入账号',trigger: 'blur'}]">
                        <el-input v-model="userForm.username" placeholder="请输入账号" style="width: 100%;"></el-input>
                    </el-form-item>
                    <el-form-item prop="password" label="密码" :rules="[{required: true,message: '请输入密码',trigger: 'blur'}]">
                        <el-input v-model="userForm.password" type="password" placeholder="请输入密码" style="width: 100%;"></el-input>
                    </el-form-item>
                    <div style="padding-bottom: 2rem">
                        <el-checkbox v-model="userForm.remember" label="记住密码"/>
                    </div>
                    <div style="display: flex;justify-content: space-between;padding-bottom: 2rem">
                        <el-button type="primary" link>忘记密码？</el-button>
                        <el-button @click="onRegister" type="primary" link>注册账号</el-button>
                    </div>
                    <div style="padding-bottom: 2rem">
                        <el-button @click="onSubmit" :loading="loading" type="primary" size="large" round style="width: 100%;">登录</el-button>
                    </div>
                </el-form>
            </el-card>
        </div>
    </div>
</template>
<script setup>
    import {reactive, ref, unref, toRefs} from 'vue'
    import {useRouter} from 'vue-router'
    import {useUserStore,useTokenStore} from '@/store/settings'
    import {login} from '@/api/admin/user'

    const userStore = useUserStore()
	const tokenStore = useTokenStore()
    const router = useRouter()
    const userFormRef = ref(null)
    const state = reactive({
        shadow: 'always',
        loading: false,
        userForm: {
            username: '',
            password: '',
            remember: '',
        },
    })

    const {
        shadow,loading,userForm
    } = toRefs(state)

    const onSubmit = async () =>{
        const form = unref(userFormRef)
        if (!form) return;
        await form.validate()

        let param = {
			username: state.userForm.username,
			password: state.userForm.password,
            type: 2
		}

		state.loading = true;
		login(param).then(res=>{
			if(res){
				// 存储用户信息和token
				userStore.setUser(res.result);
				tokenStore.setToken(res.result.token);
				state.loading = false;
				router.push({path: '/app/index'});
			}
		}).catch(()=>{
			state.loading = false;
		})
    }

    const onRegister = () =>{
        router.push({path:'/app/appRegister'})
    }

</script>
<style lang="scss">
    .login-container{
        height: 100%;
        .login-header{
            text-align: center;
            color: #fff;
            font-size: 20px;
            padding: 4rem 0;
            background: var(--theme);
        }
        .login-main{
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