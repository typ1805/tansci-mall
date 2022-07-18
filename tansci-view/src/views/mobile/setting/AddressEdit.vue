<template>
    <div class="address-edit">
        <div class="address-edit-header">
            <el-page-header @back="toBack" icon="ArrowLeft" title="返回">
                <template #content>
                    <span class="content">优惠券</span>
                </template>
            </el-page-header>
        </div>
        <div class="address-edit-main">
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
        addressList: [],
    })

    const {
        defaultHeight,addressList
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 80 + "px";
    })

    onMounted(()=>{
        onAddress();
    })

    const onAddress = (userId) =>{
            
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
        }
    }
</style>

