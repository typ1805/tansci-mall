import axios from '@/utils/axios'

/**
 * 登录
 * @param params
 * @returns {Promise<AxiosResponse<T>>}
 */
 export function login(params) {
    return axios.post('/tansci/user/login', params);
}

/**
 * 登出
 * @param params
 * @returns {Promise<AxiosResponse<T>>}
 */
export function logout(params) {
    return axios.post('/tansci/user/logout', params);
}

/**
 * 用户列表分页
 * @param {*} params
 * @returns
 */
 export function userPage(params) {
    return axios.get('/tansci/user/page', {params: params});
}

/**
 * 用户
 * @param {*} params
 * @returns
 */
export function qryByUserName(params) {
    return axios.get('/tansci/user/qryByUserName', {params: params});
}

/**
 * 添加用户
 * @param {*} params
 * @returns
 */
export function addUser(params) {
    return axios.post('/tansci/user/save', params);
}

/**
 * 修改用户
 * @param {*} params
 * @returns
 */
export function updateUser(params) {
    return axios.post('/tansci/user/update', params);
}

/**
 * 删除用户
 * @param {*} params
 * @returns
 */
export function delUser(params) {
    return axios.get('/tansci/user/del', {params: params});
}

/**
 * 修改密码
 * @param {*} params
 * @returns
 */
export function modifyPass(params) {
    return axios.post('/tansci/user/modifyPass', params);
}