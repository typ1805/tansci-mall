<template>
    <div class="search">
        <div class="search-header">
            <Search @onSearch="onSearch"></Search>
        </div>
        <el-scrollbar :height="defaultHeight">
            <div class="search-main">
                <GoodsList :goodsList="goodsList" mode="horizontal"></GoodsList>
            </div>
        </el-scrollbar>
        <div class="search-footer">
            <FooterMenu></FooterMenu>
        </div>
    </div>
</template>
<script setup>
    import {onBeforeMount, reactive, toRefs} from 'vue'
    import Search from '@/components/miniapp/Search.vue'
    import GoodsList from '@/components/miniapp/GoodsList.vue'
    import FooterMenu from '@/components/miniapp/FooterMenu.vue'

    const state = reactive({
        defaultHeight: null,
        searchFrom: {
            keyword: null
        },
        goodsList: [], 
    })

    const {
        defaultHeight,searchFrom,goodsList
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 68 + "px";
    })

    const onSearch = (val) =>{
        if(val && val != ''){
            onRecommendList()
        }
    }

    const onRecommendList = () =>{
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
    .search{
        background: #F2F3F5;
        .search-header{
            padding: 0.4rem;
        }
        .search-main{
            padding: 0 0.4rem;
        }
    }
</style>