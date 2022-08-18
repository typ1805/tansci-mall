<template>
    <div class="order-list">
        <div class="order-list-header">
            <el-page-header @back="toBack" icon="ArrowLeft" title="返回">
                <template #content>
                    <span class="content">订单列表</span>
                </template>
            </el-page-header>
        </div>
        <div class="order-list-main">
            <div class="main-tabs">
                <el-tabs v-model="activeTab" :stretch="true" @tab-click="onHandleClick">
                    <el-tab-pane name="all">
                        <template #label>
                            <span>全部</span>
                        </template>
                        <el-scrollbar :height="defaultHeight">
                            <OrderList :orderList="orderList" @onDelOrder="onDelOrder" @onEvaluate="onEvaluate" @onBuyAgain="onBuyAgain"></OrderList>
                        </el-scrollbar>
                    </el-tab-pane>
                    <el-tab-pane name="pay">
                        <template #label>
                            <span>待付款</span>
                        </template>
                        <el-scrollbar :height="defaultHeight">
                            <OrderList :orderList="orderList" @onDelOrder="onDelOrder" @onEvaluate="onEvaluate" @onBuyAgain="onBuyAgain"></OrderList>
                        </el-scrollbar>
                    </el-tab-pane>
                    <el-tab-pane name="delivery">
                        <template #label>
                            <span>待收货</span>
                        </template>
                        <el-scrollbar :height="defaultHeight">
                            <OrderList :orderList="orderList" @onDelOrder="onDelOrder" @onEvaluate="onEvaluate" @onBuyAgain="onBuyAgain"></OrderList>
                        </el-scrollbar>
                    </el-tab-pane>
                    <el-tab-pane name="appraise">
                        <template #label>
                            <span>待评价</span>
                        </template>
                        <el-scrollbar :height="defaultHeight">
                            <OrderList :orderList="orderList" @onDelOrder="onDelOrder" @onEvaluate="onEvaluate" @onBuyAgain="onBuyAgain"></OrderList>
                        </el-scrollbar>
                    </el-tab-pane>
                </el-tabs>
            </div>
        </div>
    </div>
</template>
<script setup>
    import {onBeforeMount, onMounted, reactive, toRefs} from 'vue'
    import {ElMessage,ElMessageBox} from 'element-plus'
    import {useRouter,useRoute} from 'vue-router'
    import {useUserStore} from '@/store/settings'
    import OrderList from '@/components/miniapp/OrderList.vue'
    import {getOrderList,delOrder} from '@/api/mobile/order'

    const router = useRouter()
    const route = useRoute()
    const userStore = useUserStore();
    const state = reactive({
        defaultHeight: null,
        activeTab: 'all',
        orderList: [],
    })

    const {
        defaultHeight,activeTab,orderList
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 80 + "px";
    })

    onMounted(()=>{
        if(route.query){
            state.activeTab = route.query.type?route.query.type:'all'
        }
        onOrderList();
    })

    const onHandleClick = (tab, event) =>{
        onOrderList(tab.props.name);
    }

    const onOrderList = (type) =>{
        const user = userStore.getUser.user;
        if(!user || !user.username){
            return;
        }

        let status = null;
        switch(type){
            case 'all':
                status = null;
                break;
            case 'pay':
                status = '0';
                break;
            case 'delivery':
                status = '1,2,3';
                break;
            case 'appraise':
                status = '4';
                break;
            default:
                status = null;
        }

        getOrderList({username: user.username, status: status}).then(res=>{
            state.orderList = res.result;
        })
    }

    // 删除订单信息
    const onDelOrder = (orderId) =>{
        ElMessageBox.confirm('此操作将永久删除, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            center: true,
            showClose: false,
        }).then(() => {
            delOrder({orderId: orderId}).then(res=>{
                if(res){
                    ElMessage.success("删除成功！")
                    onOrderList()
                }
            })
        }).catch(e=>{
            console.log(e)
        })
    }

    // 评价
    const onEvaluate = (orderId) =>{
        console.log(orderId)
    }

    // 再次购买
    const onBuyAgain = (goodsId) =>{
        router.push({path: '/app/goodsDetail', query:{goodsId: goodsId}})
    }

    const toBack = () =>{
        router.go(-1)
    }

</script>
<style lang="scss">
    .order-list{
        .order-list-header{
            padding: 0.4rem 0;
            // border-bottom: 1px solid #F0F2F5;
            .content{
                font-size: 16px;
                padding-left: 5rem;
            }
        }
        .order-list-main{
            .el-tabs__header{
                margin: 0;
                padding: 0.2rem 0.4rem;
            }
            .el-scrollbar{
                background: #FAFAFA;
                padding: 0.4rem 0.2rem;
            }
        }
    }
</style>

