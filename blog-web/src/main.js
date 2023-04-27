/*
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-16 21:43:44
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-04-23 20:08:47
 * @FilePath: \vue3.0\src\main.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%A
 */
import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index'
import { ElMessage } from 'element-plus'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import 'element-plus/theme-chalk/dark/css-vars.css'
// 引入VueCookies
import VueCookies from 'vue3-cookies'

const app = createApp(App)

app.config.globalProperties.$message = ElMessage
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
app.use(ElementPlus)
.use(VueCookies)
.use(router)
.mount('#app')
