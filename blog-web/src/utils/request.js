/*
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-20 02:10:02
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-04-21 21:19:50
 * @FilePath: \blog-web\src\utils\request.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
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
const service = axios.create({
    // axios中请求配置有baseURL选项，表示请求URL公共部分
    // baseURL: process.env.VUE_APP_BASE_API,
    // 超时
    timeout: 50000
})

// 创建axios实例
const anonymous = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  // baseURL: process.env.VUE_APP_BASE_API,
  // 超时
  timeout: 50000
})

// request拦截器
service.interceptors.request.use(
    (config) => {
      let token = sessionStorage.getItem("token")
        if (token && !config.isJmReport) {
          // console.log(config)
            config.headers.Authorization = 'Bearer ' + token // 让每个请求携带自定义token 请根据实际情况自行修改
        }
        return config
    },
    (error) => {  
        // eslint-disable-next-line no-console
        console.log(error)
        Promise.reject(error)
    }
)

export default service 
export {anonymous}

