<template>
    <div class="index">
        <div class="index-search">
            <Search></Search>
        </div>
        <el-scrollbar :height="defaultHeight">
            <div class="index-card">
                <el-card :shadow="shadow" :body-style="{padding: '1rem 0.2rem'}"> 
                    <div class="card-shop">
                        <div v-for="shop in shopList" :key="shop" class="card-shop-item">
                            <el-icon :color="shop.color" :size="40">
                                <component :is="shop.icon"></component>
                            </el-icon>
                            <div>{{shop.name}}</div>
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

    const state = reactive({
        shadow: 'always',
        defaultHeight: null,
        shopList: [], // 热门店铺
        seckillList: [], // 秒杀商品
        rankingList: [], // 排行榜
        goodsList: [], // 热门商品
        carouselList: [], // 轮播商品
    })

    const {
        shadow,defaultHeight,shopList,seckillList,rankingList,goodsList,carouselList,
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 100 + "px";
    })

    onMounted(() => {
        onShopList();
        onSeckillList();
        onRankingList();
        onRecommendList();
    })

    const onShopList = () =>{
        state.shopList = [
            { icon: 'AlarmClock', color:'#409EFC', name: '乐购超市', },
            { icon: 'Apple', color:'#67C23A', name: '乐购手机', },
            { icon: 'Bicycle', color:'#E6A23C', name: '乐购家电', },
            { icon: 'Cherry', color:'#F56C6C', name: '乐购百货', },
            { icon: 'ColdDrink', color:'#3cba92', name: '充值中心', },
            { icon: 'Film', color:'#72afd3', name: '新潮配件', },
            { icon: 'HotWater', color:'#fee140', name: '领券中心', },
            { icon: 'ElementPlus', color:'#f68084', name: '生鲜到家', },
            { icon: 'Goblet', color:'#8fd3f4', name: '下单立返', },
            { icon: 'IceTea', color:'#d4fc79', name: '二手拍拍', },
        ]
    }

    const onSeckillList = () =>{
        state.seckillList = [
            {
                shopId: '1001',
                goods: [
                    {
                        goodsId: 'g00007',
                        name: '闪购1',
                        image: 'https://m.360buyimg.com/babel/s200x200_jfs/t1/194840/6/24456/152551/628b821aE645d930b/5234cfcb0977ef2f.jpg.avif'
                    },
                    {
                        goodsId: 'g00008',
                        name: '闪购2',
                        image: 'https://m.360buyimg.com/babel/s200x200_jfs/t1/212866/38/20554/787389/62bec908Ec696a01c/6f7874c0cea7f511.jpg.avif'
                    }
                ]
            },
            {
                shopId: '1002',
                goods: [
                    {
                        goodsId: 'g00009',
                        name: '风格',
                        image: 'https://m.360buyimg.com/babel/s200x200_jfs/t1/185855/21/18986/193252/6115cb9bE32c87205/7cc8d08c561f0ef5.jpg.avif'
                    },
                    {
                        goodsId: 'g000010',
                        name: '发广告',
                        image: 'https://m.360buyimg.com/babel/s200x200_jfs/t1/68195/15/19567/185393/62c30dddE76149d2a/993ae8b50f25c829.jpg.avif'
                    }
                ]
            },
            {
                shopId: '1003',
                goods: [
                    {
                        goodsId: 'g000011',
                        name: '很过分',
                        image: 'https://m.360buyimg.com/babel/s200x200_jfs/t1/92988/1/18197/298442/6263a873E03c4fd33/a8978e8917f6bc79.jpg.avif'
                    },
                    {
                        goodsId: 'g000012',
                        name: '共和国',
                        image: 'https://m.360buyimg.com/babel/s200x200_jfs/t1/92086/32/28700/664359/6281ffaeEdca15717/a07c38c01bc1ec6a.jpg.avif'
                    }
                ]
            }
        ]
    }

    const onRankingList = () =>{
        state.rankingList = [
            {
                shopId: '1001',
                goods: [
                    {
                        goodsId: 'g00001',
                        name: '闪购1',
                        image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/71241/18/20466/243329/62bfd7fdE7206400c/7acd644d86f367de.jpg.avif'
                    },
                    {
                        goodsId: 'g00002',
                        name: '闪购2',
                        image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/101509/24/30810/163038/62ba60cbE65baa74a/88628943ed2025c7.jpg.avif'
                    }
                ]
            },
            {
                shopId: '1002',
                goods: [
                    {
                        goodsId: 'g00003',
                        name: '风格',
                        image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/62238/2/19413/220830/62b0abeaE4acbc470/add9ae4ee0c022c3.jpg.avif'
                    },
                    {
                        goodsId: 'g00004',
                        name: '发广告',
                        image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/181621/38/26357/228605/62c3a488E491bc614/b10cb208f31ccf8d.jpg.avif'
                    }
                ]
            },
            {
                shopId: '1003',
                goods: [
                    {
                        goodsId: 'g00005',
                        name: '很过分',
                        image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/129295/6/21878/150787/61db851aEc11c8314/d8434671bca9d638.jpg.avif'
                    },
                    {
                        goodsId: 'g00006',
                        name: '共和国',
                        image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/49220/21/19821/231177/62b0971eE7eb32528/f3daeb06247bb558.jpg.avif'
                    }
                ]
            }
        ]
    }

    const onRecommendList = () =>{
        state.carouselList = [
            {goodsId: 1, image: 'https://m.360buyimg.com/babel/s1000x2000_jfs/t1/117064/40/26924/138402/62b55f9dE1e33b404/4113ba35a942ec15.png.avif'},
            {goodsId: 2, image: 'https://m.360buyimg.com/babel/s800x1000_jfs/t1/216574/9/20786/109035/62b5748cE2c79da73/2e9634a5275fc246.png.avif'},
            {goodsId: 3, image: 'https://m.360buyimg.com/babel/s800x1000_jfs/t1/193025/29/25347/137721/62b561cfE97ac1235/18ef380a8f11ab5b.png.avif'},
            {goodsId: 3, image: 'https://m.360buyimg.com/babel/s800x1000_jfs/t1/65230/4/19689/112010/62b55d66E434e50f1/16512285c9a368aa.png.avif'},
        ]
        state.goodsList = [
            {
                goodsId: 'g00001',
                name: '可配置定义更丰富的内容展',
                price: 147.00,
                comment: 200,
                rating: 48,
                image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/151287/11/6299/88753/5fab4128E87c46fe5/769a497c164a554c.jpg.avif',
            },
            {
                goodsId: 'g00002',
                name: '可配置定义更丰富的内容展',
                price: 447.00,
                comment: 200,
                rating: 48,
                image: 'https://m.360buyimg.com/babel/s200x200_jfs/t1/106777/29/30068/284228/62be64faE956a8da2/e4b08f6349798e56.jpg.avif',
            },
            {
                goodsId: 'g00003',
                name: '可配置定义更丰富的内容展',
                price: 47.00,
                comment: 200,
                rating: 48,
                image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/132097/2/22280/370382/62bab80aE34809e28/055504818282cd3d.jpg.avif',
            },
            {
                goodsId: 'g00004',
                name: '可配置定义更丰富的内容展',
                price: 472.00,
                comment: 200,
                rating: 48,
                image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/2828/33/19368/238363/62bbbe12Ee47c0996/2c92c0a2ebc78f0c.jpg.avif',
            },
            {
                goodsId: 'g00005',
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