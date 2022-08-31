import axios from '@/utils/axios'

/**
 * 分页
 */
export function indexConfigPage(params) {
    return axios.get('/tansci/indexConfig/page', { params: params });
}

/**
 * 添加
 */
export function saveIndexConfig(params) {
    return axios.post('/tansci/indexConfig/save', params);
}

/**
 * 修改
 */
export function updateIndexConfig(params) {
    return axios.post('/tansci/indexConfig/update', params);
}

/**
 * 删除
 */
export function delIndexConfig(params) {
    return axios.get('/tansci/indexConfig/delete', { params: params });
}
