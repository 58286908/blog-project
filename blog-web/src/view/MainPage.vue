<!--
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-20 02:10:02
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-03-31 11:41:42
 * @FilePath: \blog-web\src\view\MainPage.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="bg">
    <el-container>
      <el-header>
        <h1>喜欢摸鱼把鱼摸出火</h1>
      </el-header>
      <el-container>
        <SideNavigate></SideNavigate>
        <el-main>
          <!-- <BlogMain></BlogMain> -->
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
    <span>桂ICP备2022011078号</span>

  </div>
</template>
<script>

import SideNavigate from "@/components/SideNavigate";
// import BlogMain from "@/components/BlogMain.vue";
import { onMounted } from "vue";
export default {
  components: { SideNavigate },
  setup () {
    const valueHtml = {
      title: '',
      content: ''
    }
    onMounted(() => {

    })

    return {

      valueHtml,

    }
  },
};
</script>
<style scoped>
.bg {
  background: url(../assets/106266425_p0_master1200.jpg);
  height: 100vh;
}

.inner {
  text-align: left;
  padding: 50px;
  width: 100%;
}
</style>
