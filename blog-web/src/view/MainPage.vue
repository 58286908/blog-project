<!--
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-20 02:10:02
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-04-18 17:31:11
 * @FilePath: \blog-web\src\view\MainPage.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="bg">
    <el-container>
      <SideNavigate></SideNavigate>
      <el-container>
        <el-container>

          <el-header>
            <h1>喜欢摸鱼把鱼摸出火</h1>
            <span ref="headText">{{ userText }}</span>
            <router-link v-if="validToken" to="/login">登录</router-link>
            <!-- <el-button @click="helloToday">hello</el-button> -->
          </el-header>
          <el-main style="width: 72vw;">
            <!-- <blog-main></blog-main> -->
            <router-view></router-view>
          </el-main>
          <el-affix position="bottom">
            <el-footer style="width: 100%;background-color: aquamarine;" height="30px">
              <el-text tag="b" style="font-size: large;margin: 0 auto;color: black;">桂ICP备2022011078号</el-text>
            </el-footer>
          </el-affix>
        </el-container>
      </el-container>
    </el-container>
    <span></span>

  </div>
</template>
<script>

import SideNavigate from "@/components/SideNavigate";
// import BlogMain from "@/components/BlogMain.vue";
import { onMounted, ref } from "vue";
import { hello } from '@/api/today'
export default {
  components: { SideNavigate },
  setup () {
    const valueHtml = {
      title: '',
      content: ''
    }
    //test api
    const helloToday = (() => {
      hello().then(res => {
        console.log(res)
      })
    })
    const userText = ref('现在是游客模式哦')
    console.log(userText.value)
    const validToken = ref(false)
    onMounted(() => {
      const token = sessionStorage.getItem("token")
      const nickName = sessionStorage.getItem("nickName")
      token != '' && nickName != undefined ? userText.value = "欢迎你：" + nickName : validToken.value = true
      token == '' || nickName == undefined ? userText.value = "现在是游客模式哦" : validToken.value = false
    })

    return {

      valueHtml,
      helloToday,
      userText,
      validToken
    }
  },
};
</script>
<style scoped>
.bg {
  background-color: #f7f8fa;
  /* background */
}

.inner {
  text-align: left;
  padding: 50px;
  width: 100%;
}
</style>
