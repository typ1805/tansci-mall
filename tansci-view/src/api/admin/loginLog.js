import axios from '@/utils/axios'

/**
 * 登录日志
 * @param {*} params
 * @returns
 */
export function loginLogPage(params) {
    return axios.get('/tansci/loginLog/page', {params: params});
}