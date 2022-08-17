const mobile = [
    {
        path: '/app/index',
        name: 'index',
        chineseName: '首页',
        type: 2,
        meta: {
            title: "首页",
            type: 'app',
            auth: false,
        },
        component: () => import('@/views/mobile/Index.vue')
    },
    {
        path: '/app/search',
        name: 'search',
        chineseName: '搜索',
        type: 2,
        meta: {
            title: "搜索",
            type: 'app',
            auth: false,
        },
        component: () => import('@/views/mobile/Search.vue')
    },
    {
        path: '/app/cart',
        name: 'cart',
        chineseName: '购物车',
        type: 2,
        meta: {
            title: "购物车",
            type: 'app',
            auth: true,
        },
        component: () => import('@/views/mobile/Cart.vue')
    },
    {
        path: '/app/shop',
        name: 'shop',
        chineseName: '店铺',
        type: 2,
        meta: {
            title: "店铺",
            type: 'app',
            auth: false,
        },
        component: () => import('@/views/mobile/Shop.vue')
    },
    {
        path: '/app/goodsDetail',
        name: 'goodsDetail',
        chineseName: '商品详情',
        type: 2,
        meta: {
            title: "商品详情",
            type: 'app',
            auth: false,
        },
        component: () => import('@/views/mobile/GoodsDetail.vue')
    },
    {
        path: '/app/order',
        name: 'order',
        chineseName: '订单',
        type: 2,
        meta: {
            title: "订单",
            type: 'app',
            auth: true,
        },
        component: () => import('@/views/mobile/Order.vue')
    },
    {
        path: '/app/orderList',
        name: 'orderList',
        chineseName: '订单列表',
        type: 2,
        meta: {
            title: "订单列表",
            type: 'app',
            auth: true,
        },
        component: () => import('@/views/mobile/OrderList.vue')
    },
    // =======================我的=============start=======================================
    {
        path: '/app/settings',
        name: 'settings',
        chineseName: '我的',
        type: 2,
        meta: {
            title: "我的",
            type: 'app',
            auth: true,
        },
        component: () => import('@/views/mobile/Settings.vue')
    },
    {
        path: '/app/accountSetting',
        name: 'accountSetting',
        chineseName: '账户设置',
        type: 2,
        meta: {
            title: "账户设置",
            type: 'app',
            auth: true,
        },
        component: () => import('@/views/mobile/setting/AccountSetting.vue')
    },
    {
        path: '/app/addressEdit',
        name: 'addressEdit',
        chineseName: '地址管理',
        type: 2,
        meta: {
            title: "地址管理",
            type: 'app',
            auth: true,
        },
        component: () => import('@/views/mobile/setting/AddressEdit.vue')
    },
    {
        path: '/app/coupon',
        name: 'coupon',
        chineseName: '优惠券',
        type: 2,
        meta: {
            title: "优惠券",
            type: 'app',
            auth: true,
        },
        component: () => import('@/views/mobile/setting/Coupon.vue')
    },
    {
        path: '/app/integral',
        name: 'integral',
        chineseName: '我的积分',
        type: 2,
        meta: {
            title: "我的积分",
            type: 'app',
            auth: true,
        },
        component: () => import('@/views/mobile/setting/Integral.vue')
    },
    {
        path: '/app/member',
        name: 'member',
        chineseName: '我的会员',
        type: 2,
        meta: {
            title: "我的会员",
            type: 'app',
            auth: true,
        },
        component: () => import('@/views/mobile/setting/Member.vue')
    },
    {
        path: '/app/customerService',
        name: 'customerService',
        chineseName: '客户服务',
        type: 2,
        meta: {
            title: "客户服务",
            type: 'app',
            auth: true,
        },
        component: () => import('@/views/mobile/setting/CustomerService.vue')
    },
    // =======================我的=============end=======================================

    // ===================登录、注册========start=====================
    {
        path: '/app/appLogin',
        name: 'appLogin',
        chineseName: '登录',
        type: 2,
        meta: {
            title: "登录",
            type: 'app',
            auth: false,
        },
        component: () => import('@/views/mobile/Login.vue')
    },
    {
        path: '/app/appRegister',
        name: 'appRegister',
        chineseName: '注册',
        type: 2,
        meta: {
            title: "注册",
            type: 'app',
            auth: false,
        },
        component: () => import('@/views/mobile/Register.vue')
    },
    // ===================登录、注册========end=====================
    
]

export default mobile