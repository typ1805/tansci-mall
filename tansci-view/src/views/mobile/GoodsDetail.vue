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
                            <el-image :src="image.url" style="width: 100%; height: 100%;" fit="fit"/>
                        </el-carousel-item>
                    </el-carousel>
                </div>
                <div class="main-content">
                    <el-card :shadow="shadow">
                        <div class="content-price">
                            <span style="font-size: 12px;">￥</span><span>{{onDecimal(goodsInfo.price)}}</span>
                        </div>
                        <div class="content-title">{{goodsInfo.name}} - {{goodsInfo.intro}}</div>
                        <div class="content-service">
                            <el-alert type="error" center :closable="false">
                                <template #default>
                                    <el-icon :size="16"><BellFilled /></el-icon>
                                    <span style="font-size: 16px;font-weight: 700;padding:0 1rem 0 0.4rem;">放心购</span>
                                    <span>免费上门取退-7天保价-极速审核</span>
                                </template>
                            </el-alert>
                        </div>
                        <div class="content-number">
                            <span>数量</span>
                            <el-input-number v-model="goodsInfo.goodsNum" :min="1" :max="100" size="small" style="width:86px;"/>
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
                                <span>{{goodsInfo.address?goodsInfo.address:'您还未登录！'}}</span>
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
                                <span>评价 {{goodsInfo.comment}}条</span>
                                <div>
                                    <span style="font-size: 12px;font-weight: 500;padding-right: 0.4rem;">好评度{{goodsInfo.rating}}%</span>
                                    <el-button icon="MoreFilled" type="primary" link></el-button>
                                </div>
                            </div>
                        </template>
                        <div v-if="goodsInfo.goodsCommentList && goodsInfo.goodsCommentList.length > 0">
                            <div v-for="(comment,index) in goodsInfo.goodsCommentList" :key="index" class="comment-content">
                                <div class="content-user">
                                    <div>
                                        <el-avatar :size="20" icon="UserFilled" style="color: #F56C6C;"/>
                                        <span style="padding:0 0.4rem; font-size: 12px;">{{comment.userName}}</span>
                                        <el-rate v-model="comment.rating" :max="5" size="small" disabled/>
                                    </div>
                                    <div style="color:#909399;font-size: 12px;">{{comment.date}}</div>
                                </div>
                                <div class="content-text">{{comment.content}}</div>
                                <el-divider v-show="index+1 < goodsInfo.goodsCommentList.length" />
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
                                    <div style="color:#909399;font-size: 12px;">{{comment.date}}</div>
                                </div>
                                <div class="content-text">{{comment.content}}</div>
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
                        <div class="detail-content" v-html="goodsInfo.details"></div>
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
    import {ElMessage} from 'element-plus'
    import {useRouter, useRoute} from 'vue-router'
    import {useUserStore} from '@/store/settings'
    import {toDecimal} from '@/utils/utils'
    import {getGoodsInfo} from '@/api/mobile/goods'
    import {getGoodsCommentPage} from '@/api/mobile/goodsComment'
    import {userAddressList} from '@/api/admin/userAddress'
    import {qryByUserName} from '@/api/admin/user'
    import {saveCart} from '@/api/admin/cart'

    const userStore = useUserStore();
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
        commentPage: {
            current: 1,
            size: 10,
            total: 1,
        },
        commentList: [],
        userInfo: {},
        addressList: [],
    })

    const {
        defaultHeight,goodsInfo,tags,commentDrawer,commentPage,commentList,userInfo,addressList
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 80 + "px";
    })

    onMounted(()=>{
        if(route.query.goodsId){
            onGoodsInfo(route.query.goodsId); 
            onUserInfo();
        }
    })

    const onGoodsInfo = () =>{
        getGoodsInfo({goodsId: route.query.goodsId}).then(res=>{
            state.goodsInfo = res.result;
            state.goodsInfo.goodsNum = 1;
        })
    }

    const onCommentList = () =>{
        let param = {
            goodsId: route.query.goodsId
        }
        getGoodsCommentPage(Object.assign(state.commentPage, param)).then(res=>{
            state.commentList = res.result.records;
        })
    }

    const onComment = () =>{
        onCommentList();
        state.commentDrawer = true;
    }

    // 获取商户信息
    const onUserInfo = () =>{
        const user = userStore.getUser.user;
        if(!user || !user.username){
            return;
        }
        qryByUserName({username: user.username}).then(res=>{
            state.userInfo = res.result;
            onUserAddressList();
        })
    }
    // 获取商户地址
    const onUserAddressList = () =>{
        if(!state.userInfo.id){
            return;
        }   
        userAddressList({userId: state.userInfo.id}).then(res=>{
            state.addressList = res.result;
            let address = state.addressList.find(item=>{
                return item.flag = 1;
            });
            if(address){
                state.goodsInfo.address = address.province+address.city+address.region+address.details;
                state.goodsInfo.addressId = address.id;
            } 
        })
    }

    // 购物车：type: 1、跳转购物车，0、添加购物
    const onAddCart = (type) =>{
        if(type == 0){
            let param = {
                shopId: state.goodsInfo.shopId,
                goodsId: state.goodsInfo.goodsId,
                goodsNum: state.goodsInfo.goodsNum?state.goodsInfo.goodsNum:1,
                userId: state.userInfo.id,
                status: 0,
            }
            saveCart(param).then(res=>{
                if(res){
                    ElMessage.success("加入成功！")
                }
            })
        } else {
            router.push({path:'/app/cart'})
        }
    }

    // 立即购买
    const onSubmit = () =>{
        let param = {
            shopId: state.goodsInfo.shopId,
            goodsId: state.goodsInfo.goodsId,
            goodsNum: state.goodsInfo.goodsNum?state.goodsInfo.goodsNum:1,
            userId: state.userInfo.id,
            status: 0,
        }
        saveCart(param).then(res=>{
            if(res){
                router.push({path:'/app/order'})
            }
        })
    }

    // 跳转店铺
    const toShop = () =>{
        router.push({path:'/app/shop', query:{shopId: state.goodsInfo.shopId}})
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
                    margin-top: -0.2rem;
                    i,span{
                        vertical-align: middle;
                    }
                }
                .content-number{
                    padding-top: 1rem;
                    display: flex;
                    justify-content: space-between;
                    span{
                        font-size: 14px;
                        font-weight: 700;
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