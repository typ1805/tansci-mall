import axios from '@/utils/axios'

/**
 * 商品列表
 * @param params
 * @returns {Promise<AxiosResponse<T>>}
 */
 export function goodsList(userId) {
    return axios.post('/tansci/api/goodsList/' + userId);
}