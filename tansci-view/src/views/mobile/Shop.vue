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
            <el-tabs v-model="tabAactive" :stretch="true" @tab-click="handleClick">
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

    const state = reactive({
        shadow: 'never',
        defaultHeight: null,
        tabAactive: 'selected',
        shopInfo: {},
        goodsList: [],
    })

    const {
        shadow,defaultHeight,tabAactive,shopInfo,goodsList
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 150 + "px";
    })

    onMounted(()=>{
        onShopInfo();
        onGoodsList();
    })

    const onShopInfo = () =>{
        state.shopInfo = {
            shopId: 's10001',
            name: '鸿星尔克安宁旗舰店',
            avatar: 'https://www.baidu.com/img/flexible/logo/pc/result.png',
            level: 4.5,
        }
    }

    const onGoodsList = () =>{
        state.goodsList = [
            {
                goodsId: 'g00001',
                name: '可配置定义更丰富的内容展',
                price: 147.00,
                comment: 200,
                rating: 48,
                image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/151287/11/6299/88753/5fab4128E87c46fe5/769a497c164a554c.jpg.avif',
                labelList: [
                    {name:'新品', type:'success'},
                ]
            },
            {
                goodsId: 'g00002',
                name: '可配置定义更丰富的内容展',
                price: 447.00,
                comment: 200,
                rating: 48,
                image: 'https://29553125.s61i.faiusr.com/2/AD0I5eOLDhACGAAgv5PvlQYozJLmiAIw7gU47gU!400x400.jpg',
                labelList: [
                    {name:'爆品', type:'danger'},
                ]
            },
            {
                goodsId: 'g00003',
                name: '可配置定义更丰富的内容展',
                price: 447.00,
                comment: 200,
                rating: 48,
                image: 'https://m.360buyimg.com/babel/s200x200_jfs/t1/106777/29/30068/284228/62be64faE956a8da2/e4b08f6349798e56.jpg.avif',
                labelList: [
                    {name:'新品', type:'success'},
                    {name:'爆品', type:'danger'},
                ]
            },
            {
                goodsId: 'g00004',
                name: '可配置定义更丰富的内容展',
                price: 47.00,
                comment: 200,
                rating: 48,
                image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/132097/2/22280/370382/62bab80aE34809e28/055504818282cd3d.jpg.avif',
                labelList: [
                    {name:'秒杀', type:'warning'},
                ]
            },
            {
                goodsId: 'g00005',
                name: '可配置定义更丰富的内容展',
                price: 472.00,
                comment: 200,
                rating: 48,
                image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/2828/33/19368/238363/62bbbe12Ee47c0996/2c92c0a2ebc78f0c.jpg.avif',
                labelList: [
                    {name:'爆品', type:'danger'},
                ]
            },
            {
                goodsId: 'g00006',
                name: '可配置定义更丰富的内容展',
                price: 472.00,
                comment: 200,
                rating: 48,
                image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/87173/11/29471/354593/62adf573Eac278253/3bc086c45af2cce4.jpg.avif',
            },
            {
                goodsId: 'g00007',
                name: '可配置定义更丰富的内容展',
                price: 472.00,
                comment: 200,
                rating: 48,
                image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/87173/11/29471/354593/62adf573Eac278253/3bc086c45af2cce4.jpg.avif',
            },
        ]
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