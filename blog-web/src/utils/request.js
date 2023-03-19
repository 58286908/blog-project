// import Vue from 'vue'
import axios from 'axios'
// import router from '../router'
// import { Message, MessageBox, Notification } from 'element-ui'
// import store from '@/store'
// import { getToken } from '@/utils/auth'
// import * as wlUtils from '@/utils/wl-utils'

// 全局方法挂载
// Vue.prototype.$axios = axios

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'

// 创建axios实例
// const service = axios.create({
//     // axios中请求配置有baseURL选项，表示请求URL公共部分
//     baseURL: process.env.VUE_APP_BASE_API,
//     // 超时
//     timeout: 50000
// })

// request拦截器
// service.interceptors.request.use(
    // (config) => {
        // if (getToken() && !config.isJmReport) {
            // config.headers.Authorization = 'Bearer ' + getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
        // }
        // return config
    // },
    // (error) => {
        // eslint-disable-next-line no-console
        // console.log(error)
        // Promise.reject(error)
    // }
// )
