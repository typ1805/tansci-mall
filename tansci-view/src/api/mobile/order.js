import axios from '@/utils/axios'

/**
 * 订单分页
 * @param {*} params
 * @returns
 */
export function orderPage(params) {
    return axios.get('/tansci/order/page', {params: params});
}

/**
 * 删除订单
 * @param {*} params
 * @returns
 */
export function submitOrder(params) {
    return axios.post('/tansci/order/submit', params);
}

/**
 * 删除订单
 * @param {*} params
 * @returns
 */
export function delOrder(params) {
    return axios.get('/tansci/order/delete', {params: params});
}