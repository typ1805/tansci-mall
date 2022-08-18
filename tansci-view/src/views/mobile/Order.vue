<template>
    <div class="order">
        <div class="order-header">
            <el-page-header @back="toBack" icon="ArrowLeft" title="返回">
                <template #content>
                    <span class="content">确认订单</span>
                </template>
            </el-page-header>
        </div>
        <el-scrollbar :height="defaultHeight">
            <div class="order-main">
                <div class="main-address">
                    <el-card :shadow="shadow">
                        <div class="address-content">
                            <div>{{addressInfo.details}}</div>
                            <div style="font-weight: 700;padding: 0.4rem 0;">{{addressInfo.province+addressInfo.city+addressInfo.region}}</div>
                            <div>{{addressInfo.name}} {{addressInfo.phone}}</div>
                        </div>
                    </el-card>
                </div>
                <div class="main-shop">
                    <el-card :shadow="shadow">
                        <div v-for="(order,index) in orderInfo.orderList" :key="index" class="shop-content">
                            <div class="shop-info">
                                <el-icon style="padding-right: 0.4rem;"><Shop /></el-icon>
                                <span>{{order.shopName}}</span>
                            </div>
                            <div v-for="goods in order.goodsList" :key="goods" class="goods-list">
                                <div class="goods-image">
                                    <el-image :src="goods.coverImg" style="width: 120px; height: 120px;" fit="fit"/>
                                </div>
                                <div class="goods-content">
                                    <div class="content-title">{{goods.name}}</div>
                                    <div class="content-price">
                                        <span style="font-size: 12px;">￥</span><span>{{onDecimal(goods.price)}}</span>
                                    </div>
                                    <div>
                                        <span style="font-size: 12px;">数量：</span><span style="font-weight: 700;">{{goods.goodsNum}}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="shop-transport">
                                <div>配送</div>
                                <div>普通快递</div>
                            </div>
                            <div>预计三日后的24:00前送达</div>
                            <el-divider v-show="index+1 < orderInfo.orderList.length"  />
                        </div>
                    </el-card>
                </div>
                <div class="main-amount">
                    <el-card :shadow="shadow">
                        <div class="amount-content">
                            <div class="content-item">
                                <div>商品金额</div>
                                <div style="font-weight: 700;">￥{{onDecimal(orderInfo.amount)}}</div>
                            </div>
                            <div class="content-item" style="padding: 1rem 0;">
                                <div>运费</div>
                                <div style="font-weight: 700;">￥{{onDecimal(orderInfo.freight)}}</div>
                            </div>
                            <div class="content-item">
                                <div>优惠金额</div>
                                <div style="font-weight: 700;">￥{{onDecimal(orderInfo.discount)}}</div>
                            </div>
                        </div>
                        <el-divider />
                        <div class="amount-total">
                            <span>合计：</span>
                            <span style="font-size: 12px;color: red;">￥</span>
                            <span class="price">{{onDecimal(orderInfo.amount)}}</span>
                        </div>
                    </el-card>
                </div>
                <div class="main-payment">
                    <el-card :shadow="shadow">
                        <div class="payment-content">
                            <div class="content-item">
                                <div>发票</div>
                                <div style="font-weight: 700;">不开发票</div>
                            </div>
                            <div class="content-item" style="padding-top: 1rem;">
                                <div>支付方式</div>
                                <div>
                                    <el-radio-group v-model="orderInfo.payType">
                                        <el-radio :label="2">微信</el-radio>
                                        <el-radio :label="1">支付宝</el-radio>
                                        <el-radio :label="3">银联</el-radio>
                                    </el-radio-group>
                                </div>
                            </div>
                        </div>
                    </el-card>
                </div>
            </div>
        </el-scrollbar>
        <div class="order-footer">
            <div class="footer-price">
                <span style="font-size: 12px;">￥</span><span>{{onDecimal(orderInfo.amount)}}</span>
            </div>
            <div class="footer-submit">
                <el-button @click="onSubmit" :disabled="orderInfo.amount>0?false:true" :loading="loading" type="danger" round>立即支付</el-button>
            </div>
        </div>
    </div>
</template>
<script setup>
    import {onBeforeMount, onMounted, reactive, toRefs} from 'vue'
    import {ElMessage} from 'element-plus'
    import {useRouter} from 'vue-router'
    import {useUserStore} from '@/store/settings'
    import {toDecimal} from '@/utils/utils'
    import {qryByUserName} from '@/api/admin/user'
    import {userAddressList} from '@/api/admin/userAddress'
    import {getCartList} from '@/api/admin/cart'
    import {submitOrder} from '@/api/mobile/order'

    const router = useRouter()
    const userStore = useUserStore();
    const state = reactive({
        shadow: 'always',
        defaultHeight: null,
        orderInfo: {},
        addressList: [],
        addressInfo: {},
        loading: false,
    })

    const {
        shadow,defaultHeight,orderInfo,addressList,addressInfo,loading,
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 80 + "px";
    })

    onMounted(()=>{
        onUserAddressList();
        onOrderInfo();
    })

    // 获取订单商品信息
    const onOrderInfo = () =>{
        const user = userStore.getUser.user;
        if(!user || !user.username){
            return;
        }
        getCartList({username: user.username}).then(res=>{
            if(res.result){
                let totalAmount = 0;
                res.result.forEach(item=>{
                    item.goodsList.forEach(goods=>{
                        if(goods.isSelect){
                            totalAmount = totalAmount + goods.price * goods.goodsNum;
                        }
                    })
                })
                state.orderInfo = {
                    amount: totalAmount,
                    payType: 2,
                    freight: 0,
                    discount: 0,
                    orderList: res.result,
                }
            }
        })

        // state.orderInfo = {
        //     amount: 144,
        //     freight: 10,
        //     discount: 0,
        //     orderList: [
        //         {
        //             shopId: '10002',
        //             shopName: '金发地个旗舰店',
        //             goodsList: [
        //                 {
        //                     goodsId: 'g0004',
        //                     name: '鸿星尔克官方旗舰2022男鞋防滑透气舒适轻便时尚',
        //                     price: 144.00,
        //                     number: 1,
        //                     image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/87173/11/29471/354593/62adf573Eac278253/3bc086c45af2cce4.jpg.avif',
        //                 }
        //             ]
        //         },
        //         {
        //             shopId: '10003',
        //             shopName: '丰东股份旗舰店',
        //             goodsList: [
        //                 {
        //                     goodsId: 'g0004',
        //                     name: '鸿星尔克官方旗舰2022男鞋防滑透气舒适轻便时尚',
        //                     price: 144.00,
        //                     number: 1,
        //                     image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/87173/11/29471/354593/62adf573Eac278253/3bc086c45af2cce4.jpg.avif',
        //                 }
        //             ]
        //         }
        //     ]
        // }
    }

    // 获取商户地址
    const onUserAddressList = () =>{
        const user = userStore.getUser.user;
        if(!user || !user.username){
            return;
        }
        qryByUserName({username: user.username}).then(res=>{
            userAddressList({userId: res.result.id}).then(res=>{
                if(res.result){
                    state.addressList = res.result;
                    state.addressInfo = res.result.find(item=>{
                        return item.flag = 1;
                    });
                }
            });
        });
    }

    // 支付
    const onSubmit = () =>{
        state.loading = true;
        let orders = [];
        let orderList = state.orderInfo.orderList;
        orderList.forEach(shop=>{
            let goodsList = JSON.parse(JSON.stringify(shop.goodsList));
            let _goodsList = goodsList.filter(goods => goods.isSelect === 1);
            _goodsList.forEach(item =>{
                orders.push({
                    cartId: item.cartId,
                    goodsId: item.goodsId,
                    goodsNum: item.goodsNum,
                    price: item.price,
                    discount: 0,
                    shopId: item.shopId,
                    userId: item.userId,
                });
            });
        })

        const user = userStore.getUser.user;
        let param = {
            payType: state.orderInfo.payType,
            username: user.username,
            goodsOrders: orders,
        }
        submitOrder(param).then(res=>{
            if(res){
                ElMessage.success(res.result)
                router.push({path:'/app/settings'});
                state.loading = false;
            }
        })
    }

    const toBack = () =>{
        router.go(-1)
    }

    function onDecimal(val){
        return toDecimal(val);
    }

</script>
<style lang="scss">
    .order{
        .order-header{
            padding: 0.4rem 0;
            border-bottom: 1px solid #F0F2F5;
            .content{
                font-size: 16px;
                padding-left: 5rem;
            }
        }
        .order-main{
            .main-address,.main-shop,.main-amount,.main-payment{
                .el-card{
                    border-left: 0;
                    border-right: 0;
                    margin-bottom: 0.4rem;
                    border-radius: 8px;
                }
            }
            .main-address{
                .el-card{
                    border-top: none;
                    border-top-left-radius: 0;
                    border-top-right-radius: 0;
                }
            }
            .main-shop{
                .shop-content{
                    .shop-info{
                        i,span{
                            vertical-align: middle;
                        }
                    }
                    .goods-list{
                        display: flex;
                        justify-content: flex-start;
                        align-items: stretch;
                        .goods-image{
                            padding: 0.4rem;
                            .el-image{
                                border-radius: 8px;
                            }
                        }
                        .goods-content{
                            padding-left: 0.4rem;
                            margin: auto 0;
                            .content-title{
                                font-weight: 700;
                                font-size: 14px;
                                padding-bottom: 0.6rem;
                                overflow: hidden;
                                display: -webkit-box;
                                -webkit-line-clamp: 2;
                                line-clamp: 2;
                                -webkit-box-orient: vertical;
                            }
                            .content-price{
                                color: red;
                                font-weight: 700;
                                font-size: 16px;
                                padding-bottom: 0.6rem;
                            }
                        }
                    }
                    .shop-transport{
                        display: flex;
                        justify-content: space-between;
                        padding: 1rem 0;
                    }
                }
            }
            .main-amount{
                .amount-content{
                    .content-item{
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                    }
                }
                .amount-total{
                    text-align: right;
                    .price{
                        color: red;
                        font-weight: 700;
                        font-size: 16px;
                    }
                }
            }
            .main-payment{
                .payment-content{
                    .content-item{
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                    }
                }
            }
        }
        .order-footer{
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-top: 1px solid #e4e7ed;
            padding: 0.4rem 0;
            .footer-price{
                color: red;
                font-weight: 700;
                font-size: 16px;
                padding-left: 1rem;
            }
            .footer-submit{
                padding-right: 1rem;
            }
        }
    }
</style>