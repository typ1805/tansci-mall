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
                <el-card v-for="(coupon,index) in coupons" :key="index" :shadow="shadow">
                    <div class="coupon-content">
                        <div style="padding-right: 2rem;text-align: center;">
                            <div class="amount">
                                <span style="font-size: 14px;">￥</span>
                                <span>{{coupon.price}}</span>
                            </div>
                            <div class="text">
                                <span>满</span>
                                <span>{{coupon.norm}}</span>
                                <span>可用</span>
                            </div>
                        </div>
                        <div>
                            <div class="title">
                                <el-tag color="#00DDDD" size="small" effect="dark" round>{{coupon.typeName}}</el-tag>
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
                            <div style="float: right;">
                                <el-tag v-if="coupon.status == 1" type="success" size="small" effect="dark" round>未使用</el-tag>
                                <el-tag v-if="coupon.status == 2" type="warning" size="small" effect="dark" round>{{coupon.statusName}}</el-tag>
                                <el-tag v-if="coupon.status == 3" type="danger" size="small" effect="dark" round>{{coupon.statusName}}</el-tag>
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
    import {useUserStore} from '@/store/settings'
    import {couponList} from '@/api/admin/coupon'

    const userStore = useUserStore();
    const router = useRouter()
    const state = reactive({
        shadow: 'never',
        defaultHeight: null,
        coupons: [],
    })

    const {
        shadow,defaultHeight,coupons
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 45 + "px";
    })

    onMounted(()=>{
        onCouponList();
    })

    const onCouponList = () =>{
        const user = userStore.getUser.user;
        if(!user || !user.username){
            return;
        }

        couponList({username: user.username}).then(res=>{
            state.coupons = res.result;
        })
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

