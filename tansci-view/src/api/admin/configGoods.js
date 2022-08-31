import axios from '@/utils/axios'

/**
 * 分页
 */
export function configGoodsPage(params) {
    return axios.get('/tansci/configGoods/page', { params: params });
}

/**
 * 添加
 */
export function saveConfigGoods(params) {
    return axios.post('/tansci/configGoods/save', params);
}

/**
 * 修改
 */
export function updateConfigGoods(params) {
    return axios.post('/tansci/configGoods/update', params);
}

/**
 * 删除
 */
export function delConfigGoods(params) {
    return axios.get('/tansci/configGoods/delete', { params: params });
}
