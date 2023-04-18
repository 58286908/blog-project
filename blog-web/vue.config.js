/*
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-16 21:43:44
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-04-16 20:42:22
 * @FilePath: \blog-web\vue.config.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    // host: '0.0.0.0',
    // port: port,
    proxy: {
      // detail: https://cli.vuejs.org/config/#devserver-proxy
      'api': {
        // target: `http://localhost:8888`,
        target: `http://localhost:8002`,
        // target: `http://10.6.3.42:8002`,
        // target: `http://172.18.30.3:8432`,
        changeOrigin: true,
        // pathRewrite: {
        //   ['^' + process.env.VUE_APP_BASE_API]: ''
        // }
        pathRewrite: {
          '/api': ''
        }
      }
    },
    // disableHostCheck: true
  },
})
