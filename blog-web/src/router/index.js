/*
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-24 21:37:32
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-04-25 14:36:11
 * @FilePath: \blog-web\src\router\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { createRouter, createWebHashHistory } from "vue-router";

const GenShin = () => import("@/view/GenShin.vue");
const MainPage = () => import("@/view/MainPage.vue");
const BlogMain = ()=> import("@/components/BlogMain")
const LoginUser = ()=> import("@/view/LoginUser")
const BlogContent = ()=> import("@/components/BlogContent")

const routes = [
  { path: "/gen-shin", component: GenShin },
  { 
    path: "/",
     component: MainPage,
     redirect:'index',
     children:[
      {
        path:'index',
        component: BlogMain
      },
      {
        path:'blogContent',
        name:'BlogContent',
        component: BlogContent
      }
     ] 
    },
    {
      path: "/login",
      component:LoginUser
    }
  ];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
