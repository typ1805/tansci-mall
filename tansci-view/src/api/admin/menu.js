import axios from '@/utils/axios'

/**
 * 列表
 * @param {*} params 
 * @returns 
 */
 export function menuList(params) {
    return axios.get('/tansci/menu/list', { params: params });
}

/**
 * 删除菜单
 * @param {*} params 
 * @returns 
 */
export function delMenu(params) {
    return axios.get('/tansci/menu/del', { params: params });
}

/**
 * 添加菜单
 * @param {*} params 
 * @returns 
 */
export function saveMenu(params) {
    return axios.post('/tansci/menu/save', params);
}

/**
 * 更新菜单
 * @param {*} params 
 * @returns 
 */
export function updateMenu(params) {
    return axios.post('/tansci/menu/update', params);
}