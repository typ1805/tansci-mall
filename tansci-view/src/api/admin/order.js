import axios from '@/utils/axios'

/**
 * 订单列表分页
 * @param {*} params
 * @returns
 */
export function orderPage(params) {
    return axios.get('/tansci/order/page', {params: params});
}


/**
 * 修改订单
 * @param {*} params
 * @returns
 */
export function updateOrder(params) {
    return axios.post('/tansci/order/update', params);
}

/**
 * 删除订单
 * @param {*} params
 * @returns
 */
export function delOrder(params) {
    return axios.get('/tansci/order/del', {params: params});
}