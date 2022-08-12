<template>
    <div class="goods-container">
        <div v-if="mode == 'vertical' && goodsList && goodsList.length > 0" class="goods-vertical-list">
            <el-card v-if="carouselList && carouselList.length > 0" :shadow="shadow" :body-style="{padding: '0px'}">  
                <el-carousel height="273px" :autoplay="true" indicator-position="none">
                    <el-carousel-item v-for="goods in carouselList" :key="goods" @click="toGoodsDetail(goods.goodsId)" style="height: 100%">
                        <el-image :src="goods.image" style="width: 100%; height: 100%;" fit="fit"/>
                    </el-carousel-item>
                </el-carousel>
            </el-card>
            <el-card v-for="item in goodsList" :key="item" @click="toGoodsDetail(item.goodsId)" :shadow="shadow" :body-style="{padding: '0px'}">  
                <el-image :src="item.image" style="width: 100%; height: 180px;" fit="fit"/>
                <div class="goods-content">
                    <div class="content-title">{{item.name}}</div>
                    <div class="content-price">
                        <span style="font-size: 12px;">￥</span><span>{{onDecimal(item.price)}}</span>
                    </div>
                    <div class="content-comment">{{item.comment}}+ 条评论  {{item.rating}}%好评率</div>
                </div>
            </el-card>
        </div>
        <div v-if="mode == 'horizontal' && goodsList && goodsList.length > 0" class="goods-horizontal-list">
            <el-card v-if="carouselList && carouselList.length > 0" :shadow="shadow" :body-style="{padding: '0px'}">  
                <el-carousel height="180px" :autoplay="true" indicator-position="none">
                    <el-carousel-item v-for="goods in carouselList" :key="goods" @click="toGoodsDetail(goods.goodsId)" style="height: 100%">
                        <el-image :src="goods.image" style="width: 100%; height: 100%;" fit="fit"/>
                    </el-carousel-item>
                </el-carousel>
            </el-card>
            <el-card v-for="item in goodsList" :key="item" @click="toGoodsDetail(item.goodsId)" :shadow="shadow" :body-style="{padding: '0px'}">  
                <div class="goods-list">
                    <div class="goods-image">
                        <el-image :src="item.image" style="width: 120px; height: 120px;" fit="fit"/>
                    </div>
                    <div class="goods-content">
                        <div class="content-title">{{item.name}}</div>
                        <div class="content-price">
                            <span style="font-size: 12px;">￥</span><span>{{onDecimal(item.price)}}</span>
                        </div>
                        <div v-if="item.labelList && item.labelList.length > 0" class="content-label">
                            <el-tag v-for="label in item.labelList" :key="label" :type="label.type" round size="small" style="margin-right: 0.4rem;">{{label.name}}</el-tag>
                        </div>
                        <div class="content-comment">{{item.comment}}+ 条评论  {{item.rating}}%好评率</div>
                    </div>
                </div>
            </el-card>
        </div>
        <div v-if="goodsList && goodsList.length > 4" class="goods-tips">~已经到底了！~</div>
        <el-empty v-else :image-size="200" description="没找到您需要的商品，试试其他的吧~"/>
    </div>
</template>
<script setup>
    import {defineProps, reactive, toRefs} from 'vue'
    import {useRouter} from 'vue-router'
    import {toDecimal} from '@/utils/utils'

    const router = useRouter()

    const props = defineProps({
        carouselList: Array,
        goodsList: Array,
        mode: {
            type: String,
            default: 'vertical' // 展示模式：可选值（horizontal、vertical），默认 vertical
        }
    })

    const state = reactive({
        shadow: 'always',
    })

    const {
        shadow
    } = toRefs(state)

    function onDecimal(val){
        return toDecimal(val);
    }

    const toGoodsDetail = (goodsId) =>{
        router.push({
            path: '/app/goodsDetail',
            query:{
                goodsId: goodsId
            }
        })
    }

</script>
<style lang="scss">
    .goods-container{
        .goods-vertical-list,.goods-horizontal-list{
            .el-card{
                border: none;
                border-radius: 8px;
                cursor: pointer;
                margin-top: 0.6rem;
            }
            .goods-content{
                padding: 0.6rem;
                .content-title{
                    font-weight: 700;
                    font-size: 14px;
                    white-space: nowrap;
                    overflow: hidden;
                    text-overflow: ellipsis;
                    padding-bottom: 0.6rem;
                }
                .content-price{
                    color: red;
                    font-weight: 700;
                    font-size: 16px;
                    padding-bottom: 0.6rem;
                }
                .content-label{
                    padding-bottom: 0.6rem;
                }
                .content-comment{
                    font-size: 12px;
                    color: #909399;
                }
            }
        }
        .goods-vertical-list{
            display: flex;
            justify-content: space-between;
            align-items: stretch;
            flex-wrap: wrap;
            .el-card{
                width: 49%;
            }
        }
        .goods-horizontal-list{
            .goods-list{
                display: flex;
                justify-content: flex-start;
                align-items: stretch;
                flex-wrap: wrap;
                .goods-image{
                    padding: 0.4rem;
                    .el-image{
                        border-radius: 8px;
                    }
                }
            }
            .goods-content{
                padding-left: 0.4rem;
                margin: auto 0;
            }
        }
        .goods-tips{
            padding: 1rem 0;
            text-align: center;
            margin: 0 auto;
            font-size: 12px;
            color: #909399;
        }
    }
</style>