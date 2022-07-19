<template>
    <div class="integral">
        <div class="integral-header">
            <el-page-header @back="toBack" icon="ArrowLeft" title="返回">
                <template #content>
                    <span class="content">我的积分</span>
                </template>
            </el-page-header>
        </div>
        <div class="integral-main">
            <el-scrollbar :height="defaultHeight">
                <div class="main-integral">
                    <el-card :shadow="shadow" :body-style="{padding: '1rem', 'text-align': 'center', 'line-height': '34px'}">
                        <div><el-icon :size="30" color="#FF8800"><Avatar /></el-icon></div>
                        <div style="color: #ffffff;font-weight: 700;">我的积分 {{integralInfo.integral}}</div>
                        <div>
                            <el-button @click="toIndex" type="warning" round style="width: 60%;">去赚积分</el-button>
                        </div>
                    </el-card>
                </div>
                <div class="main-rule">
                    <div class="rule-title">积分规则</div>
                    <div class="rule-text">在订单签收后有机会获得积分奖励哦</div>
                </div>
                <div class="main-rule-list">
                    <el-card v-for="rule in ruleList" :key="rule" :shadow="shadow" :body-style="{padding: '0'}">
                        <div class="rule-list-price">
                            <div class="price">
                                <span style="font-size: 12px;">￥</span>
                                <span>{{rule.price}}</span>
                            </div>
                            <div>
                                <div>全品类</div>
                                <div style="font-size: 12px;">满可获积分</div>
                            </div>
                        </div>
                        <div class="rule-list-integral">{{rule.integral}}积分</div>
                    </el-card>
                </div>
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
        integralInfo: {
            integral: 0
        },
        ruleList: [
            {price: 10,integral:1},
            {price: 20,integral:2},
            {price: 50,integral:3},
            {price: 80,integral:4},
            {price: 100,integral:5},
            {price: 200,integral:20},
        ],
    })

    const {
        shadow,defaultHeight,integralInfo,ruleList
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight) - 45 + "px";
    })

    onMounted(()=>{
        onIntegral();
    })

    const onIntegral = (userId) =>{
        state.integralInfo = {
            integral: 500
        }
    }

    const toIndex = () =>{
        router.push({path:'/miniapp/index'})
    }

    const toBack = () =>{
        router.go(-1)
    }

</script>
<style lang="scss">
    .integral{
        .integral-header{
            padding: 0.4rem 0;
            .content{
                font-size: 16px;
                padding-left: 5rem;
            }
        }
        .integral-main{
            .el-scrollbar{
                background: #FAFAFA;
                padding: 0.4rem 0.4rem;
            }
            .main-integral{
                .el-card{
                    margin-bottom: 0.4rem;
                    border-radius: 8px;
                }
                .el-card__body{
                    background-image: linear-gradient(to bottom, #FFDDAA 0%, #FF8800  100%);
                }
            }
            .main-rule{
                padding: 1rem 0;
                .rule-title{
                    font-size: 16px;
                    padding-bottom: 0.2rem;
                }
                .rule-text{
                    color: #909399;
                    font-size: 12px;
                }
            }
            .main-rule-list{
                display: flex;
                justify-content: space-between;
                align-items: stretch;
                flex-wrap: wrap;
                .el-card{
                    width: 48%;
                    margin-bottom: 0.8rem;
                }
                .rule-list-price{
                    display: flex;
                    justify-content: left;
                    align-items: center;
                    padding: 1rem 0.8rem;
                    color: #909399;
                    .price{
                        color: #FF8800;
                        font-size: 20px;
                        padding-right: 1rem;
                    }
                }
                .rule-list-integral{
                    padding: 0.4rem 0;
                    background-image: linear-gradient(to bottom, #FFAA33 0%, #FF8800  100%);
                    color: #ffffff;
                    text-align: center;
                }
            }
        }
    }
</style>

