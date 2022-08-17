<template>
    <div class="shop">
        <div class="shop-header">
            <el-avatar :src="shopInfo.avatar" shape="square" :size="50" fit="fill" />
            <div class="header-title">
                <div class="title-name">{{shopInfo.name}}</div>
                <div class="title-level">
                    <span>店铺星级</span>
                    <el-rate v-model="shopInfo.level" :max="5" size="small" disabled/>
                </div>
            </div>
        </div>
        <div class="shop-main">
            <el-tabs v-model="tabAactive" :stretch="true" @tab-click="onHandleClick">
                <el-tab-pane label="精选" name="selected">
                    <el-scrollbar :height="defaultHeight">
                        <el-card :shadow="shadow" :body-style="{padding: '0.8rem 0.4rem'}">
                            <el-alert type="warning" center :closable="false">
                                <template #default>
                                    <span class="selected-title">本店新用户</span>
                                    <span>成为会员最高享9.5折，还会有1倍积分</span>
                                </template>
                            </el-alert>
                            <GoodsList :goodsList="goodsList"></GoodsList>
                        </el-card>
                    </el-scrollbar>
                </el-tab-pane>
                <el-tab-pane label="商品" name="commodity">
                    <el-scrollbar :height="defaultHeight">
                        <el-card :shadow="shadow" :body-style="{padding: '0.2rem 0.4rem'}">
                            <GoodsList :goodsList="goodsList"></GoodsList>
                        </el-card>
                    </el-scrollbar>
                </el-tab-pane>
                <el-tab-pane label="活动" name="activity">
                    <el-scrollbar :height="defaultHeight">
                        <el-card :shadow="shadow" :body-style="{padding: '0.2rem 0.4rem'}">
                            <el-empty description="暂无活动" />
                        </el-card>
                    </el-scrollbar>
                </el-tab-pane>
                <el-tab-pane label="新品" name="products">
                    <el-scrollbar :height="defaultHeight">
                        <el-card :shadow="shadow" :body-style="{padding: '0.2rem 0.4rem'}">
                            <GoodsList :goodsList="goodsList"></GoodsList>
                        </el-card>
                    </el-scrollbar>
                </el-tab-pane>
                <el-tab-pane label="动态" name="dynamic">
                    <el-scrollbar :height="defaultHeight">
                        <el-card :shadow="shadow" :body-style="{padding: '0.2rem 0.4rem'}">
                            <el-empty description="暂无动态" />
                        </el-card>
                    </el-scrollbar>
                </el-tab-pane>
            </el-tabs>
        </div>
        <div class="shop-footer">
            <FooterMenu></FooterMenu>
        </div>
    </div>
</template>
<script setup>
    import {onBeforeMount, onMounted, reactive, toRefs} from 'vue'
    import GoodsList from '@/components/miniapp/GoodsList.vue'
    import FooterMenu from '@/components/miniapp/FooterMenu.vue'
    import {useRoute} from 'vue-router'
    import {getShopInfo} from '@/api/mobile/shop'
    import {getGoodsPage} from '@/api/mobile/goods'

    const route = useRoute()
    const state = reactive({
        shadow: 'never',
        defaultHeight: null,
        tabAactive: 'selected',
        shopInfo: {},
        goodsPage: {
            current: 1,
            size: 10,
            total: 1,
        },
        goodsList: [],
    })

    const {
        shadow,defaultHeight,tabAactive,shopInfo,goodsPage,goodsList
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 150 + "px";
    })

    onMounted(()=>{
        if(route.query.shopId){
            onShopInfo(route.query.shopId);
        }
    })

    const onShopInfo = (shopId) =>{
        getShopInfo({shopId: shopId}).then(res=>{
            state.shopInfo = res.result;
            onGoodsList(shopId);
        })
    }

    const onGoodsList = (shopId) =>{
        getGoodsPage(Object.assign(state.goodsPage, {shopId: shopId})).then(res=>{
            state.goodsList = res.result.records;
        })
    }

    const onHandleClick = (val) =>{
        console.log(val)
    }
</script>
<style lang="scss">
    .shop{
        background-image: linear-gradient(to bottom, #ae8b9c 0%,#E6E8EB 30%, #ffffff 50%);
        .shop-header{
            padding: 1rem;
            display: flex;
            justify-content: left;
            align-items: center;
            color: #ffffff;
            .el-avatar{
                background: #ffffff;
            }
            .header-title{
                padding-left: 1rem;
                .title-name{
                    font-size: 18px;
                    font-weight: 700;
                }
                .title-level{
                    font-size: 12px;
                    .el-rate,span{
                        vertical-align: middle;
                    }
                    .el-rate .el-rate__icon.is-active,.el-rate__decimal{
                        color: #ff0000;
                        margin-right: 0;
                    }
                    .el-rate .el-rate__icon{
                        font-size: 14px;
                    }
                }
            }
        }
        .shop-main{
            .el-tabs__header{
                padding: 0 2rem;
                margin: 0;
            }
            .el-tabs__item{
                color: #303133;
            }
            .el-card{
                border: none;
                border-top-left-radius: 12px;
                border-top-right-radius: 12px;
            }
            .selected-title{
                padding-right: 0.4rem;
                color: #000;
                font-weight: 700;
            }
        }
    }
</style>