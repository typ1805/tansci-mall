<template>
    <div class="login-container" :style="defaultStyle">
        <div class="login-main">
            <el-card :shadow="shadow" :body-style="{padding: '2rem 3rem'}">
                <div class="main-title">登录</div>
                <el-form :model="userForm" :rules="rules" label-width="auto" ref="userFormRef">
                    <el-form-item prop="username" :rules="[{required: true,message: '请输入账号',trigger: 'blur'}]">
                        <template #label>
                            <el-icon :size="26" color="var(--theme)" style="padding-top: 0.2rem;"><User /></el-icon>
                        </template>
                        <el-input v-model="userForm.username" placeholder="请输入账号" style="width: 100%;"></el-input>
                    </el-form-item>
                    <el-divider />
                    <el-form-item prop="password" :rules="[{required: true,message: '请输入密码',trigger: 'blur'}]">
                        <template #label>
                            <el-icon :size="26" color="var(--theme)" style="padding-top: 0.2rem;"><Lock /></el-icon>
                        </template>
                        <el-input v-model="userForm.password" type="password" placeholder="请输入密码" show-password style="width: 100%;"></el-input>
                    </el-form-item>
                    <el-divider />
                    <div style="display: flex;justify-content: space-between;padding-bottom: 2rem">
                        <el-button @click="onRegister" type="primary" link>注册账号</el-button>
                        <el-button type="primary" link>忘记密码？</el-button>
                    </div>
                    <div style="padding-bottom: 2rem">
                        <el-button @click="onSubmit" :loading="loading" type="primary" size="large" round style="width: 100%;">登录</el-button>
                    </div>
                    <el-form-item prop="remember" :rules="[{required: true,message: '请勾选使用协议',trigger: 'blur'}]" class="item-remember">
                        <el-checkbox v-model="userForm.remember" label="同意平台的《使用协议》"/>
                    </el-form-item>
                </el-form>
            </el-card>
        </div>
    </div>
</template>
<script setup>
    import {onBeforeMount, reactive, ref, unref, toRefs} from 'vue'
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
        defaultStyle: null,
        userForm: {
            username: '',
            password: '',
            remember: null,
            type: 2,
        },
    })

    const {
        shadow,loading,defaultStyle,userForm
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultStyle = {
            height: (document.body.clientHeight || document.documentElement.clientHeight) + "px",
        };
    })

    const onSubmit = async () =>{
        const form = unref(userFormRef)
        if (!form) return;
        await form.validate()

		state.loading = true;
		login(state.userForm).then(res=>{
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
        display: flex;
        justify-content: center;
        align-items: center;
        background-image: linear-gradient(to bottom, #f3e7e9 0%, #e3eeff 99%, #e3eeff 100%);
        .login-main{
            padding: 0 3rem;
            .main-title{
                font-size: 2rem;
                padding-bottom: 2rem;
                font-weight: 700;
            }
            .item-remember{
                .el-form-item__label-wrap{
                    display: none;
                }
                .el-form-item__content{
                    padding-left: 1rem;
                }
            }
            .el-divider--horizontal{
                margin: 0 0 3rem 0;
                border-top: 2px solid #A8ABB2;
            }
            .el-form-item,.el-form-item--default{
                margin-bottom: 0.4rem;
            }
            .el-form-item__error{
                padding-top: 15px;
            }
            .el-card{
                border-radius: 2rem;
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