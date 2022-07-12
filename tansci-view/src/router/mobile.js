const mobile = [
    {
        path: '/miniapp/index',
        name: 'index',
        chineseName: '首页',
        type: 2,
        meta: {title: "首页"},
        component: () => import('@/views/mobile/Index.vue')
    },
    {
        path: '/miniapp/search',
        name: 'search',
        chineseName: '搜索',
        type: 2,
        meta: {title: "搜索"},
        component: () => import('@/views/mobile/Search.vue')
    },
    {
        path: '/miniapp/cart',
        name: 'cart',
        chineseName: '购物车',
        type: 2,
        meta: {title: "购物车"},
        component: () => import('@/views/mobile/Cart.vue')
    },
    {
        path: '/miniapp/settings',
        name: 'settings',
        chineseName: '我的',
        type: 2,
        meta: {title: "我的"},
        component: () => import('@/views/mobile/Settings.vue')
    },
    {
        path: '/miniapp/goodsDetail',
        name: 'goodsDetail',
        chineseName: '商品详情',
        type: 2,
        meta: {title: "商品详情"},
        component: () => import('@/views/mobile/GoodsDetail.vue')
    },
    {
        path: '/miniapp/order',
        name: 'order',
        chineseName: '订单',
        type: 2,
        meta: {title: "订单"},
        component: () => import('@/views/mobile/Order.vue')
    },
    {
        path: '/miniapp/shop',
        name: 'shop',
        chineseName: '店铺',
        type: 2,
        meta: {title: "店铺"},
        component: () => import('@/views/mobile/Shop.vue')
    },

]

export default mobile