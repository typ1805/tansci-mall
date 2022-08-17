<template>
    <div class="home">
        <div class="home-agency-wrap">
            <el-card shadow="always">
                <div class="agency-wrap">
                    <div class="agency-greetings">
                        <el-icon color="#E6A23C" :size="20">
                            <promotion/>
                        </el-icon>
                        <span style="padding-left: 8px;">{{timeAddress()}}</span>
                        <span class="text">{{username}}</span>
                        <span>，祝您新的一天工作愉快！</span>
                    </div>
                </div>
            </el-card>
        </div>
        <div class="home-card-wrap">
            <el-card v-for="(card,index) in cardList" :key="index" shadow="always">
                <div class="card-num" :style="{color:card.color}">
                    <el-icon :color="card.color" style="vertical-align: middle;">
                        <component :is="card.icon"></component>
                    </el-icon>
                    <span style="vertical-align: middle;">{{numberDormat(card.num == null ? 0 : card.num)}}</span>
                </div>
                <div class="card-desc" :style="{color:card.color}">{{card.name}}</div>
            </el-card>
        </div>
        <div class="home-chart-wrap">
            <div class="chart-left">
                <el-card shadow="always">
                    <template #header>
                        <el-icon color="#E6A23C" :size="20" style="vertical-align: middle;"><histogram /></el-icon>
                        <span class="chart-header">交易数据总览</span>
                    </template>
                    <div id="orderOverview" style="height:500px;"></div>
                </el-card>
            </div>
            <div class="chart-right">
                <el-card shadow="always">
                    <template #header>
                        <el-icon color="#E6A23C" :size="20" style="vertical-align: middle;"><trend-charts /></el-icon>
                        <span class="chart-header">支付渠道总览</span>
                    </template>
                    <div id="channelAnalysis" style="height:500px;"></div>
                </el-card>
            </div>
        </div>
    </div>
</template>
<script setup>
    import {onMounted, onBeforeUnmount, reactive, toRefs} from 'vue'
    import {useUserStore} from '@/store/settings'
    import * as echarts from 'echarts'
    import {timeAddress,numberDormat} from '@/utils/utils'
    import {statistics,orderOverview,channelAnalysis} from '@/api/admin/home'

    const userStore = useUserStore();
    let orderOverviewChart = null;
    let channelAnalysisChart =null;
    const state = reactive({
        username: userStore.getUser.user.username == null ? '未登录':userStore.getUser.user.username,
        cardList: [],
    })

    const {
        username, cardList
    } = toRefs(state)

    onMounted(() => {
        onCardList();
        onOrderOverview();
        onChannelAnalysis();
    })

    onBeforeUnmount(() => {
        if(orderOverviewChart){
            orderOverviewChart.dispose();
            orderOverviewChart = null;
        }
        if(channelAnalysisChart){
            channelAnalysisChart.dispose();
            channelAnalysisChart = null;
        }
    })

    const onCardList = () =>{
        statistics({}).then(res=>{
            if(res){
                state.cardList = [
                    {"name": "成交额（元）", "num": res.result.orderAmount, "color": '#67C23A', icon:'HelpFilled'},
                    {"name": "净收入（元）", "num": res.result.netIncome, "color": '#F56C6C', icon:'TrendCharts'},
                    {"name": "订单总数（个）", "num": res.result.orderNum, "color": '#E6A23C', icon:'Coin'},
                    {"name": "总商品数（个）", "num": res.result.goodsNum, "color": '#409EFF', icon:'Goods'},
                    {"name": "商品分类（个）", "num": res.result.goodsTypeNum, "color": '#909399', icon:'Ticket'},
                ]
            }
        })
    }

    const onOrderOverview = async () =>{
        let data = [];
        await orderOverview({}).then(res=>{
            if(res){
                res.result.forEach(item=>{
                    data.push([
                        item.date,
                        item.orderNum,
                        item.amount,
                        item.wxAmout,
                        item.aliAmout
                    ])
                })
            }
        })

        orderOverviewChart = echarts.init(document.getElementById("orderOverview"));
        orderOverviewChart.setOption({
            legend: {},
            tooltip: {},
            color: ['#E6A23C', '#F56C6C', '#07c160', '#00bbee'],
            dataset: {
                dimensions: ['product', '订单量', '总收入', '微信收入', '支付宝收入'],
                source: data
            },
            xAxis: {
                type: 'category',
                axisTick: {
                    show: false // 不显示坐标轴刻度线
                },
            },
            yAxis: {
                show: true, // 不显示坐标轴线、坐标轴刻度线和坐标轴上的文字
                axisTick: {
                    show: false // 不显示坐标轴刻度线
                },
                axisLine: {
                    show: false // 不显示坐标轴线
                },
                splitLine: {
                    show: true // 不显示网格线
                },
            },
            series: [
                {type: 'bar'},
                {type: 'bar'},
                {type: 'bar'},
                {type: 'bar'},
            ]
        });
        window.onresize = function () {
            orderOverviewChart.resize();
        };
    }

    const onChannelAnalysis = async () =>{
        let data = [];
        await channelAnalysis({}).then(res=>{
            if(res){
                data = res.result;
                if(!data || data.length == 0){
                    data.push({value:0, name:'微信'})
                    data.push({value:0, name:'支付宝'})
                }
                data.push({value:0, name:'银联'})
            }
        })

        channelAnalysisChart = echarts.init(document.getElementById("channelAnalysis"));
        channelAnalysisChart.setOption({
            title: {},
            legend: {},
            tooltip: {
                trigger: 'item',
                formatter: '{a} <br/>{b} : {c}￥ ({d}%)'
            },
            color: ['#DD2C34', '#00bbee', '#07c160'],
            series: [
                {
                    name: '渠道',
                    type: 'pie',
                    radius: '90%',
                    avoidLabelOverlap: false,
                    roseType: 'radius',
                    center: ['50%', '50%'],
                    itemStyle: {
                        borderRadius: 10,
                        borderColor: '#fff',
                        borderWidth: 2
                    },
                    label:{
                        show: true,
                        position: 'inner'
                    },
                    labelLine: {
                        show:false
                    },
                    data: data.sort(function (a, b) {
                        return a.value - b.value;
                    })
                }
            ]
        });
        window.onresize = function () {
            channelAnalysisChart.resize();
        };
    }

</script>
<style lang="scss" scoped>
    .home {
        .home-agency-wrap {
            .agency-wrap {
                display: flex;
                flex-wrap: wrap;
                justify-content: space-between;
                align-items: center;
                padding: 0.8rem 2rem;

                .agency-greetings {
                    font-size: 16px;
                    color: var(--t8);

                    .text {
                        font-weight: 700;
                    }
                }
            }
        }

        .home-card-wrap {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            text-align: center;
            padding: 0.4rem 0;

            .el-card {
                flex: 1;
                margin-right: 0.4rem;

                &:last-child {
                    margin-right: 0;
                }
            }

            .card-num {
                padding: 0.4rem 0;
                font-size: 28px;
                font-weight: 700;
            }

            .card-desc {
                padding: 0.4rem 0;
                font-size: 14px;
            }
        }

        .home-chart-wrap{
            display: flex;
            justify-content: space-between;
            .chart-header{
                vertical-align: middle;
                font-size: 16px;
                color: #606266;
                font-weight: 700;
                padding-left: 8px;
            }
            .chart-left{
                width: 70%;
                padding-right: 0.3rem;
            }
            .chart-right{
                width: 30%;
                padding-right: 0.3rem;
            }
        }
    }
</style>