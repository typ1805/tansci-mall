<template>
    <div class="menu-container">
        <el-menu router :default-active="$route.path" mode="horizontal" :ellipsis="false" active-text-color="#ff0844" background-color="#ffffff">
            <el-menu-item v-for="menu in menuList" :key="menu" :index="menu.path">
                <el-icon color="#303133" :size="20">
                    <component :is="menu.icon"></component>
                </el-icon>
                <div>{{menu.name}}</div>
            </el-menu-item>
        </el-menu>
    </div>
</template>
<script setup>
    import {onMounted, reactive, toRefs} from 'vue'

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

</script>
<style lang="scss">
    .menu-container{
        position: fixed;
        left: 0;
        bottom: 0;
        border-top: 1px solid #e4e7ed;
        .el-menu{
            border: none;
            .el-menu-item, .el-sub-menu__title{
                height: 40px;
                line-height: 40px;
            }
            .el-menu-item{
                padding: 0 17px;
            }
            .el-menu-item.is-active{
                border: none !important;
            }
            .el-sub-menu__title:hover{
                background: #fff !important;
            }
            .el-menu-item:hover{
                background: #fff !important;
            }
        }
    }
</style>