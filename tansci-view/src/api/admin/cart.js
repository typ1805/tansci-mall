import axios from '@/utils/axios'

/**
 * 分页
 */
export function cartPage(params) {
    return axios.get('/tansci/cart/page', { params: params });
}

/**
 * 购物车列表
 */
export function getCartList(params) {
    return axios.get('/tansci/cart/list', { params: params });
}

/**
 * 添加
 */
export function saveCart(params) {
    return axios.post('/tansci/cart/save', params);
}

/**
 * 删除
 */
export function delCart(params) {
    return axios.get('/tansci/cart/delete', { params: params });
}

/**
 * 批量删除
 */
export function delBatchCart(params) {
    return axios.post('/tansci/cart/batchDelete', params);
}