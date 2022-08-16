import axios from '@/utils/axios'

/**
 * 商品列表分页
 * @param {*} params
 * @returns
 */
export function getGoodsPage(params) {
    return axios.get('/tansci/api/goods/getGoodsPage', {params: params});
}

/**
 * 商品详情
 * @param {*} params
 * @returns
 */
export function getGoodsInfo(params) {
    return axios.get('/tansci/api/goods/getGoodsInfo', {params: params});
}