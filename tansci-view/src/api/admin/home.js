import axios from '@/utils/axios'

/**
 * 平台数据统计
 * @param {*} params
 * @returns
 */
export function statistics(params) {
    return axios.get('/tansci/home/statistics', {params: params});
}

/**
 * 交易数据总览
 * @param {*} params
 * @returns
 */
export function orderOverview(params) {
    return axios.get('/tansci/home/orderOverview', {params: params});
}

/**
 * 支付渠道总览
 * @param {*} params
 * @returns
 */
export function channelAnalysis(params) {
    return axios.get('/tansci/home/channelAnalysis', {params: params});
}