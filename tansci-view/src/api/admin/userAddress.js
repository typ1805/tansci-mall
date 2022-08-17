import axios from '@/utils/axios'

/**
 * 商户地址分页
 * @param params
 * @returns 
 */
export function userAddressPage(params) {
    return axios.get('/tansci/userAddress/page', {params: params});
}

/**
 * 商户地址列表
 * @param params
 * @returns 
 */
export function userAddressList(params) {
    return axios.get('/tansci/userAddress/list', {params: params});
}

/**
 * 添加
 * @param {*} params
 * @returns
 */
export function addUserAddress(params) {
    return axios.post('/tansci/userAddress/save', params);
}

/**
 * 修改
 * @param {*} params
 * @returns
 */
export function updUserAddress(params) {
    return axios.post('/tansci/userAddress/update', params);
}

/**
 * 删除
 * @param {*} params
 * @returns
 */
export function delUserAddress(params) {
    return axios.get('/tansci/userAddress/del', {params: params});
}