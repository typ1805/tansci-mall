import axios from '@/utils/axios'

/**
 * 分页列表
 */
 export function dicList(params) {
    return axios.get('/tansci/dic/dicList', { params: params });
}
/**
 * 字典列表
 */
export function getGroupNameByList(params) {
    return axios.get('/tansci/dic/getGroupNameByList', { params: params });
}
/**
 * 添加字典
 */
export function saveDic(params) {
    return axios.post('/tansci/dic/save', params);
}
/**
 * 修改字典
 */
export function updateDic(params) {
    return axios.post('/tansci/dic/update', params);
}
/**
 * 删除字典
 */
export function delDic(params) {
    return axios.get('/tansci/dic/del', { params: params });
}