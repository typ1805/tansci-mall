import axios from '@/utils/axios'

/**
 * 订单列表分页
 * @param {*} params
 * @returns
 */
export function goodsOrderPage(params) {
    return axios.get('/tansci/goodsOrder/page', {params: params});
}


/**
 * 修改订单
 * @param {*} params
 * @returns
 */
export function updateGoodsOrder(params) {
    return axios.post('/tansci/goodsOrder/update', params);
}

/**
 * 删除订单
 * @param {*} params
 * @returns
 */
export function delGoodsOrder(params) {
    return axios.get('/tansci/goodsOrder/del', {params: params});
}