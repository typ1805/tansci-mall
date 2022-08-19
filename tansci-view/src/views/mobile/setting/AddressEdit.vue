<template>
    <div class="address-edit">
        <div class="address-edit-header">
            <el-page-header @back="toBack" icon="ArrowLeft" title="返回">
                <template #content>
                    <span class="content">地址管理</span>
                </template>
            </el-page-header>
        </div>
        <div class="address-edit-main">
            <el-scrollbar :height="defaultHeight">
                <el-card v-for="item in addressList" :key="item" :shadow="shadow">
                    <div style="padding-bottom: 0.6rem; display: flex; justify-content: space-between; align-items: center;">
                        <div>
                            <span style="font-weight: 700;padding-right: 0.4rem;">{{item.name}} {{item.phone}}</span>
                            <el-tag v-if="item.flag == 1" size="small" type="danger" round effect="dark">默认</el-tag>
                        </div>
                        <div @click="onAddressEdit(item.id)"><el-icon><EditPen /></el-icon></div>
                    </div>
                    <div>{{item.details}}</div>
                </el-card>
            </el-scrollbar>
            <el-drawer v-model="addressDrawer" size="80%" :with-header="false" direction="btt">
                <el-form :model="addressForm" :rules="rules" ref="addressFormRef" label-width="80px">
                    <el-form-item prop="name" label="姓名" :rules="[{required: true,message: '请输入姓名',trigger: 'blur'}]">
                        <el-input v-model="addressForm.name" placeholder="请输入姓名"></el-input>
                    </el-form-item>
                    <el-divider/>
                    <el-form-item prop="phone" label="电话" :rules="[
                        {required: true, message: '请输入手机号', trigger: 'blur'},
                        {pattern: /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/, message: '请输入正确的手机号', trigger: 'blur'}]">
                        <el-input v-model="addressForm.phone" placeholder="请输入手机号"></el-input>
                    </el-form-item>
                    <el-divider/>
                    <el-form-item prop="area" label="地区" :rules="[{required: true, message: '请选择地区', trigger: 'change'}]">
                        <el-tree-select @node-click="onNodeCheck" v-model="addressForm.area" :data="areaList" placeholder="请选择地区"  accordion highlight-current ref="treeRef"/>
                    </el-form-item>
                    <el-divider/>
                    <el-form-item prop="details" label="地址" :rules="[{required: true, message: '请输入详细地址', trigger: 'blur'}]">
                        <el-input v-model="addressForm.details" placeholder="请输入详细地址"></el-input>
                    </el-form-item>
                    <el-divider/>
                    <div class="address">
                        <el-alert title="收货地址" type="warning" :closable="false">
                            <template #default>
                                <div>{{addressForm.name}}  {{addressForm.phone}}</div>
                                <div>{{addressForm.province + addressForm.city + addressForm.region}} - {{addressForm.details}}</div>
                            </template>
                        </el-alert>
                    </div>
                    <div style="padding: 2rem 0;">
                        <el-button @click="onSubmit" type="danger" size="large" round style="width: 100%;">保存</el-button>
                    </div>
                </el-form>
            </el-drawer>
        </div>
        <div class="address-edit-footer">
            <el-button @click="onAddressAdd" type="danger" round style="width:90%;">新增地址</el-button>
        </div>
    </div>
</template>
<script setup>
    import {onBeforeMount, onMounted, reactive, ref, unref, toRefs} from 'vue'
    import {useRouter} from 'vue-router'
    import {useUserStore} from '@/store/settings'
    import areaArr from '@/utils/area'
    import {getTreeFindPath} from '@/utils/utils'
    import {userAddressList,addUserAddress,updUserAddress} from '@/api/admin/userAddress'

    const router = useRouter()
    const userStore = useUserStore()
    const addressFormRef = ref(null)
    const treeRef = ref(null)
    const state = reactive({
        shadow: 'never',
        defaultHeight: null,
        areaList: areaArr,
        addressList: [],
        addressDrawer: false,
        addressForm: {
            id: '',
            userId: '',
            name: '',
            phone: '',
            province: '',
            city: '',
            region: '',
            details: '',
            area: '',
        },
    })

    const {
        shadow,defaultHeight,areaList,addressList,addressDrawer,addressForm
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 45 + "px";
    })

    onMounted(()=>{
        onAddress();
    })

    const onAddress = () =>{
        const user = userStore.getUser.user;
        if(!user || !user.username){
            return;
        }
        userAddressList({username:user.username}).then(res=>{
            state.addressList = res.result
        })
    }

    const onAddressEdit = (id) =>{
        let address = state.addressList.find(item=> {return item.id == id});
        state.addressForm = {
            id: address.id,
            userId: address.userId,
            name: address.name,
            phone: address.phone,
            province: address.province,
            city: address.city,
            region: address.region,
            details: address.details,
            area: address.region,
        }
        state.addressDrawer = true;
    }

    const onAddressAdd = () =>{
        state.addressForm = {
            id: '',
            userId: state.addressList[0].userId,
            name: '',
            phone: '',
            province: '',
            city: '',
            region: '',
            details: '',
            area: '',
        }
        state.addressDrawer = true;
    }

    const onNodeCheck = (val,event) =>{
        let nodes = getTreeFindPath(state.areaList, data=> data.value === val.value);

        let province = nodes.find(item=>{return item.level == 0});
        state.addressForm.province = province?province.label:'';

        let city = nodes.find(item=>{return item.level == 1});
        state.addressForm.city = city?city.label:'';

        let region = nodes.find(item=>{return item.level == 2});
        state.addressForm.region = region?region.label:'';
    }

    const onSubmit = async () =>{
        const form = unref(addressFormRef);
        if (!form) return;
        await form.validate();

        if(state.addressForm.id){
            updUserAddress({
                id: state.addressForm.id,
                userId: state.addressForm.userId,
                name: state.addressForm.name,
                phone: state.addressForm.phone,
                province: state.addressForm.province,
                city: state.addressForm.city,
                region: state.addressForm.region,
                details: state.addressForm.details,
            }).then(res=>{
                if(res){
                    state.addressDrawer = false;
                    onAddress();
                }
            })
        } else {
            addUserAddress({
                id: state.addressForm.id,
                userId: state.addressForm.userId,
                name: state.addressForm.name,
                phone: state.addressForm.phone,
                province: state.addressForm.province,
                city: state.addressForm.city,
                region: state.addressForm.region,
                details: state.addressForm.details,
                flag: 0,
            }).then(res=>{
                if(res){
                    state.addressDrawer = false;
                    onAddress();
                }
            })
        }
    }

    const toBack = () =>{
        router.go(-1)
    }

</script>
<style lang="scss">
    .address-edit{
        .address-edit-header{
            padding: 0.4rem 0;
            .content{
                font-size: 16px;
                padding-left: 5rem;
            }
        }
        .address-edit-main{
            .el-scrollbar{
                background: #FAFAFA;
                padding: 0.4rem 0.2rem;
            }
            .el-card{
                margin-bottom: 0.4rem;
                border-radius: 8px;
            }
            .address{
                padding: 1rem 0.4rem;
                font-size: 12px;
                line-height: 20px;
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
            .el-select .el-input.is-focus .el-input__wrapper{
                border: none !important;
                box-shadow: none !important;
            }
            .el-input.is-disabled .el-input__wrapper{
                background: #ffffff !important;
            }
            .el-input__suffix{
                display: none !important;
            }
            .el-divider--horizontal{
                margin: 4px 0;
            }
        }
        .address-edit-footer{
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
            padding: 0.4rem;
            text-align: center;
        }
    }
</style>

