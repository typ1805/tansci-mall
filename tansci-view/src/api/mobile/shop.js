import axios from '@/utils/axios'

/**
 * 店铺分页
 * @param {*} params
 * @returns
 */
export function getShopPage(params) {
    return axios.get('/tansci/api/shop/getShopPage', {params: params});
}

/**
 * 店铺信息
 * @param {*} params
 * @returns
 */
export function getShopInfo(params) {
    return axios.get('/tansci/api/shop/getShopInfo', {params: params});
}