<template>
    <div class="activity-container">
        <el-card :shadow="shadow" class="activity-seckill" :body-style="{padding: '0.4rem'}"> 
            <template #header>
                <el-icon color="#F56C6C" style="vertical-align: middle; padding-right: 0.2rem;"><Timer /></el-icon>
                <span style="vertical-align: middle">秒杀</span>
            </template>
            <div>
                <el-carousel height="80px" direction="vertical" :autoplay="true" indicator-position="none">
                    <el-carousel-item v-for="seckill in seckillList" :key="seckill">
                        <div v-for="item in seckill.goods" :key="item" @click="toGoodsDetail(item.goodsId)" class="goods">
                            <el-image :src="item.image" style="width: 100%; height: 100%;border-radius: 8px;" fit="fit"/>
                        </div>
                    </el-carousel-item>
                </el-carousel>
            </div>
        </el-card>
        <el-card :shadow="shadow" class="activity-ranking" :body-style="{padding: '0.4rem'}">  
            <template #header>
                <el-icon color="#E6A23C" style="vertical-align: middle; padding-right: 0.2rem;"><Histogram /></el-icon>
                <span style="vertical-align: middle">排行榜</span>
            </template>
            <div>
                <el-carousel height="80px" direction="vertical" :interval="4000" :autoplay="true" indicator-position="none">
                    <el-carousel-item v-for="ranking in rankingList" :key="ranking">
                        <div v-for="item in ranking.goods" :key="item" @click="toGoodsDetail(item.goodsId)" class="goods">
                            <el-image :src="item.image" style="width: 100%; height: 100%;border-radius: 8px;" fit="fit"/>
                        </div>
                    </el-carousel-item>
                </el-carousel>
            </div>
        </el-card>
    </div>
</template>
<script setup>
    import {defineProps, reactive, toRefs} from 'vue'
    import {useRouter} from 'vue-router'

    const router = useRouter()
    const props = defineProps({
        seckillList: Array,
        rankingList: Array,
    })

    const state = reactive({
        shadow: 'always',
    })

    const {
        shadow
    } = toRefs(state)

    const toGoodsDetail = (goodsId) =>{
        router.push({
            path: '/miniapp/goodsDetail',
            query:{
                goodsId: goodsId
            }
        })
    }

</script>
<style lang="scss">
    .activity-container{
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;
        align-items: center;
        .el-card{
            width: 49%;
            border: none;
            border-radius: 8px;

            .el-card__header{
                border: none;
                padding: 0.3rem 0.6rem;
                font-weight: 700;
                font-size: 14px;
                color: #000000;
            }
        }
        .activity-seckill{
            .el-card__header{
                background-image: linear-gradient(-20deg, #e9defa 0%, #fad0c4 100%);
            }
        }
        .activity-ranking{
            .el-card__header{
                background-image: linear-gradient(-20deg, #e9defa 0%, #fbfcdb 100%);
            }
        }
        .activity-seckill, .activity-ranking{
            .el-carousel__item{
                display: flex;
                justify-content: space-between;
                flex-wrap: wrap;
                align-items: center;
            }
            .goods{
                width: 48%;
            }
        }
    }
</style>