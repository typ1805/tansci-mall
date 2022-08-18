<template>
    <div class="order-list-container">
        <el-card v-for="(order,index) in orderList" :key="index" class="order-content" :shadow="shadow">
            <div class="shop-info">
                <div class="shop-info-title">
                    <el-icon :size="20" style="padding-right: 0.4rem;"><Shop /></el-icon>
                    <span>{{order.shopName}}</span>
                </div>
                <div>
                    <span>{{order.orderStatusName}}</span>
                    <el-divider direction="vertical" />
                    <el-button @click="onDelOrder(order.orderId)" type="primary" icon="Delete" link></el-button>
                </div>
            </div>
            <div v-for="goods in order.goodsList" :key="goods" class="goods-list">
                <div class="goods-image">
                    <el-image :src="goods.coverImg" style="width: 120px; height: 120px;" fit="fit"/>
                </div>
                <div class="goods-content">
                    <div class="content-title">{{goods.name}}</div>
                    <div>
                        <span style="font-size: 12px;">数量：</span><span style="font-weight: 700;">{{goods.goodsNum}}</span>
                    </div>
                </div>
            </div>
            <div class="order-amount">
                <span>实付：</span>
                <span style="font-size: 12px;font-weight: 700;">￥</span>
                <span style="font-weight: 700;font-size: 16px;">{{onDecimal(order.price)}}</span>
            </div>
            <el-divider/>
            <div class="order-but">
                <el-button @click="onEvaluate(order.orderId)" round>评价晒单</el-button>
                <el-button @click="onBuyAgain(order.goodsList[0].goodsId)" type="danger" round>再次购买</el-button>
            </div>
        </el-card>
        <div v-if="orderList && orderList.length" class="order-tips">~已经到底了！~</div>
        <el-empty v-else :image-size="200" description="您暂时没有相关订单！"/>
    </div>
</template>
<script setup>
    import {defineProps, defineEmits, reactive, toRefs} from 'vue'
    import {toDecimal} from '@/utils/utils'

    const props = defineProps({
        orderList: Array,
    })

    const state = reactive({
        shadow: 'never',
    })

    const emit = defineEmits([
        'onDelOrder', 'onEvaluate', 'onBuyAgain'
    ])

    const {
        shadow,
    } = toRefs(state)

    function onDecimal(val){
        return toDecimal(val);
    }

    // 删除订单
    const onDelOrder = (orderId) =>{
        emit('onDelOrder', orderId);
    }

    // 评价
    const onEvaluate = (orderId) =>{
        emit('onEvaluate', orderId);
    }

    // 再次购买
    const onBuyAgain = (goodsId) =>{
        emit('onBuyAgain', goodsId);
    }

</script>
<style lang="scss">
    .order-list-container{
        .el-card{
            margin-bottom: 0.4rem;
            border-radius: 8px;
        }
        .order-content{
            .shop-info{
                display: flex;
                justify-content: space-between;
                padding-bottom: 0.4rem;
                color: var(--t6);
                i,span{
                    vertical-align: middle;
                }
                .shop-info-title{
                    font-size: 18px;
                    color: var(--t9);
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
                }
            }
            .order-amount{
                display: flex;
                justify-content: end;
            }
            .order-but{
                display: flex;
                justify-content: end;
            }
            .el-divider--horizontal{
                margin: 10px 0;
            }
        }
        .order-tips{
            padding: 1rem 0;
            text-align: center;
            margin: 0 auto;
            font-size: 12px;
            color: #909399;
        }
    }
</style>