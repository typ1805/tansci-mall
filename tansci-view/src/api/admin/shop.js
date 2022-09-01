import axios from '@/utils/axios'

/**
 * 店铺分页
 * @param {*} params
 * @returns
 */
export function shopPage(params) {
    return axios.get('/tansci/shop/page', {params: params});
}

/**
 * 添加
 * @param {*} params
 * @returns
 */
export function saveShop(params) {
    return axios.post('/tansci/shop/save', params);
}

/**
 * 更新
 * @param {*} params
 * @returns
 */
export function updateShop(params) {
    return axios.post('/tansci/shop/update', params);
}

/**
 * 删除
 * @param {*} params
 * @returns
 */
export function delShop(params) {
    return axios.get('/tansci/shop/delete', {params: params});
}
