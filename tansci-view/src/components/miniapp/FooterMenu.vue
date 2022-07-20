<template>
    <div class="menu-container">
        <div class="menu-item" v-for="menu in menuList" :key="menu" @click="onMenu(menu.path)"
            :class="{'is-active': $route.path == menu.path}">
            <div>
                <el-icon :color="$route.path == menu.path?'#ff0844':'#303133'" :size="20">
                    <component :is="menu.icon"></component>
                </el-icon>
            </div>
            <div>{{menu.name}}</div>
        </div>
    </div>
</template>
<script setup>
    import {onMounted, reactive, toRefs} from 'vue'
    import {useRouter} from 'vue-router'

    const router = useRouter()
    const state = reactive({
        menuList: [],
    })

    const {
        menuList,
    } = toRefs(state)

    onMounted(() => {
        onMenuList();
    })

    const onMenuList = () =>{
        state.menuList = [
            {path: '/miniapp/index', name: '首页', icon: 'House'},
            {path: '/miniapp/search', name: '搜索', icon: 'Search'},
            {path: '/miniapp/cart', name: '购物车', icon: 'ShoppingCart'},
            {path: '/miniapp/settings', name: '我的', icon: 'User'},
        ]
    }

    const onMenu = (val) =>{
        router.push({path: val})
    }

</script>
<style lang="scss">
    .menu-container{
        width: 100%;
        position: fixed;
        display: flex;
        justify-content: space-between;
        align-items: stretch;
        left: 0;
        bottom: 0;
        background: #ffffff;
        border-top: 1px solid #e4e7ed;      
        .menu-item{
            text-align: center;
            padding: 0.4rem 1.2rem;
        }
        .is-active{
            color: #ff0844;
        }
    }
</style>