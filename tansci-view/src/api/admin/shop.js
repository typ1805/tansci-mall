import axios from '@/utils/axios'

/**
 * 店铺分页
 * @param {*} params
 * @returns
 */
export function shopPage(params) {
    return axios.get('/tansci/shop/page', {params: params});
}
