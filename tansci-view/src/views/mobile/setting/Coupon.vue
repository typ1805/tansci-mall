<template>
    <div class="coupon">
        <div class="coupon-header">
            <el-page-header @back="toBack" icon="ArrowLeft" title="返回">
                <template #content>
                    <span class="content">优惠券</span>
                </template>
            </el-page-header>
        </div>
        <div class="coupon-main">
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
        couponList: [],
    })

    const {
        defaultHeight,couponList
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 80 + "px";
    })

    onMounted(()=>{
        onCouponList();
    })

    const onCouponList = (userId) =>{
            
    }

    const toBack = () =>{
        router.go(-1)
    }

</script>
<style lang="scss">
    .coupon{
        .coupon-header{
            padding: 0.4rem 0;
            .content{
                font-size: 16px;
                padding-left: 5rem;
            }
        }
        .coupon-main{
            .el-scrollbar{
                background: #FAFAFA;
                padding: 0.4rem 0.2rem;
            }
        }
    }
</style>

