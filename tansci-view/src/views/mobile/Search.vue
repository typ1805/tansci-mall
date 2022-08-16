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
    import {getGoodsPage} from '@/api/mobile/goods'

    const state = reactive({
        defaultHeight: null,
        searchFrom: {
            name: null
        },
        page: {
            current: 1,
            size: 10,
            total: 1,
        },
        goodsList: [], 
    })

    const {
        defaultHeight,searchFrom,page,goodsList
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 68 + "px";
    })

    const onSearch = (val) =>{
        if(val && val != ''){
            state.searchFrom.name = val;
            onGoodsPage()
        }
    }

    const onGoodsPage = () =>{
        getGoodsPage(Object.assign(state.page, state.searchFrom)).then(res=>{
            state.goodsList = res.result.records;
        })
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