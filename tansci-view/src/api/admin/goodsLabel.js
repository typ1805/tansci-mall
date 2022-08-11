import axios from '@/utils/axios'

/**
 * 分页
 * @param {*} params
 * @returns
 */
export function goodsLabelPage(params) {
    return axios.get('/tansci/goodsLabel/page', {params: params});
}

/**
 * 列表
 * @param {*} params
 * @returns
 */
export function goodsLabelList(params) {
    return axios.get('/tansci/goodsLabel/list', {params: params});
}

/**
 * 添加
 * @param {*} params
 * @returns
 */
export function goodsLabelSave(params) {
    return axios.post('/tansci/goodsLabel/save', params);
}

/**
 * 修改
 * @param {*} params
 * @returns
 */
export function goodsLabelUpdate(params) {
    return axios.post('/tansci/goodsLabel/update', params);
}

/**
 * 删除
 * @param {*} params
 * @returns
 */
export function goodsLabelDel(params) {
    return axios.get('/tansci/goodsLabel/delete', {params: params});
}