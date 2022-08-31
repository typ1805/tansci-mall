import axios from '@/utils/axios'

/**
 * 分页
 */
export function indexCarouselPage(params) {
    return axios.get('/tansci/indexCarousel/page', { params: params });
}

/**
 * 添加
 */
export function saveIndexCarousel(params) {
    return axios.post('/tansci/indexCarousel/save', params);
}

/**
 * 修改
 */
export function updateIndexCarousel(params) {
    return axios.post('/tansci/indexCarousel/update', params);
}

/**
 * 删除
 */
export function delIndexCarousel(params) {
    return axios.get('/tansci/indexCarousel/delete', { params: params });
}
