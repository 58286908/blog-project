/*
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-16 21:43:44
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-05-01 22:13:31
 * @FilePath: \blog-web\vue.config.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
// vue.config.js
const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  // 选项
  publicPath: process.env.NODE_ENV === 'production'? './': '/',
  transpileDependencies:true,
  assetsDir:'./static',
  lintOnSave: true,
  configureWebpack:{
  devServer:{
    proxy:{
      '/api':{
        // target:'http://localhost:8002',
        target:'http://127.0.0.1:8002',
        changeOrigin: true,
        secure: false, 
      }
    }
    
  }
}
})