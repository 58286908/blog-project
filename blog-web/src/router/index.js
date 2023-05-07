/*
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-24 21:37:32
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-05-04 13:13:51
 * @FilePath: \blog-web\src\router\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { createRouter, createWebHistory } from "vue-router";

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
        // alias: '/',
        component: BlogMain
      },
      {
        path:'blogContent',
        component: BlogContent,
        // redirect: to => {
          // console.log(to)
          // 方法接收目标路由作为参数
          // return 重定向的字符串路径/路径对象
          // return { path: 'blogContent',name:'BlogContent',
          //  }
        // },
      }
     ] 
    },
    {
      path: "/login",
      component:LoginUser
    }
  ];

const router = createRouter({
  // mode: 'history',
  // base: '/ishow/qian/dist/',
  // base:'/',
  history: createWebHistory(),
  routes
});

export default router;
