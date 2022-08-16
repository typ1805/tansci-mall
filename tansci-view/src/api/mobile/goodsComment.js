import axios from '@/utils/axios'

/**
 * 商品评价分页
 * @param {*} params
 * @returns
 */
export function getGoodsCommentPage(params) {
    return axios.get('/tansci/api/goodsComment/getGoodsCommentPage', {params: params});
}