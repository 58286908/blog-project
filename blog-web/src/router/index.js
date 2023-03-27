/*
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-24 21:37:32
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-03-25 13:52:33
 * @FilePath: \blog-web\src\router\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { createRouter, createWebHashHistory } from "vue-router";

const GenShin = () => import("@/view/GenShin.vue");
const MainPage = () => import("@/view/MainPage.vue");

const routes = [
  { path: "/GenShin", component: GenShin },
  { path: "/", component: MainPage },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;