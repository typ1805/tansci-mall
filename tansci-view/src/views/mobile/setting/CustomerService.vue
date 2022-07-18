<template>
    <div class="customer-service">
        <div class="customer-service-header">
            <el-page-header @back="toBack" icon="ArrowLeft" title="返回">
                <template #content>
                    <span class="content">我的会员</span>
                </template>
            </el-page-header>
        </div>
        <div class="customer-service-main">
            <el-scrollbar :height="defaultHeight">

            </el-scrollbar>
        </div>
    </div>
</template>
<script setup>
    import {onBeforeMount, onMounted, reactive, toRefs} from 'vue'
    import {useRouter} from 'vue-router'

    const router = useRouter()

    const state = reactive({
        defaultHeight: null,
        customerInfo: {},
    })

    const {
        defaultHeight,customerInfo
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 80 + "px";
    })

    onMounted(()=>{
        onCustomerInfo();
    })

    const onCustomerInfo = (userId) =>{
            
    }

    const toBack = () =>{
        router.go(-1)
    }

</script>
<style lang="scss">
    .customer-service{
        .customer-service-header{
            padding: 0.4rem 0;
            .content{
                font-size: 16px;
                padding-left: 5rem;
            }
        }
        .customer-service-main{
            .el-scrollbar{
                background: #FAFAFA;
                padding: 0.4rem 0.2rem;
            }
        }
    }
</style>

