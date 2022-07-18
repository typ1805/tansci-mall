<template>
    <div class="integral">
        <div class="integral-header">
            <el-page-header @back="toBack" icon="ArrowLeft" title="返回">
                <template #content>
                    <span class="content">我的积分</span>
                </template>
            </el-page-header>
        </div>
        <div class="integral-main">
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
        integralInfo: {},
    })

    const {
        defaultHeight,integralInfo
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 80 + "px";
    })

    onMounted(()=>{
        onIntegral();
    })

    const onIntegral = (userId) =>{
            
    }

    const toBack = () =>{
        router.go(-1)
    }

</script>
<style lang="scss">
    .integral{
        .integral-header{
            padding: 0.4rem 0;
            .content{
                font-size: 16px;
                padding-left: 5rem;
            }
        }
        .integral-main{
            .el-scrollbar{
                background: #FAFAFA;
                padding: 0.4rem 0.2rem;
            }
        }
    }
</style>

