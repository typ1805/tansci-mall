<template>
	<div class="login" :style="loginStyle">
		<div class="login-header">
			<div>
				<el-image :src="logo"  style="width: 100%; height: 100%;"></el-image>
			</div>
			<div>
				<span class="title">Tansci 商城</span>
			</div>
		</div>
		<div class="login-main">
			<div class="main-title">帐号登录</div>
			<div class="main-container">
				<div class="logo">
					<el-image :src="loginLogo"  style="width: 100%; height: 100%;"></el-image>
				</div>
				<div class="form">
					<el-form :model="loginForm" :rules="rules" size="large" ref="loginRuleForm">
						<el-form-item prop="username" :rules="[
								{required: true,message: '请输入用户名',trigger: 'blur'},
								{pattern: /^[a-zA-Z]\w{4,17}$/,message: '用户名式有误，请重新输入',trigger: 'blur'}]">
							<el-input v-model="state.loginForm.username" prefix-icon="Avatar" placeholder="账号" style="width:100%"></el-input>
						</el-form-item>
						<el-form-item prop="password" :rules="[
								{required: true,message: '请输入密码',trigger: 'blur'},
								{pattern: /^[a-zA-Z]\w{5,17}$/,message: '密码格式有误，请重新输入',trigger: 'blur'}]">
							<el-input type="password" v-model="loginForm.password" prefix-icon="Lock" show-password placeholder="密码" style="width:100%"></el-input>
						</el-form-item>
						<el-form-item prop="verifyStatus" :rules="[{required: true,message: '请拖动滑块验证',trigger: 'blur'}]">
							<SlidingVerify ref="slidingVerify" :status="loginForm.verifyStatus" :successFun="onVerifySuccess" :errorFun="onVerifyError"></SlidingVerify>
						</el-form-item>
						<el-form-item>
							<el-checkbox v-model="loginForm.keepPassword" label="记住密码"></el-checkbox>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" @click="submit" :loading="loading" style="width:100%">登录</el-button>
						</el-form-item>
					</el-form>
				</div>
			</div>
		</div>
		<div class="login-footer">
			<div>
				<el-link target="_blank">Tansci 帐号用户协议</el-link>
				<el-divider direction="vertical" />
				<el-link target="_blank">关于 Tansci 帐号与隐私的声明</el-link>
				<el-divider direction="vertical" />
				<el-link target="_blank">常见问题</el-link>
			</div>
			<div class="copy">
				Tansci Mall 版权所有 &copy; 2022- {{copyYear()}}
			</div>
		</div>
	</div>
</template>
<script setup>
	import {onBeforeMount,onMounted,reactive,ref,toRefs,unref} from "vue"
	import {useRouter} from 'vue-router'
	import SlidingVerify from '@/components/common/SlidingVerify.vue'
	import {useUserStore, useTokenStore} from '@/store/settings'
	import {login} from '@/api/admin/user'

	const userStore = useUserStore()
	const tokenStore = useTokenStore()
	const router = useRouter()
	let loginRuleForm = ref(null) 
	let slidingVerify = ref()
	const logo = new URL('../../assets/image/logo.png', import.meta.url).href
	const loginLogo = new URL('../../assets/image/login-logo.svg', import.meta.url).href

	const state = reactive({
		loginStyle: {
			height: '',
		},
		loading: false,
		loginForm: {
			username: '',
			password: '',
			verifyStatus: null,
			keepPassword: null,
		}
	})
	const {loginStyle,loading,loginForm} = toRefs(state)

	onMounted(()=>{
	})

	onBeforeMount(() => {
		state.loginStyle.height = (document.body.clientHeight || document.documentElement.clientHeight) + "px"
	})

	const onVerifySuccess = (e) =>{
		state.loginForm.verifyStatus = e;
	}
	const onVerifyError = (e) =>{
		state.loginForm.verifyStatus = e;
	}

	const submit = async () => {
		const form = unref(loginRuleForm)
		if (!form) return;
		await form.validate();

		let param = {
			username: state.loginForm.username,
			password: state.loginForm.password,
			type: 1
		}

		state.loading = true;
		login(param).then(res=>{
			if(res){
				// 存储用户信息和token
				userStore.setUser(res.result);
				tokenStore.setToken(res.result.token);
				state.loading = false;
				router.push({path: 'main'});
			}
		}).catch(()=>{
			state.loginForm.verifyStatus = null;
			slidingVerify.value.onRefresh()
			state.loading = false;
		})
	}

	function copyYear(){
		let date = new Date();
		return date.getFullYear();
	}
</script>
<style lang="scss" scoped="scoped">
	.login {
		.login-header{
			display: flex;
			height: 4rem;
			padding: 0 20%;
			line-height: 4rem;
			background: #f1f1f1;
			box-shadow: rgba(0, 0, 0, 0.45) 0px 25px 20px -20px;
			.title{
				padding: 0 1rem;
				color: var(--t9);
				font-size: 18px;
				font-weight: 700;
			}
		}
		.login-main{
			height: 80%;
			background-image: linear-gradient(240deg, #c7f6da 0%, #FAFDFE 50%, #f3f3f3 100%);
			.main-title{
				font-size: 32px;
				text-align: center;
				padding: 7rem 0;
			}
			.main-container{
				display: flex;
				flex-wrap: wrap;
				justify-content: center;
				align-items: center;
				.logo{
					width: 20rem;
					padding-right: 4rem;
				}
				.form{
					width: 20rem;
					padding-left: 4rem;
					border-left: 1px solid #dcdfe6;
				}
			}
		}
		.login-footer{
			box-shadow: rgba(0, 0, 0, 0.06) 0px 2px 4px 0px inset;
			height: 100%;
			background: #f1f1f1;
			text-align: center;
			padding: 2rem 0;
			color: #606266;
			.copy{
				padding-top: 1rem;
			}
		}
	}
</style>
