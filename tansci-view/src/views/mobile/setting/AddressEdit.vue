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
                <el-card :shadow="shadow">
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
                            <el-tree-select @check="onNodeCheck" v-model="addressForm.area" :data="areaList" placeholder="请选择地区" show-checkbox accordion highlight-current ref="treeRef"/>
                        </el-form-item>
                        <el-divider/>
                        <el-form-item prop="details" label="详细地址" :rules="[{required: true, message: '请输入详细地址', trigger: 'blur'}]">
                            <el-input v-model="addressForm.details" placeholder="请输入详细地址"></el-input>
                        </el-form-item>
                        <el-divider/>
                        <el-form-item prop="zipCode" label="邮政编码" :rules="[{required: true, message: '请输入邮政编码', trigger: 'blur'}]">
                            <el-input v-model="addressForm.zipCode" placeholder="请输入邮政编码"></el-input>
                        </el-form-item>
                        <el-divider/>
                        <div class="address">
                            <el-alert title="默认收货地址" type="warning" :closable="false">
                                <template #default>
                                    <div>{{addressForm.name}}  {{addressForm.phone}}</div>
                                    <div>{{addressForm.address}} - {{addressForm.details}}</div>
                                </template>
                            </el-alert>
                        </div>
						<div style="padding: 2rem 0;">
                            <el-button @click="onSubmit" type="danger" size="large" round style="width: 100%;">保存</el-button>
                        </div>
					</el-form>
                </el-card>
            </el-scrollbar>
        </div>
    </div>
</template>
<script setup>
    import {onBeforeMount, onMounted, reactive, ref, unref, toRefs} from 'vue'
    import {useRouter} from 'vue-router'
    import areaArr from "@/utils/area"

    const router = useRouter()
    const addressFormRef = ref(null)
    const treeRef = ref(null)
    const state = reactive({
        shadow: 'never',
        defaultHeight: null,
        areaList: areaArr,
        addressForm: {
            id: '',
            userId: '',
            name: '',
            phone: '',
            area: '',
            details: '',
            zipCode: '',
            address: '',
        },
    })

    const {
        shadow,defaultHeight,areaList,addressForm
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 45 + "px";
    })

    onMounted(()=>{
        onAddress();
    })

    const onAddress = (userId) =>{
        state.addressForm = {
            id: 'a10001',
            userId: 'u10001',
            name: '小平',
            phone: '18893542365',
            area: '甘肃省兰州市七里河区',
            details: '兰石厂会让站家属院',
            zipCode: '747500',
            address: '甘肃省兰州市七里河区',
        }
    }

    const onNodeCheck = (data,event) =>{
        event
        // const tree = unref(treeRef)
        // let nodes = tree.getCheckedNodes(false,true);
        let nodes = event.halfCheckedNodes;
        nodes.push(data)
        let _area = '';
        nodes.forEach(node => {
            _area = _area + '' + node.label
        });
        state.addressForm.address = _area;
    }

    const onSubmit = async () =>{
        const form = unref(addressFormRef);
        if (!form) return;
        await form.validate();
        // 保存完整地区（选中的只是编码）
        state.addressForm.area = state.addressForm.address;

        console.log(state.addressForm)
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
    }
</style>

