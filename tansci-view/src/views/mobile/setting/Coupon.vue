<template>
    <div class="coupon">
        <div class="coupon-header">
            <el-page-header @back="toBack" icon="ArrowLeft" title="返回">
                <template #content>
                    <span class="content">优惠券</span>
                </template>
            </el-page-header>
        </div>
        <div class="coupon-main">
            <el-scrollbar :height="defaultHeight">
                <el-card v-for="(coupon,index) in couponList" :key="index" :shadow="shadow">
                    <div class="coupon-content">
                        <div style="padding-right: 2rem;text-align: center;">
                            <div class="amount">
                                <span style="font-size: 14px;">￥</span>
                                <span>{{coupon.amount}}</span>
                            </div>
                            <div class="text">
                                <span>满</span>
                                <span>{{coupon.norm}}</span>
                                <span>元可用</span>
                            </div>
                        </div>
                        <div>
                            <div class="title">
                                <el-tag color="#00DDDD" size="small" effect="dark" round>{{coupon.type}}</el-tag>
                                <span style="padding-left: 0.4rem;">{{coupon.name}}</span>
                            </div>
                            <div class="time">
                                <span>{{coupon.startTime}}~{{coupon.endTime}}</span>
                            </div>
                            <el-divider border-style="dashed" />
                            <div class="remarks">
                                <span>使用说明：</span>
                                <span>{{coupon.remarks}}</span>
                            </div>
                        </div>
                    </div>
                </el-card>
            </el-scrollbar>
        </div>
    </div>
</template>
<script setup>
    import {onBeforeMount, onMounted, reactive, toRefs} from 'vue'
    import {useRouter} from 'vue-router'

    const router = useRouter()

    const state = reactive({
        shadow: 'never',
        defaultHeight: null,
        couponList: [],
    })

    const {
        shadow,defaultHeight,couponList
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 45 + "px";
    })

    onMounted(()=>{
        onCouponList();
    })

    const onCouponList = (userId) =>{
        state.couponList = [
            {couponId: 'c1001',amount:50,norm:69,name:'鸿星尔克旗舰店',type:'店铺券',startTime:'2022-07-19',endTime:'2022-07-31',remarks:'仅可在此店铺使用'},
            {couponId: 'c1002',amount:10,norm:69,name:'鸿星尔克旗舰店',type:'店铺券',startTime:'2022-07-19',endTime:'2022-07-31',remarks:'仅可在此店铺使用'},
            {couponId: 'c1003',amount:45,norm:69,name:'平台优惠券',type:'平台券',startTime:'2022-07-19',endTime:'2022-07-31',remarks:'仅可在此店铺使用'},
            {couponId: 'c1004',amount:20,norm:69,name:'鸿星尔克旗舰店',type:'店铺券',startTime:'2022-07-19',endTime:'2022-07-31',remarks:'仅可在此店铺使用'},
            {couponId: 'c1005',amount:5,norm:69,name:'平台优惠券',type:'平台券',startTime:'2022-07-19',endTime:'2022-07-31',remarks:'仅可在此店铺使用'},
        ]
    }

    const toBack = () =>{
        router.go(-1)
    }

</script>
<style lang="scss">
    .coupon{
        .coupon-header{
            padding: 0.4rem 0;
            .content{
                font-size: 16px;
                padding-left: 5rem;
            }
        }
        .coupon-main{
            .el-scrollbar{
                background: #FAFAFA;
                padding: 0.4rem 0.2rem;
            }
            .el-card{
                margin-bottom: 0.8rem;
                border-radius: 8px;
                border-top: 6px solid #00DDDD;
            }
            .coupon-content{
                display: flex;
                justify-content: left;
                align-items: center;
                .amount{
                    font-size: 30px;
                    font-weight: 700;
                    color: #00DDDD;
                }
                .text{
                    color: #00DDDD;
                }
                .title{
                    padding-bottom: 1.4rem;
                }
                .time{
                    color: #909399;
                    font-size: 12px;
                    line-height: 30px;
                }
                .remarks{
                    color: #909399;
                    line-height: 30px;
                }
                .el-tag{
                    border: none !important;
                }
                .el-divider{
                    margin: 0.2rem 0;
                }
            }
        }
    }
</style>

