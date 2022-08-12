<template>
    <div class="goods-detail">
        <div class="detail-header">
            <el-page-header @back="toBack" icon="ArrowLeft" title="返回">
                <template #content>
                    <span class="content">商品详情</span>
                </template>
            </el-page-header>
        </div>
        <el-scrollbar :height="defaultHeight">
            <div class="detail-main">
                <div class="main-carousel">
                    <el-carousel height="200px" :autoplay="true" :interval="5000">
                        <el-carousel-item v-for="image in goodsInfo.imageList" :key="image" style="height: 100%">
                            <el-image :src="image" style="width: 100%; height: 100%;" fit="fit"/>
                        </el-carousel-item>
                    </el-carousel>
                </div>
                <div class="main-content">
                    <el-card :shadow="shadow">
                        <div class="content-price">
                            <span style="font-size: 12px;">￥</span><span>{{onDecimal(goodsInfo.price)}}</span>
                        </div>
                        <div class="content-title">{{goodsInfo.name}}</div>
                        <div class="content-service">
                            <el-alert type="error" center :closable="false">
                                <template #default>
                                    <el-icon :size="16"><BellFilled /></el-icon>
                                    <span style="font-size: 16px;font-weight: 700;padding:0 1rem 0 0.4rem;">放心购</span>
                                    <span>免费上门取退-7天保价-极速审核</span>
                                </template>
                            </el-alert>
                        </div>
                    </el-card>
                </div>
                <div class="main-address">
                    <el-card :shadow="shadow">
                        <template #header>
                            <div class="card-header">
                                <span>我的地址</span>
                                <el-button icon="MoreFilled" type="primary" link></el-button>
                            </div>
                        </template>
                        <div class="address-content">
                            <div>
                                <span class="content-title">送至</span>
                                <span>{{goodsInfo.address}}</span>
                            </div>
                            <div style="padding: 0.4rem 0;">
                                <span class="content-service-title">现货</span>
                                <span class="content-service-text">今日17:30前完成下单，预计明天的22:00前发货</span>
                            </div>
                            <div>
                                <el-tag v-for="tag in tags" :key="tag" type="warning" size="small" effect="light" round style="margin: 0.2rem;">
                                    <el-icon color="#FF0000" :size="14">
                                        <component :is="tag.icon"></component>
                                    </el-icon>
                                    <span style="padding-left: 0.2rem;">{{tag.name}}</span>
                                </el-tag>
                            </div>
                        </div>
                    </el-card>
                </div>
                <div class="main-comment">
                    <el-card :shadow="shadow">
                        <template #header>
                            <div class="card-header">
                                <span>评价 {{goodsInfo.commentNum}}条</span>
                                <div>
                                    <span style="font-size: 12px;font-weight: 500;padding-right: 0.4rem;">好评度{{goodsInfo.commentRating}}%</span>
                                    <el-button icon="MoreFilled" type="primary" link></el-button>
                                </div>
                            </div>
                        </template>
                        <div v-if="goodsInfo.commentList && goodsInfo.commentList.length > 0">
                            <div v-for="(comment,index) in goodsInfo.commentList" :key="index" class="comment-content">
                                <div class="content-user">
                                    <div>
                                        <el-avatar :size="20" icon="UserFilled" style="color: #F56C6C;"/>
                                        <span style="padding:0 0.4rem; font-size: 12px;">{{comment.userName}}</span>
                                        <el-rate v-model="comment.rating" :max="5" size="small" disabled/>
                                    </div>
                                    <div style="color:#909399;font-size: 12px;">{{comment.data}}</div>
                                </div>
                                <div class="content-text">{{comment.text}}</div>
                                <el-divider v-show="index+1 < goodsInfo.commentList.length" />
                            </div>
                            <div class="comment-but">
                                <el-button @click="onComment" round>查看全部评价</el-button>
                            </div>
                        </div>
                        <div v-else style="text-align: center; color: #909399;">暂无评价</div>
                    </el-card>
                    <el-drawer v-model="commentDrawer" :show-close="false" size="80%" direction="btt">
                        <template #header>
                            <span>商品评价</span>
                            <el-icon @click="commentDrawer = false" class="el-icon--left"><CircleCloseFilled /></el-icon>
                        </template>
                        <div v-if="commentList && commentList.length">
                            <div v-for="(comment,index) in commentList" :key="index" class="comment-content">
                                <div class="content-user">
                                    <div>
                                        <el-avatar :size="20" icon="UserFilled" style="color: #F56C6C;"/>
                                        <span style="padding:0 0.4rem; font-size: 12px;">{{comment.userName}}</span>
                                        <el-rate v-model="comment.rating" :max="5" size="small" disabled/>
                                    </div>
                                    <div style="color:#909399;font-size: 12px;">{{comment.data}}</div>
                                </div>
                                <div class="content-text">{{comment.text}}</div>
                                <el-divider v-show="index+1 < commentList.length" />
                            </div>
                        </div>
                        <el-empty v-else description="暂无评价" />
                    </el-drawer>
                </div>
                <div class="main-detail">
                    <el-card :shadow="shadow">
                        <template #header>
                            <div class="card-header">
                                <span>详情</span>
                                <el-button icon="MoreFilled" type="primary" link></el-button>
                            </div>
                        </template>
                        <div class="detail-content">
                            {{goodsInfo.detail}}
                        </div>
                    </el-card>
                </div>
            </div>
        </el-scrollbar>
        <div class="detail-footer">
            <div class="footer-shop">
                <span @click="toShop">
                    <el-icon :size="24" color="#F56C6C"><Shop /></el-icon>
                    <span style="font-size: 12px;padding-right: 0.4rem;">店铺</span>
                </span>
                <span @click="onAddCart(1)">
                    <el-icon :size="24"><ShoppingCart /></el-icon>
                    <span style="font-size: 12px;">购物车</span>
                </span>
            </div>
            <div class="footer-submit">
                <el-button @click="onAddCart(0)" color="#FFD700" round>加入购物车</el-button>
                <el-button @click="onSubmit" type="danger" round>立即购买</el-button>
            </div>
        </div>
    </div>
</template>
<script setup>
    import {onBeforeMount, onMounted, reactive, toRefs} from 'vue'
    import {useRouter, useRoute} from 'vue-router'
    import {toDecimal} from '@/utils/utils'

    const router = useRouter()
    const route = useRoute()
    const state = reactive({
        shadow: 'always',
        defaultHeight: null,
        goodsInfo: {},
        tags:[
            {icon:'CircleCheck',name:'店铺发货&售后'},
            {icon:'CircleCheck',name:'可全球配送'},
            {icon:'CircleCheck',name:'7天无理由退货'},
            {icon:'CircleCheck',name:'15天保价'},
            {icon:'CircleCheck',name:'极速审核'},
        ],
        commentDrawer: false,
        commentList: [],
    })

    const {
        defaultHeight,goodsInfo,tags,commentDrawer,commentList
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 80 + "px";
    })

    onMounted(()=>{
        if(route.query.goodsId){
            onGoodsInfo(route.query.goodsId); 
        }
    })

    const onGoodsInfo = () =>{
        state.goodsInfo = {
            goodsId: route.query.goodsId,
            name: '鸿星尔克官方旗舰2022男鞋防滑透气舒适轻便时尚百搭休闲运动休闲鞋子男士夏季新款舒适 正黑 41',
            price: 147.00,
            address: '甘肃省兰州市安宁区银滩路街道北滨河路永新华世界湾1#1-803室',
            commentNum: 200,
            commentRating: 48,
            commentList: [
                {userName:'张三',rating:4,data:'2022-07-07',text:'网面的设计，夏天穿很透气不会猛将，的机房第三方反倒是尽快发哈市豆腐干绝代风华申达股份。'},
                {userName:'李四',rating:4.5,data:'2022-07-07',text:'网面的设计，夏天穿很透气不会猛将，的机房第三方反倒是尽快发哈市豆腐干绝代风华申达股份。'},
            ],
            imageList: [
                'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/151287/11/6299/88753/5fab4128E87c46fe5/769a497c164a554c.jpg.avif',
                'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/101509/24/30810/163038/62ba60cbE65baa74a/88628943ed2025c7.jpg.avif',
                'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/62238/2/19413/220830/62b0abeaE4acbc470/add9ae4ee0c022c3.jpg.avif',
                'https://m11.360buyimg.com/babel/s1228x1228_jfs/t1/181621/38/26357/228605/62c3a488E491bc614/b10cb208f31ccf8d.jpg.avif'
            ],
            labelList: [
                {name:'新品', type:'success'},
            ],
            detail: '鸿星尔克官方旗舰2022男鞋防滑透气舒适轻便时尚百搭休闲运动休闲鞋子男士夏季新款舒适，鸿星尔克官方旗舰2022男鞋防滑透气舒适轻便时尚百搭休闲运动休闲鞋子男士夏季新款舒适，鸿星尔克官方旗舰2022男鞋防滑透气舒适轻便时尚百搭休闲运动休闲鞋子男士夏季新款舒适',
        }
    }

    const onCommentList = () =>{
        state.commentList = [
            {userName:'张三',rating:4,data:'2022-07-07',text:'网面的设计，夏天穿很透气不会猛将，的机房第三方反倒是尽快发哈市豆腐干绝代风华申达股份。'},
            {userName:'李四',rating:4.5,data:'2022-07-07',text:'网面的设计，夏天穿很透气不会猛将，的机房第三方反倒是尽快发哈市豆腐干绝代风华申达股份。'},
            {userName:'王五',rating:3.9,data:'2022-07-07',text:'网面的设计，夏天穿很透气不会猛将，的机房第三方反倒是尽快发哈市豆腐干绝代风华申达股份。'},
            {userName:'地方',rating:3.9,data:'2022-07-07',text:'网面的设计，夏天穿很透气不会猛将，的机房第三方反倒是尽快发哈市豆腐干绝代风华申达股份。'},
            {userName:'胜多负少',rating:3.9,data:'2022-07-07',text:'网面的设计，夏天穿很透气不会猛将，的机房第三方反倒是尽快发哈市豆腐干绝代风华申达股份。'},
            {userName:'fdgdfg',rating:3.9,data:'2022-07-07',text:'网面的设计，夏天穿很透气不会猛将，的机房第三方反倒是尽快发哈市豆腐干绝代风华申达股份。'},
            {userName:'fgdg',rating:3.9,data:'2022-07-07',text:'网面的设计，夏天穿很透气不会猛将，的机房第三方反倒是尽快发哈市豆腐干绝代风华申达股份。'},
            {userName:'代付',rating:3.9,data:'2022-07-07',text:'网面的设计，夏天穿很透气不会猛将，的机房第三方反倒是尽快发哈市豆腐干绝代风华申达股份。'},
        ]
    }

    const onComment = () =>{
        onCommentList();
        state.commentDrawer = true;
    }

    const onAddCart = (type) =>{
        if(type == 1){
            // 添加购物成功后跳转至购物车

        }
        router.push({path:'/app/cart'})
    }

    const onSubmit = () =>{
        // 添加订单成功后跳转至确认订单
        router.push({path:'/app/order'})
    }

    const toShop = () =>{
        // 跳转店铺
        router.push({path:'/app/shop'})
    }

    const toBack = () =>{
        router.go(-1)
    }

    function onDecimal(val){
        return toDecimal(val);
    }

</script>
<style lang="scss">
    .goods-detail{
        .detail-header{
            padding: 0.4rem 0;
            border-bottom: 1px solid #F0F2F5;
            .content{
                font-size: 16px;
                padding-left: 5rem;
            }
        }
        .detail-main{
            .main-content,.main-address,.main-comment,.main-detail{
                .el-card{
                    border-left: 0;
                    border-right: 0;
                    margin-bottom: 0.4rem;
                    border-radius: 8px;
                    .el-card__header{
                        padding: 0.4rem 1rem;
                        border: 0;
                    }
                }
            }
            .card-header{
                display: flex;
                justify-content: space-between;
                span{
                    font-size: 14px;
                    font-weight: 700;
                }
            }
            .main-content{
                .content-title{
                    font-weight: 700;
                    font-size: 14px;
                    padding-bottom: 0.6rem;
                    line-height: 22px;
                    overflow: hidden;
                    display: -webkit-box;
                    -webkit-line-clamp: 2;
                    line-clamp: 2;
                    -webkit-box-orient: vertical;
                }
                .content-price{
                    color: red;
                    font-weight: 700;
                    font-size: 16px;
                    padding-bottom: 0.6rem;
                }
                .content-service{
                    i,span{
                        vertical-align: middle;
                    }
                }
            }
            .main-address{
                .address-content{
                    i,span{
                        vertical-align: middle;
                    }
                    .content-title{
                        font-weight: 700;
                        padding-right: 0.4rem;
                    }
                    .content-service-title{
                        font-weight: 700;
                        color: red;
                        padding-right: 0.4rem;
                    }
                    .content-service-text{
                        color: #909399;
                    }
                }
            }
            .main-comment{
                .comment-content{
                    .content-user{
                        display: flex;
                        justify-content: space-between;
                        padding-bottom: 0.6rem;
                        .el-avatar,.el-rate,span{
                            vertical-align: middle;
                        }
                        .el-avatar{
                            background: #ffffff;
                        }
                        .el-rate .el-rate__icon.is-active,.el-rate__decimal{
                            color: #ff0000;
                            margin-right: 0;
                        }
                        .el-rate .el-rate__icon{
                            font-size: 14px;
                        }
                    }
                    .content-text{
                        line-height: 22px;
                    }
                }
                .comment-but{
                    text-align: center;
                    padding-top: 1rem;
                }
                .el-drawer{
                    border-top-left-radius: 10px;
                    border-top-right-radius: 10px;
                }
            }
            .main-detail{
                padding: 0;
                margin: 0;
                line-height: 24px;
            }
        }
        .detail-footer{
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-top: 1px solid #e4e7ed;
            padding: 0.4rem 0;
            .footer-shop{
                padding-left: 1rem;
                i,span{
                    vertical-align: middle
                }
            }
            .footer-submit{
                padding-right: 1rem;
            }
        }
    }
</style>