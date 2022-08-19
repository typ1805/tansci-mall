import { createRouter, createWebHistory} from 'vue-router'
import Layout from '@/layout/Layout.vue'
import {menuList} from '@/api/admin/menu'
import {useMenuStore} from '@/store/settings'

import common from './common'
import mobile from './mobile'

// createRouter 创建路由实例
const router = createRouter({
    /**
     * hash模式：createWebHashHistory，
     * history模式：createWebHistory
     */
    history: createWebHistory(),
    routes:[
        ...common,
        ...mobile
    ]
})

let flag = true // 刷新标识
router.beforeEach(async (to, from, next) => {

    // 设置头部
    if (to.meta.title) {
        document.title = to.meta.title
    } else {
        document.title = "商城"
    }

    // 判断App是否需要登录
    if(to.meta.auth && !sessionStorage.getItem('token')){
        return next({ path: "/app/appLogin" });
    }

    // 判断App不加载动态路由
    let isApp = false;
    if(to.meta.type == 'app'){
        isApp = true;
    }

    // 动态添加路由
    if(sessionStorage.getItem('token') && flag && !isApp){
        const menuStore = useMenuStore();
        await menuList({types:'0,1', status: 1}).then((res)=>{
            let result = routerFilter(res.result)
            result.push({path:'/:pathMatch(.*)*', redirect:'/404'})
            result.forEach((item) => {
                router.addRoute(item)
            })
            menuStore.setMenu([...result])
            flag = false
            next({ ...to, replace: true })
        }).catch((error)=>{
            console.log(error)
            next()
		})
    } else{
        next()
    }
})

// 格式化路由
let modules = import.meta.glob('../views/**/**/*.vue')
export function routerFilter(data) {
    data.forEach((item)=>{
        let flag = false;
        if(item.parentId == 0){
            item.path = '/' + item.name;
            item.name = item.name;
            item.chineseName = item.chineseName;
            item.englishName = item.englishName;
            item.icon = item.icon;
            item.meta = { title: item.chineseName };
            item.redirect = item.name;
            item.component = Layout;
            if(!item.children || item.children.length == 0){
                item.children = [{
                    path: '/' + item.name,
                    name: item.name,
                    icon: item.icon,
                    chineseName: item.chineseName,
                    englishName: item.englishName,
                    meta: { title: item.chineseName },
                    // component: () => import('../views' + item.url + '.vue')
                    component: modules['../views' + item.url + '.vue']
                }];
                flag = true;
            }
        } else {
            item.path = '/' + item.name;
            item.name = item.name;
            item.chineseName = item.chineseName;
            item.englishName = item.englishName;
            item.icon = item.icon;
            item.meta = { title: item.chineseName };
            // item.component = () => import('../views' + item.url + '.vue');
            item.component = modules['../views' + item.url + '.vue'];
        }
        if(item.children && item.children.length && !flag){
            routerFilter(item.children)
        }
    })
    return data;
}

export default router