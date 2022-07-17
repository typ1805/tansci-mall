import axios from '@/utils/axios'

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