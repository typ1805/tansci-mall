<template>
    <div class="shopping-cart">
        <div class="cart-header">
            <el-card :shadow="shadow" :body-style="{padding: '0.8rem'}">
                <div class="header-card">
                    <div>
                        <el-icon><ShoppingCartFull /></el-icon>
                        <span style="padding-left: 0.2rem;">我的购物车</span>
                    </div>
                    <div>
                        <el-divider direction="vertical" />
                        <el-button @click="onEdit" type="primary" link>
                            <span v-if="!isEdit">编辑</span>
                            <span v-else>完成</span>
                        </el-button>
                    </div>
                </div>
            </el-card>
        </div>
        <el-scrollbar :height="defaultHeight">
            <div v-if="cartList && cartList.length > 0" class="cart-main">
                <el-card v-for="item in cartList" :key="item" :shadow="shadow" :body-style="{padding: '0px'}">  
                    <template #header>
                        <el-checkbox v-model="item.isSelect" @change="onBoxChange(item.shopId,null)" :true-label="1" :false-label="0"></el-checkbox>
                        <el-button icon="Shop" type="primary" link>
                            {{item.shopName}}<el-icon><ArrowRight /></el-icon>
                        </el-button>
                    </template>
                    <div v-for="(goods,index) in item.goodsList" :key="index" @click="toGoodsDetail(goods.goodsId)" class="cart-list" :style="{borderBottom: index+1 == item.goodsList.length?'':'1px solid #F0F2F5'}">
                        <div class="goods-checkbox">
                            <el-checkbox v-model="goods.isSelect" @change="onBoxChange(item.shopId,goods.goodsId)" :true-label="1" :false-label="0"></el-checkbox>
                        </div>
                        <div class="goods-image">
                            <el-image :src="goods.image" style="width: 120px; height: 120px;" fit="fit"/>
                        </div>
                        <div class="goods-content">
                            <div class="content-title">{{goods.name}}</div>
                            <div class="content-price">
                                <span style="font-size: 12px;">￥</span><span>{{onDecimal(goods.price)}}</span>
                            </div>
                            <div class="content-number">
                                <el-input-number v-model="goods.number" :min="1" :max="10" size="small" style="width:86px;"/>
                            </div>
                        </div>
                    </div>
                </el-card>
            </div>
            <el-empty v-else :image-size="200" description="购物车空空如也，去逛逛吧~">
                <el-button @click="toIndex" type="danger" round>继续购物</el-button>
            </el-empty>
        </el-scrollbar>
        <div class="cart-footer">
            <div class="footer-settlement">
                <div v-if="!isEdit" class="settlement-submit">
                    <div>总计：<span style="font-weight: 700;font-size: 18px;">￥{{onDecimal(totalAmount)}}</span></div>
                    <el-button @click="onSubmit" :disabled="totalAmount > 0 ? false:true" type="danger" round>立即结算</el-button>
                </div>
                <div v-else class="settlement-delete">
                    <el-button @click="onDelSubmit" type="danger" round>删除</el-button>
                </div>
            </div>
            <div class="footer-menu">
                <FooterMenu></FooterMenu>
            </div>
        </div>
    </div>
</template>
<script setup>
    import {onBeforeMount, onMounted, reactive, toRefs} from 'vue'
    import {useRouter} from 'vue-router'
    import FooterMenu from '@/components/miniapp/FooterMenu.vue'
    import {toDecimal} from '@/utils/utils'

    const router = useRouter()
    const state = reactive({
        shadow: 'always',
        defaultHeight: null,
        cartList: [],
        totalAmount: 0,
        isEdit: false,
    })

    const {
        shadow,defaultHeight,cartList,totalAmount,isEdit
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 130 + "px";
    })

    onMounted(()=>{
        onCartList();
    })

    // 购物车为空，跳转至首页
    const toIndex = () =>{
        router.push({path: '/miniapp/index'})
    }

    // 购物车列表
    const onCartList = () =>{
        state.cartList = [
            {
                shopId: '10001',
                shopName: '慢慢旗舰店',
                isSelect: 1,
                goodsList: [
                    {
                        goodsId: 'g0001',
                        name: '可配置定义更丰富的规范和地方法官和对方内容展',
                        price: 147.00,
                        number: 1,
                        isSelect: 1,
                        image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/151287/11/6299/88753/5fab4128E87c46fe5/769a497c164a554c.jpg.avif',
                    },
                    {
                        goodsId: 'g0002',
                        name: '可配置定义更法规和豆腐干和的丰富的内容展',
                        price: 144.00,
                        number: 1,
                        isSelect: 1,
                        image: 'https://29553125.s61i.faiusr.com/2/AD0I5eOLDhACGAAgv5PvlQYozJLmiAIw7gU47gU!400x400.jpg',
                    }
                ]
            },
            {
                shopId: '10002',
                shopName: '红线尔康旗舰店',
                isSelect: 1,
                goodsList: [
                    {
                        goodsId: 'g0003',
                        name: '可配置定义更丰法规和豆腐干和的富的内容展',
                        price: 144.00,
                        number: 1,
                        isSelect: 1,
                        image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/2828/33/19368/238363/62bbbe12Ee47c0996/2c92c0a2ebc78f0c.jpg.avif',
                    }
                ]
            },
            {
                shopId: '10002',
                shopName: '金发地个旗舰店',
                isSelect: 0,
                goodsList: [
                    {
                        goodsId: 'g0004',
                        name: '可配置定义更法规和豆腐干和的发丰富的内容展',
                        price: 144.00,
                        number: 1,
                        isSelect: 0,
                        image: 'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/87173/11/29471/354593/62adf573Eac278253/3bc086c45af2cce4.jpg.avif',
                    }
                ]
            }
        ]

        onCalculatedAmount();
    }

    // 提价
    const onSubmit = () =>{
        let newCartList = [];
        state.cartList.forEach(shop=>{
            if(shop.isSelect === 1){
                newCartList.push(shop);
            } else {
                let shopInfo = JSON.parse(JSON.stringify(shop));
                shopInfo.goodsList = shopInfo.goodsList.filter(goods => goods.isSelect === 1)
                if(shopInfo.goodsList && shopInfo.goodsList.length > 0){
                    newCartList.push(shopInfo);
                }
            }
        })

        console.log(newCartList)
    }

    // 移除购物车
    const onDelSubmit = () =>{
        // 数组是引用类型, 深拷贝一下
        let cartArr = JSON.parse(JSON.stringify(state.cartList));
        cartArr.forEach((shop, index) =>{
            let goodsArr = JSON.parse(JSON.stringify(shop.goodsList));
            let newGoodsArr = goodsArr.filter(goods => goods.isSelect === 0);
            console.log(newGoodsArr)
            if(newGoodsArr.length === 0){
                cartArr.splice(index,1);
            } else {
                shop.goodsList = newGoodsArr;
            }
        })
        state.cartList = cartArr;
    }

    // 购物车编辑
    const onEdit = () =>{
        if(state.isEdit){
            state.isEdit = false;
            onCalculatedAmount();
        } else {
            state.isEdit = true;
        }
    }

    // 提交选中&取消
    const onBoxChange = (shopId, goodsId) =>{
        state.cartList.filter((cart)=>{
            if(cart.shopId == shopId){
                if(goodsId){
                    let flag = true;
                    cart.goodsList.forEach(goods=>{
                        if(goods.isSelect === 0){
                            flag = false;
                            return;
                        }
                    })
                    if(flag){
                        cart.isSelect = 1;
                    } else {
                        cart.isSelect = 0;
                    }
                } else {
                    if(cart.isSelect === 1){
                        cart.goodsList.forEach(goods=>{
                            goods.isSelect = 1;
                        })
                    } else {
                        cart.goodsList.forEach(goods=>{
                            goods.isSelect = 0;
                        })
                    }
                }
                return;
            }
        })
        onCalculatedAmount();
    }

    // 计算总金额
    const onCalculatedAmount = () =>{
        let _cartList = state.cartList;
        let _totalAmount = 0;
        _cartList.forEach(item=>{
            item.goodsList.forEach(goods=>{
                if(goods.isSelect){
                    _totalAmount = _totalAmount + goods.price;
                }
            })
        })
        state.totalAmount = _totalAmount
    }

    // 金额格式化
    function onDecimal(val){
        return toDecimal(val);
    }

    // 商品详情
    const toGoodsDetail = (goodsId) =>{
        router.push({
            path: '/miniapp/goodsDetail',
            query:{
                goodsId: goodsId
            }
        })
    }

</script>
<style lang="scss">
    .shopping-cart{
        .cart-header{
            .el-card{
                border-radius: 0;
                border-bottom-left-radius: 8px;
                border-bottom-right-radius: 8px;
            }
            .header-card{
                display: flex;
                justify-content: space-between;
                span, i{
                    vertical-align: middle;
                }
            }
        }
        .cart-main{
            padding: 0.2rem;
            i{
                vertical-align: middle;
            }
            .el-card{
                margin-bottom: 0.4rem;
                .el-card__header{
                    padding: 0.8rem;
                }
            }
            .el-checkbox{
                vertical-align: middle;
                height: 20px;
                margin-right: 1rem;
                .el-checkbox__inner{
                    border-radius: 50%;
                    width: 20px;
                    height: 20px;
                }
                .el-checkbox__inner::after {
                    height: 10px;
                    width: 4px;
                    left: 7px;
                    top: 2px;
                }
                .el-checkbox__input.is-checked .el-checkbox__inner{
                    background-color: red;
                    border-color: red;
                }
            }
            .cart-list{
                display: flex;
                justify-content: flex-start;
                align-items: stretch;
                padding-bottom: 0.2rem;
                margin-bottom: 0.4rem;
                .goods-checkbox{
                    margin: auto 0;
                    padding-left: 0.8rem;
                }
                .goods-image{
                    padding: 0.4rem;
                    .el-image{
                        border-radius: 8px;
                    }
                }
                .goods-content{
                    padding: 0 0.4rem;
                    margin: auto 0;
                    .content-title{
                        font-size: 14px;
                        padding-bottom: 0.6rem;
                        overflow: hidden;
                        display: -webkit-box;
                        -webkit-line-clamp: 2;
                        line-clamp: 2;
                        -webkit-box-orient: vertical;
                    }
                    .content-price{
                        float: left;
                        color: red;
                        font-weight: 700;
                        font-size: 16px;
                        padding-bottom: 0.6rem;
                    }
                    .content-number{
                        float: right;
                    }
                }
            }
            .el-input-number__increase, .el-input-number__decrease{
                background: #fff;
                color: #000;
                border: none;
            }
            .el-input__wrapper{
                box-shadow: none;
            }
            .el-input--small .el-input__inner{
                background-color: #F0F2F5;
            }
            .el-divider--horizontal{
                margin: 0.8rem;
            }
        }
        .cart-footer{
            .footer-settlement{
                .settlement-submit{
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                    padding: 0.4rem 1rem;
                    border-top: 1px solid #F0F2F5;
                }
                .settlement-delete{
                    text-align: right;
                    padding: 0.4rem 1rem;
                    border-top: 1px solid #F0F2F5;
                }
            }
        }
    }
</style>