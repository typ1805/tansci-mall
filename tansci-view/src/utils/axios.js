import axios from 'axios'
import { ElMessage } from 'element-plus'
import router from '../router'

axios.defaults.withCredentials = true
axios.defaults.timeout = 30000 // 超时时间，毫秒
axios.defaults.headers['X-Requested-With'] = 'XMLHttpRequest'
axios.defaults.headers.post['Content-Type'] = 'application/json'

// 请求异常拦截
axios.interceptors.request.use(config=> {
    // 设置token
    if(sessionStorage.getItem('token') && sessionStorage.getItem('token').length > 0){
        config.headers.Authorization = 'Bearer '+ sessionStorage.getItem('token')
    }
    return config
}, err=>{
    ElMessage.error("请求超时!")
    return Promise.resolve(err);
})

// 响应异常拦截
axios.interceptors.response.use(res => {
    if (!res) {
        ElMessage.error("服务器异常，请稍后再试！")
        return Promise.reject(res)
    }

    // 下载文件特殊处理
    if(res.config.responseType && res.config.responseType == 'blob'){
        return res;
    } else {
        if (res.data.code != 200) {
            ElMessage.error(res.data.message)
            if (res.data.code == 403 || res.data.code == 401){
                sessionStorage.clear();
                let route = router.currentRoute.value;
                if(route.meta.type == 'app'){
                    router.push({path: '/app/appLogin'});
                } else {
                    router.push({path: 'login'});
                }
            }
            return Promise.reject(res.data)
        }
    }

    return res.data;
}, err=>{
    let message = '服务器异常，请稍后再试！';
    if(err && err.response){
        let res = err.response.data;
        switch (res.code) {
            case 400: message = '请求错误，请稍后再试！'; break;
            case 401: message = '未授权，请重新登录！'; break;
            case 403: message = '拒绝访问，请稍后再试！'; break;
            case 404: message = '服务器异常，请稍后再试！'; break;
            case 408: message = '请求超时，请稍后再试！'; break;
            case 500: message = '服务器异常，请稍后再试！'; break;
            case 501: message = '服务未实现，请稍后再试！'; break;
            case 502: message = '网络错误，请稍后再试！'; break;
            case 503: message = '服务不可用，请稍后再试！'; break;
            case 504: message = '网络超时，请稍后再试！'; break;
            case 505: message = 'HTTP版本不受支持！'; break;
            default: message = `未知错误，(${res.message})！`;
        }
        // token过期，跳转登录
        if (res.code == 403 || res.code == 401) {
            ElMessage.error(message)
            sessionStorage.clear();
            let route = router.currentRoute.value;
            if(route.meta.type == 'app'){
                router.push({path: '/app/appLogin'});
            } else {
                router.push({path: 'login'});
            }
        }
    } 
    ElMessage.error(message)
    return Promise.resolve(err);
})

export default axios