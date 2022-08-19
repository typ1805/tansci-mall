import axios from '@/utils/axios'

/**
 * 分页
 */
export function couponPage(params) {
    return axios.get('/tansci/coupon/page', { params: params });
}
/**
 * 列表
 */
export function couponList(params) {
    return axios.get('/tansci/coupon/list', { params: params });
}
/**
 * 添加
 */
export function saveCoupon(params) {
    return axios.post('/tansci/coupon/save', params);
}
/**
 * 修改
 */
export function updateCoupon(params) {
    return axios.post('/tansci/coupon/update', params);
}
/**
 * 删除
 */
export function delCoupon(params) {
    return axios.get('/tansci/coupon/delete', { params: params });
}