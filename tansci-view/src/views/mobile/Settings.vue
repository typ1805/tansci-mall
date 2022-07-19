<template>
    <div class="setting">
        <el-scrollbar :height="defaultHeight">
            <div class="setting-header">
                <div>
                    <el-avatar :src="userInfo.avatar" size="large"/>
                </div>
                <div class="header-title">
                    <div>{{userInfo.username}}</div>
                    <el-tag type="warning" round size="small">V1 普通会员</el-tag>
                </div>
            </div>
            <div class="setting-main">
                <div class="main-order">
                    <el-card :shadow="shadow">
                        <template #header>
                            <span>我的订单</span>
                            <el-button @click="onOrder(null)" icon="ArrowRightBold" type="primary" text style="float: right;padding-bottom: 1.3rem;"></el-button>
                        </template>
                        <div class="card-order">
                            <div v-for="order in orderList" :key="order" @click="onOrder(order.path)" class="card-order-item">
                                <el-icon :color="order.color" :size="30">
                                    <component :is="order.icon"></component>
                                </el-icon>
                                <div>{{order.name}}</div>
                            </div>
                        </div>
                    </el-card>
                </div>
                <div class="main-service">
                    <el-card :shadow="shadow">
                        <div v-for="(item,index) in serviceList" :key="index" class="card-service">
                            <div class="card-service-title">
                                <el-icon :color="item.color" :size="24">
                                    <component :is="item.icon"></component>
                                </el-icon>
                                <span>{{item.name}}</span>
                            </div>
                            <div @click="onService(item.path)">
                                <el-icon><ArrowRightBold /></el-icon>
                            </div>
                            <el-divider v-show="index+1 < serviceList.length" />
                        </div>
                    </el-card>
                </div>
            </div>
        </el-scrollbar>
        <div class="setting-footer">
            <FooterMenu></FooterMenu>
        </div>
    </div>
</template>
<script setup>
    import {onBeforeMount, onMounted, reactive, toRefs} from 'vue'
    import {useRouter} from 'vue-router'
    import FooterMenu from '@/components/miniapp/FooterMenu.vue'

    const router = useRouter();

    const state = reactive({
        shadow: 'always',
        defaultHeight: null,
        userInfo: {
            username: '小泡泡',
            avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
        },
        orderList: [],
        serviceList: [],
    })

    const {
        shadow,defaultHeight,userInfo,orderList,serviceList,
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 40 + "px";
    })

    onMounted(()=>{
        onOrderList();
        onServiceList();
    })

    const onOrderList = () =>{
        state.orderList = [
            {path:'pay', icon: 'CreditCard', color:'#303133', name: '待付款'},
            {path:'delivery', icon: 'Postcard', color:'#303133', name: '待发货'},
            {path:'delivery', icon: 'Van', color:'#303133', name: '已发货'},
            {path:'appraise', icon: 'ChatLineSquare', color:'#303133', name: '待评价'},
            {path:'all', icon: 'Suitcase', color:'#303133', name: '已完成'},
        ]
    }

    const onOrder = (val) =>{
        router.push({path: '/miniapp/orderList', query:{type: val}})
    }

    const onServiceList = () =>{
        state.serviceList = [
            {path:'/miniapp/accountSetting', icon: 'Setting', color:'#303133', name: '账号管理'},
            {path:'/miniapp/coupon', icon: 'Ticket', color:'#303133', name: '优惠券'},
            {path:'/miniapp/integral', icon: 'Coin', color:'#303133', name: '我的积分'},
            {path:'/miniapp/member', icon: 'Medal', color:'#303133', name: '我的会员'},
            {path:'/miniapp/addressEdit', icon: 'OfficeBuilding', color:'#303133', name: '地址管理'},
            {path:'/miniapp/customerService', icon: 'Service', color:'#303133', name: '客户服务'},
        ]
    }

    const onService = (val) =>{
        router.push({path: val})
    }

</script>
<style lang="scss">
    .setting{
        .setting-header{
            display: flex;
            flex-wrap: wrap;
            background-image: linear-gradient(to right, #fad0c4 0%, #e9defa 100%);
            padding: 2rem;
            .header-title{
                padding-left: 1rem;
                div{
                    font-size: 14px;
                    font-weight: 700;
                    padding-bottom: 0.2rem;
                }
                .el-tag{
                    font-size: 12px;
                    transform : scale(.833);
                    margin-left: -0.4rem;
                }
            }
        }
        .setting-main{
            padding: 0.4rem;
            .main-order{
                padding-bottom: 0.4rem;
                .el-card__header{
                    padding: 0.4rem 1rem;
                    border-image: linear-gradient(to right, #fff 0%,#e4e7ed 20%, #e4e7ed 80%, #fff 100%) 1 1;
                }
                .card-order{
                    display: flex;
                    align-items: center;
                    flex-wrap: wrap;
                    justify-content: space-between;
                    .card-order-item{
                        width: 19%;
                        text-align: center;
                        font-size: 12px;
                        padding: 0.2rem 0;
                    }
                }
            }
            .main-service{
                .card-service{
                    display: flex;
                    align-items: center;
                    flex-wrap: wrap;
                    justify-content: space-between;
                    .card-service-title{
                        span, i{
                            vertical-align: middle;
                        }
                        span{
                            padding-left: 1rem;
                            font-size: 14px;
                        }
                    }
                }
            }
        }
    }
</style>