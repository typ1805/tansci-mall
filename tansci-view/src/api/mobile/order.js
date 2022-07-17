import axios from '@/utils/axios'

/**
 * 支付创建订单
 * @param params
 * @returns {Promise<AxiosResponse<T>>}
 */
 export function payment(params) {
    return axios.post('/tansci/api/payment', params);
}