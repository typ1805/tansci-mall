import axios from '../utils/axios'

// ==================登录=============================
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

// ================上传文件=======================
/**
 *  上传图片
 * @param {*} params
 * @returns
 */
export function uploadImage(params) {
    let config = {
        headers: {'Content-Type': 'multipart/form-data'}
    }
    return axios.post('/tansci/upload/uploadImage', params, config);
}

/**
 *  删除文件
 * @param {*} params
 * @returns
 */
export function delFile(params) {
    return axios.get('/tansci/upload/delFile', {params: params});
}

// =================用户信息===========================
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

// =====================菜单管理==================================

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

/**
 * 用户菜单权限
 * @param {*} params 
 * @returns 
 */
export function userMenuSave(params) {
    return axios.post('/tansci/menu/userMenuSave', params);
}

// ===================商品====================
/**
 * 商品列表分页
 * @param {*} params
 * @returns
 */
export function goodsPage(params) {
    return axios.get('/tansci/goods/page', {params: params});
}

/**
 * 添加商品
 * @param {*} params
 * @returns
 */
export function addGoods(params) {
    return axios.post('/tansci/goods/save', params);
}

/**
 * 修改商品
 * @param {*} params
 * @returns
 */
export function updateGoods(params) {
    return axios.post('/tansci/goods/update', params);
}

/**
 * 删除商品
 * @param {*} params
 * @returns
 */
export function delGoods(params) {
    return axios.get('/tansci/goods/del', {params: params});
}

// ===================订单====================
/**
 * 订单列表分页
 * @param {*} params
 * @returns
 */
export function orderPage(params) {
    return axios.get('/tansci/order/page', {params: params});
}


/**
 * 修改订单
 * @param {*} params
 * @returns
 */
export function updateOrder(params) {
    return axios.post('/tansci/order/update', params);
}

/**
 * 删除订单
 * @param {*} params
 * @returns
 */
export function delOrder(params) {
    return axios.get('/tansci/order/del', {params: params});
}

// =====================首页=========================
/**
 * 平台数据统计
 * @param {*} params
 * @returns
 */
export function statistics(params) {
    return axios.get('/tansci/home/statistics', {params: params});
}

/**
 * 交易数据总览
 * @param {*} params
 * @returns
 */
export function orderOverview(params) {
    return axios.get('/tansci/home/orderOverview', {params: params});
}

/**
 * 支付渠道总览
 * @param {*} params
 * @returns
 */
export function channelAnalysis(params) {
    return axios.get('/tansci/home/channelAnalysis', {params: params});
}
