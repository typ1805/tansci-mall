import axios from 'axios'
import { ElMessage } from 'element-plus'
import router from '../router'

axios.defaults.withCredentials = true
axios.defaults.headers['X-Requested-With'] = 'XMLHttpRequest'
axios.defaults.headers.post['Content-Type'] = 'application/json'

axios.interceptors.request.use(function (config) {
    config.headers.Authorization = 'Bearer '+ sessionStorage.getItem('token') || ''
    return config
})

axios.interceptors.response.use(res => {
    if (!res) {
        ElMessage.error("服务器异常，请稍后再试！")
        return Promise.reject(res)
    }

    // 下载文件特殊处理
    if(res.config.responseType == 'blob'){
        return res;
    } else {
        if (res.data.code != 200) {
            ElMessage.error(res.data.message)
            if (res.data.code == 403 || res.data.code == 401) router.push({path: '/login'})
            return Promise.reject(res.data)
        }
    }

    return res.data;
})

export default axios