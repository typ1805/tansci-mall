<template>
    <el-container class="layout-container">
        <el-aside :style="{height: defaultHeight+'px'}" :width="asideWidth" style="padding-bottom:4rem;">
            <el-card v-show="!isCollapse" shadow="always">
                <div>
                    <el-icon :size="26" style="vertical-align: middle;"><OfficeBuilding/></el-icon>
                    <span style="vertical-align: middle;padding-left:1rem;">TANSCI商城</span>
                </div>
            </el-card>
            <el-menu router :default-active="$route.path" :collapse="isCollapse"
                text-color="#242e42" active-text-color="#2F9688" background-color="var(--bg1)">
                <template v-for="item in routers" :key="item">
                    <el-menu-item v-if="!item.children || item.children.length == 1" :index="item.path">
                        <el-icon v-if="item.icon" style="vertical-align: middle;">
                            <component :is="item.icon"></component>
                        </el-icon>
                        <span style="vertical-align: middle;">{{item.chineseName}}</span>
                    </el-menu-item>
                    <Submenu v-else :data="item"></Submenu>
                </template>
            </el-menu>
        </el-aside>
        <el-container>
            <el-header height="60">
                <div class="header">
                    <div style="padding-left: 0.4rem;">
                        <el-icon @click="onCollapse" :size="20" style="vertical-align: middle; cursor: pointer;">
                            <component :is="isCollapse?'Expand':'Fold'"></component>
                        </el-icon>
                        <el-icon v-show="!mobile" :size="16" color="#55bc8a" style="vertical-align: middle;padding-left: 1rem;">
                            <Timer/>
                        </el-icon>
                        <span v-show="!mobile" style="padding-right: 2rem;vertical-align: middle;">{{nowTimes}}</span>
                    </div>
                    <div style="padding-right:0.4rem;">
                        <el-dropdown style="line-height: 60px;">
                            <span class="el-dropdown-link" style="color:var(--theme);">
                                <span style="cursor:pointer;vertical-align: middle;">{{username}} 欢迎您</span>
                                <el-icon style="vertical-align: middle;"><arrow-down /></el-icon>
                            </span>
                            <template #dropdown>
                                <el-dropdown-menu>
                                    <el-dropdown-item @click="onUserInfo" icon="User">个人信息</el-dropdown-item>
                                    <el-dropdown-item @click="onPassword" icon="Lock">修改密码</el-dropdown-item>
                                    <el-dropdown-item @click="onLogout" icon="SwitchButton">退出</el-dropdown-item>
                                </el-dropdown-menu>
                            </template>
                        </el-dropdown>
                        <el-dialog v-model="dialogPass" title="修改密码" :destroy-on-close="true" :show-close="false" :width="mobile?'100%':'50%'">
                            <el-form :model="passForm" :rules="rules" ref="validateForm" status-icon label-width="100px">
                                <el-form-item label="原始密码" prop="oldPassword" :rules="[
                                    {required: true, message: '请输入原始密码', trigger: 'blur'},
                                    {pattern: /^[a-zA-Z]\w{5,17}$/, message: '原始密码格式有误，请重新输入', trigger: 'blur'}]">
                                    <el-input class="input-size" type="password" v-model="passForm.oldPassword" prefix-icon="Lock" autocomplete="off" style="width:100%"/>
                                </el-form-item>
                                <el-form-item label="新密码" prop="password" :rules="[
                                    {required: true, message: '请输入新密码', trigger: 'blur'},
                                    {pattern: /^[a-zA-Z]\w{5,17}$/, message: '以字母开头，长度在6~18之间，只能包含字母、数字和下划线', trigger: 'blur'}]">
                                    <el-input class="input-size" type="password" v-model="passForm.password" prefix-icon="Lock" autocomplete="off" style="width:100%"/>
                                </el-form-item>
                                <el-form-item label="确认密码" prop="rePassword" :rules="[{validator: validatePass, trigger: 'blur'}]">
                                    <el-input class="input-size" type="password" v-model="passForm.rePassword" prefix-icon="Lock" autocomplete="off" style="width:100%"/>
                                </el-form-item>
                            </el-form>
                            <template #footer>
                                <span class="dialog-footer">
                                    <el-button @click="dialogPass = false">取消</el-button>
                                    <el-button type="primary" @click="onPassSubmit">立即修改</el-button>
                                </span>
                            </template>
                        </el-dialog>
                        <el-drawer v-model="userDrawer" :with-header="false">
                            <el-descriptions title="个人信息" :column="1" border>
                                <el-descriptions-item label="用户名称" label-align="right">{{userInfo.username}}</el-descriptions-item>
                                <el-descriptions-item label="用户昵称" label-align="right">{{userInfo.nickname}}</el-descriptions-item>
                                <el-descriptions-item label="类型" label-align="right">{{userInfo.type==1?'管理员':'商户'}}</el-descriptions-item>
                                <el-descriptions-item label="性别" label-align="right">{{userInfo.gender==0?'男':'女'}}</el-descriptions-item>
                                <el-descriptions-item label="手机号" label-align="right">{{userInfo.phone}}</el-descriptions-item>
                                <el-descriptions-item label="出生日期" label-align="right">{{userInfo.birthday}}</el-descriptions-item>
                                <el-descriptions-item label="邮件" label-align="right">{{userInfo.email}}</el-descriptions-item>
                                <el-descriptions-item label="地址" label-align="right">{{userInfo.address}}</el-descriptions-item>
                                <el-descriptions-item label="注册时间" label-align="right">{{userInfo.createTime}}</el-descriptions-item>
                            </el-descriptions>
                        </el-drawer>
                    </div>
                </div>
                <TabsMenu></TabsMenu>
            </el-header>
            <el-main :style="{height: defaultHeight+'px'}">
                <router-view style="margin: 0.4rem;"/>
            </el-main>
        </el-container>
        <el-backtop target=".el-main"></el-backtop>
    </el-container>
</template>
<script setup>
    import {onBeforeMount, onMounted, onDeactivated, reactive, ref, unref, toRefs} from 'vue'
    import {ElMessageBox} from 'element-plus'
    import {useRouter} from 'vue-router'
    import Submenu from "@/components/common/Submenu.vue"
    import TabsMenu from "@/components/common/TabsMenu.vue"
    import {useUserStore, useTokenStore, useMenuStore} from '@/store/settings'
    import {timeFormate, isMobile} from '@/utils/utils.js'
    import {modifyPass,logout, qryByUserName} from '@/api/admin/user'

    const router = useRouter()
    const userStore = useUserStore();
    const tokenStore = useTokenStore();
    const menuStore = useMenuStore();
    const username = userStore.getUser.username == null ? '未登录':userStore.getUser.username;
    const nowTimes = ref('');
    const validateForm = ref(null)
    const state = reactive({
        mobile: false,
        isCollapse: false,
        asideWidth: '240px',
        defaultHeight: null,
        routers: [],
        dialogPass: false,
        passForm: {
            oldPassword: '',
            password: '',
            rePassword: ''
        },
        userDrawer: false,
        userInfo: {}
    })

    const {
        mobile,isCollapse,asideWidth,defaultHeight,routers,dialogPass,passForm,userDrawer,userInfo
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight);
    })

    onMounted(()=>{
        if(isMobile()){
            state.mobile = true;
            onCollapse();
        }
        // 获取菜单
        let routers = [];
        let _routes = menuStore.getMenu;
        _routes.forEach(item=>{
            if (item.type === 1){
                routers.push(item)
            }
        })
        state.routers = routers;

        onNowTimes();

        window.addEventListener('resize', onDefaultHeight);
    })

    onDeactivated(()=>{
        window.removeEventListener('resize', onDefaultHeight, false)
    })

    const onDefaultHeight = () =>{
        state.defaultHeight = window.innerHeight
    }

    const onCollapse = () => {
        if (state.isCollapse) {
            state.asideWidth = '240px'
            state.isCollapse = false
        } else {
            state.isCollapse = true
            state.asideWidth = '64px'
        }
    }

    // 退出
    const onLogout = () =>{
        ElMessageBox.confirm('您确定要退出吗？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            logout({}).then(res=>{
                if(res){
                    // 清除相关缓存信息
                    userStore.delUser();
                    tokenStore.delToken();
                    menuStore.delMenu();
                    router.push({path: 'login'})
                }
            });
        }).catch(e=>{
            console.log(e)
        })
    }

    // 当前时间
    const onNowTimes = () =>{
        setInterval(()=>{
            nowTimes.value = timeFormate(new Date());
        },1000);
    }

    // 修改密码
    const onPassword = () =>{
        state.passForm = {
            oldPassword: '',
            password: '',
            rePassword: ''
        }
        state.dialogPass = true;
    }
    const validatePass = (rule, value, callback) => {
        if (value === '') {
            callback(new Error('请再次输入密码'));
        } else if (value !== state.passForm.password) {
            callback(new Error('两次输入密码不一致'))
        } else {
            callback()
        }
    }
    const onPassSubmit = async () => {
        const form = unref(validateForm)
        if (!form) return;
        await form.validate();
        state.passForm.username = store.getters.getUser.username;
        modifyPass(state.passForm).then(res=>{
            if(res){
                ElMessageBox.alert('修改密码成功，需重新登录！', '提示', {
                    confirmButtonText: '确定',
                    type: 'warning',
                    callback: action => {
                        if ('confirm' == action) {
                            router.push({path: 'login'});
                        }
                    }
                })
            }
        });
    }

    const onUserInfo = () =>{
        if(userStore.getUser.username){
            qryByUserName({username: userStore.getUser.username}).then(res=>{
                state.userInfo = res.result
                state.userDrawer = true
            })
        }
    }

</script>
<style lang="scss">
    .layout-container{
        .el-header{
            padding: 0;
            
            .header{
                display: flex;
                justify-content: space-between;
                line-height: 60px;
                color: var(--theme);
                background: var(--bg1);
                // border:1px transparent solid;
                // border-image:linear-gradient(to right,var(--bg1),#DCDFE6,var(--bg1)) 1 10;
                // box-shadow: 0 4px 8px 0 rgba(36,46,66,.06)!important;
                
                .el-dialog__header{
                    background: var(--theme);
                    padding: 0 10px;
                    .el-dialog__title{
                        color: var(--theme);
                    }
                }
            }
        }

        .el-aside{
            height: 100%;
            transition: all .5s;
            background: var(--bg1);
            overflow-y: auto;
            overflow-x: hidden;
            .el-menu{
                border: none;
                .el-menu-item, .el-sub-menu__title{
                    height: 40px;
                    line-height: 40px;
                }
                .el-sub-menu__title:hover{
                    background: var(--bg1) !important;
                }
                .el-menu-item:hover{
                    background: var(--bg1) !important;
                }
            }
            .el-card{
                margin: 0.4rem 0.6rem;
                background-color: var(--theme);
                color:#fff;
                .el-card__body{
                    padding: 1rem 2rem;
                }
            }
        }
        .el-aside::-webkit-scrollbar{
            width: 0px;
        }
        .el-main{
            padding: 0;
            overflow-x: hidden;
            overflow-y: auto;
            background: var(--bg1);
            .el-dialog__header{
                background: var(--theme);
                padding: 10px;
                margin: 0;
                .el-dialog__title{
                    color: #fff;
                }
            }
        }
        .el-main::-webkit-scrollbar{
            width: 0px;
        }
    }
</style>