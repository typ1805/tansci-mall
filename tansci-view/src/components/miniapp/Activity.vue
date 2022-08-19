<template>
    <el-card class="activity-container" :shadow="shadow" :body-style="{padding: '0.4rem'}">
        <template #header>
            <el-icon color="#F56C6C" style="vertical-align: middle; padding-right: 0.2rem;"><Promotion /></el-icon>
            <span style="vertical-align: middle">今日特卖</span>
        </template>
        <div class="activity-content">
            <div class="activity-item">
                <el-carousel height="10rem" direction="vertical" :autoplay="true" indicator-position="none">
                    <el-carousel-item v-for="seckill in seckillList" :key="seckill">
                        <div @click="toGoodsDetail(seckill.goodsId)">
                            <el-image :src="seckill.goods.coverImg" style="width: 100%; height: 100%; border-radius: 4px;" fit="cover"/>
                        </div>
                    </el-carousel-item>
                </el-carousel>
            </div>
            <div class="activity-item">
                <el-carousel height="10rem" direction="vertical" :interval="4000" :autoplay="true" indicator-position="none">
                    <el-carousel-item v-for="ranking in rankingList" :key="ranking">
                        <div @click="toGoodsDetail(ranking.goodsId)">
                            <el-image :src="ranking.goods.coverImg" style="width: 100%; height: 100%; border-radius: 4px;" fit="cover"/>
                        </div>
                    </el-carousel-item>
                </el-carousel>
            </div>
        </div>
    </el-card>
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
            path: '/app/goodsDetail',
            query:{
                goodsId: goodsId
            }
        })
    }

</script>
<style lang="scss">
    .activity-container{
        .activity-content{
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
            align-items: center;
        }
        .el-card__header{
            border: none;
            padding: 0.3rem 0.6rem;
            font-weight: 700;
            font-size: 14px;
            color: #000000;
            background-image: linear-gradient(-20deg, #e9defa 0%, #fad0c4 100%);
            background-image: linear-gradient(-20deg, #e9defa 0%, #fbfcdb 100%);
        }
        .activity-item{
            width: 49%;
        }
    }
</style>