<template>
    <div class="index">
        <div class="index-search">
            <Search></Search>
        </div>
        <el-scrollbar :height="defaultHeight">
            <div class="index-card">
                <el-card :shadow="shadow" :body-style="{padding: '1rem 0.2rem'}"> 
                    <div class="card-shop">
                        <div v-for="item in configList" :key="item" class="card-shop-item">
                            <el-icon :color="item.color" :size="40">
                                <component :is="item.icon"></component>
                            </el-icon>
                            <div>{{item.name}}</div>
                        </div>
                    </div>
                </el-card>
            </div>
            <div class="index-activity">
                <Activity :seckillList="seckillList" :rankingList="rankingList"></Activity>
            </div>
            <div class="index-recommend">
                <GoodsList :goodsList="goodsList" :carouselList="carouselList"></GoodsList>
            </div>
        </el-scrollbar>
        <div class="index-footer">
            <FooterMenu></FooterMenu>
        </div>
    </div>
</template>
<script setup>
    import {onBeforeMount, onMounted, reactive, toRefs} from 'vue'
    import Search from '@/components/miniapp/Search.vue'
    import Activity from '@/components/miniapp/Activity.vue'
    import GoodsList from '@/components/miniapp/GoodsList.vue'
    import FooterMenu from '@/components/miniapp/FooterMenu.vue'
    import {getGoodsPage} from '@/api/mobile/goods'
    import {getIndexConfigPage,getIndexCarouselPage,getConfigGoodsPage} from '@/api/mobile/index'

    const state = reactive({
        shadow: 'always',
        defaultHeight: null,
        configList: [], 
        seckillList: [], 
        rankingList: [],
        goodsPage: {
            current: 1,
            size: 10,
            total: 1,
        },
        goodsList: [], 
        carouselList: [],
    })

    const {
        shadow,defaultHeight,configList,seckillList,rankingList,goodsPage,goodsList,carouselList,
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 100 + "px";
    })

    onMounted(() => {
        onIndexConfig();
        onSeckillList();
        onRankingList();
        onCarouselGoods();
        onGoodsPage();
    })

    // 配置标签
    const onIndexConfig = () =>{
        getIndexConfigPage({current: 1,size: 10, status: 0}).then(res=>{
            state.configList = res.result.records;
        })
    }

    // 秒杀商品
    const onSeckillList = () =>{
        getConfigGoodsPage({current: 1,size: 5, type: 1}).then(res=>{
            state.seckillList = res.result.records;
        })
    }

    // 排行榜
    const onRankingList = () =>{
        getConfigGoodsPage({current: 1,size: 5, type: 2}).then(res=>{
            state.rankingList = res.result.records;
        })
    }

    // 轮播商品
    const onCarouselGoods = () =>{
        getIndexCarouselPage({current: 1,size: 5}).then(res=>{
            state.carouselList = res.result.records;
        })
    }

    // 热门商品
    const onGoodsPage = () =>{
        getGoodsPage(state.goodsPage).then(res=>{
            state.goodsList = res.result.records;
        })
    }

</script>
<style lang="scss">
    .index{
        // background-image: linear-gradient(to bottom, #ff0844 0%, #ffb199 20%, #e9defa, 30%, #F2F3F5 100%);
        .index-search{
            padding: 0.4rem;
        }
        .index-card{
            padding: 0.4rem;
            .card-shop{
                display: flex;
                align-items: center;
                flex-wrap: wrap;
                justify-content: space-between;
                .card-shop-item{
                    width: 19%;
                    text-align: center;
                    font-size: 12px;
                    padding: 0.2rem 0;
                }
            }
        }
        .index-activity{
            padding: 0.2rem 0.4rem;
        }
        .index-recommend{
            padding: 0 0.4rem;
        }
        .index-footer{
            
        }
    }
</style>