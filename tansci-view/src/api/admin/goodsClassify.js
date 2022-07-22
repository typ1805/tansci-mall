import axios from '@/utils/axios'

/**
 * 列表
 * @param {*} params
 * @returns
 */
 export function goodsClassifyList(params) {
    return axios.get('/tansci/goodsClassify/list', {params: params});
}

/**
 * 添加
 * @param {*} params
 * @returns
 */
export function goodsClassifySave(params) {
    return axios.post('/tansci/goodsClassify/save', params);
}

/**
 * 修改
 * @param {*} params
 * @returns
 */
export function goodsClassifyUpdate(params) {
    return axios.post('/tansci/goodsClassify/update', params);
}

/**
 * 删除
 * @param {*} params
 * @returns
 */
export function goodsClassifyDel(params) {
    return axios.get('/tansci/goodsClassify/delete', {params: params});
}