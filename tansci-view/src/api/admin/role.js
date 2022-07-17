import axios from '@/utils/axios'

/**
 * 角色列表分页
 */
 export function rolePage(params) {
    return axios.get('/tansci/role/page', { params: params });
}
/**
 * 角色列表
 */
export function roleList(params) {
    return axios.get('/tansci/role/list', { params: params });
}
/**
 * 添加角色
 */
export function addRole(params) {
    return axios.post('/tansci/role/save', params);
}
/**
 * 修改角色
 */
export function updateRole(params) {
    return axios.post('/tansci/role/update', params);
}
/**
 * 删除角色
 */
export function delRole(params) {
    return axios.get('/tansci/role/delete', { params: params });
}
/**
 * 添加用户角色
 */
export function userRoleSave(params) {
    return axios.post('/tansci/role/userRoleSave', params);
}
/**
 * 添加菜单角色
 */
export function menuRoleSave(params) {
    return axios.post('/tansci/role/menuRoleSave', params);
}