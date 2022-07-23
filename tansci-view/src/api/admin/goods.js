import axios from '@/utils/axios'

/**
 * 商品列表分页
 * @param {*} params
 * @returns
 */
export function goodsPage(params) {
    return axios.get('/tansci/goods/page', {params: params});
}

/**
 * 添加商品
 * @param {*} params
 * @returns
 */
export function addGoods(params) {
    return axios.post('/tansci/goods/save', params);
}

/**
 * 修改商品
 * @param {*} params
 * @returns
 */
export function updateGoods(params) {
    return axios.post('/tansci/goods/update', params);
}

/**
 * 删除商品
 * @param {*} params
 * @returns
 */
export function delGoods(params) {
    return axios.get('/tansci/goods/delete', {params: params});
}

/**
 * 商品图片列表
 * @param {*} params
 * @returns
 */
 export function goodsImageList(params) {
    return axios.get('/tansci/goods/goodsImageList', {params: params});
}
