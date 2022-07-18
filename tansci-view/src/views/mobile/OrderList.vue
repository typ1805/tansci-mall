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
    import {useRouter,useRoute} from 'vue-router'
    import OrderList from '@/components/miniapp/OrderList.vue'

    const router = useRouter()
    const route = useRoute()

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
        if(!type){
            type = state.activeTab;
        }
        // 请求接口
        if('all' == type){
            state.orderList = [
                {
                    shopId: '10002',
                    shopName: '金发地个旗舰店',
                    amount: 144,
                    orderId: 'o1000014',
                    goodsList: [
                        {
                            goodsId: 'g0004',
                            name: '鸿星尔克官方旗舰2022男鞋防滑透气舒适轻便时尚',
                            number: 1,
                            image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/87173/11/29471/354593/62adf573Eac278253/3bc086c45af2cce4.jpg.avif',
                        }
                    ]
                },
                {
                    shopId: '10003',
                    shopName: '丰东股份旗舰店',
                    amount: 144,
                    orderId: 'o1000015',
                    goodsList: [
                        {
                            goodsId: 'g0004',
                            name: '鸿星尔克官方旗舰2022男鞋防滑透气舒适轻便时尚',
                            number: 1,
                            image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/87173/11/29471/354593/62adf573Eac278253/3bc086c45af2cce4.jpg.avif',
                        },
                        {
                            goodsId: 'g0004',
                            name: '鸿星尔克官方旗舰2022男鞋防滑透气舒适轻便时尚',
                            number: 1,
                            image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/87173/11/29471/354593/62adf573Eac278253/3bc086c45af2cce4.jpg.avif',
                        }
                    ]
                },
            ]
        } else if('pay' == type){
            state.orderList = [
                {
                    shopId: '10002',
                    shopName: '金发地个旗舰店',
                    amount: 144,
                    orderId: 'o1000014',
                    goodsList: [
                        {
                            goodsId: 'g0004',
                            name: '鸿星尔克官方旗舰2022男鞋防滑透气舒适轻便时尚',
                            number: 1,
                            image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/87173/11/29471/354593/62adf573Eac278253/3bc086c45af2cce4.jpg.avif',
                        }
                    ]
                },
            ]
        } else {
             state.orderList = []
        }     
    }

    // 删除订单信息
    const onDelOrder = (orderId) =>{
        console.log(orderId)
    }

    // 评价
    const onEvaluate = (orderId) =>{
        console.log(orderId)
    }

    // 再次购买
    const onBuyAgain = (orderId) =>{
        console.log(orderId)
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

