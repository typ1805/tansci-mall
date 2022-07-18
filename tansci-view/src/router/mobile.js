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
        path: '/miniapp/shop',
        name: 'shop',
        chineseName: '店铺',
        type: 2,
        meta: {title: "店铺"},
        component: () => import('@/views/mobile/Shop.vue')
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
        path: '/miniapp/orderList',
        name: 'orderList',
        chineseName: '订单列表',
        type: 2,
        meta: {title: "订单列表"},
        component: () => import('@/views/mobile/OrderList.vue')
    },
    // =======================我的=============start=======================================
    {
        path: '/miniapp/settings',
        name: 'settings',
        chineseName: '我的',
        type: 2,
        meta: {title: "我的"},
        component: () => import('@/views/mobile/Settings.vue')
    },
    {
        path: '/miniapp/accountSetting',
        name: 'accountSetting',
        chineseName: '账户设置',
        type: 2,
        meta: {title: "账户设置"},
        component: () => import('@/views/mobile/setting/AccountSetting.vue')
    },
    {
        path: '/miniapp/addressEdit',
        name: 'addressEdit',
        chineseName: '地址管理',
        type: 2,
        meta: {title: "地址管理"},
        component: () => import('@/views/mobile/setting/AddressEdit.vue')
    },
    {
        path: '/miniapp/coupon',
        name: 'coupon',
        chineseName: '优惠券',
        type: 2,
        meta: {title: "优惠券"},
        component: () => import('@/views/mobile/setting/Coupon.vue')
    },
    {
        path: '/miniapp/integral',
        name: 'integral',
        chineseName: '我的积分',
        type: 2,
        meta: {title: "我的积分"},
        component: () => import('@/views/mobile/setting/Integral.vue')
    },
    {
        path: '/miniapp/member',
        name: 'member',
        chineseName: '我的会员',
        type: 2,
        meta: {title: "我的会员"},
        component: () => import('@/views/mobile/setting/Member.vue')
    },
    {
        path: '/miniapp/customerService',
        name: 'customerService',
        chineseName: '客户服务',
        type: 2,
        meta: {title: "客户服务"},
        component: () => import('@/views/mobile/setting/CustomerService.vue')
    },
     // =======================我的=============end=======================================
    
]

export default mobile