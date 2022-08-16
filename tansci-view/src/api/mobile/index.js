import axios from '@/utils/axios'

/**
 * 首页轮播分页
 * @param {*} params
 * @returns
 */
export function getIndexCarouselPage(params) {
    return axios.get('/tansci/api/indexApi/getIndexCarouselPage', {params: params});
}

/**
 * 首页配置分页
 * @param {*} params
 * @returns
 */
export function getIndexConfigPage(params) {
    return axios.get('/tansci/api/indexApi/getIndexConfigPage', {params: params});
}

/**
 * 配置商品分页
 * @param {*} params
 * @returns
 */
export function getConfigGoodsPage(params) {
    return axios.get('/tansci/api/indexApi/getConfigGoodsPage', {params: params});
}