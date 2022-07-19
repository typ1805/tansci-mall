<template>
    <div class="member">
        <div class="member-header">
            <el-page-header @back="toBack" icon="ArrowLeft" title="返回">
                <template #content>
                    <span class="content">我的会员</span>
                </template>
            </el-page-header>
        </div>
        <div class="member-main">
            <el-scrollbar :height="defaultHeight">
                <el-result icon="warning" title="暂无会员" sub-title="会员功能正在上线中，请耐心等待！"></el-result>
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
        memberInfo: {},
    })

    const {
        defaultHeight,memberInfo
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 45 + "px";
    })

    onMounted(()=>{
        onMember();
    })

    const onMember = (userId) =>{
            
    }

    const toBack = () =>{
        router.go(-1)
    }

</script>
<style lang="scss">
    .member{
        .member-header{
            padding: 0.4rem 0;
            .content{
                font-size: 16px;
                padding-left: 5rem;
            }
        }
        .member-main{
            .el-scrollbar{
                background: #FAFAFA;
                padding: 0.4rem 0.2rem;
            }
        }
    }
</style>

