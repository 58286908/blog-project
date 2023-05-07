<!--
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-20 02:10:02
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-05-04 20:54:16
 * @FilePath: \blog-web\src\view\MainPage.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div>
    <el-container>
      <SideNavigate></SideNavigate>
      <el-container>
        <el-container>
          <el-header>
            <el-row>
              <h1 style="font-size: larger;">喜欢摸鱼把鱼摸出火</h1>
            </el-row>
            <el-row v-if="isHeaderAlive">
              <span ref="headText">{{ userText }}</span>
              <router-link v-if="validToken" to="/login">登录</router-link>
              <el-button style="margin-left: 15px;" type="danger" size="small" :icon="SwitchButton" v-if="!validToken"
                circle @click="logout" />
              <!-- <el-switch inline-prompt v-model="theme" @click="toggleDark" /> -->
            </el-row>
          </el-header>
          <el-main style="width: 72vw;">
            <router-view v-if="isRouterAlive"></router-view>
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
import { nextTick, onMounted, reactive, ref, watch } from "vue";
import { useDark } from "@vueuse/core";
import { useRoute, useRouter } from 'vue-router';
import { SwitchButton } from '@element-plus/icons-vue'
export default {
  components: { SideNavigate },
  setup () {
    //刷新博客内容页面
    const isRouterAlive = ref(true)

    //刷新头部
    const isHeaderAlive = ref(ref)

    const route = reactive(useRoute())
    const router = useRouter()
    const token = ref(sessionStorage.getItem("token"))
    const nickName = ref(sessionStorage.getItem("nickName"))
    //DOM更新
    watch(() => route.query.menuName, (count, prevCount) => {
      isRouterAlive.value = false
      nextTick(() => {
        isRouterAlive.value = true
      })
      console.log(count, prevCount)
    })

    watch(() => isHeaderAlive.value, (count, prevCount) => {
      nextTick(() => {
        isHeaderAlive.value = true
        router.push('/index')
      })
      console.log(count, prevCount)
    })

    watch(() => token.value, (count, prevCount) => {
      token.value != '' && nickName.value != undefined ? userText.value = "欢迎你：" + nickName.value : validToken.value = true
      token.value == '' || nickName.value == undefined ? userText.value = "现在是游客模式哦" : validToken.value = false
      console.log(count, prevCount)

    })

    function logout () {
      token.value = sessionStorage.removeItem("token")
      nickName.value = sessionStorage.removeItem("nickName")

      isHeaderAlive.value = false
    }

    const theme = ref(false)
    const isDark = useDark({
      // 存储到localStorage/sessionStorage中的Key 根据自己的需求更改
      storageKey: 'useDarkKEY',
      // 暗黑class名字
      valueDark: 'dark',
      // 高亮class名字
      valueLight: 'light',
    })

    // const toggleDark = useToggle(isDark)

    const valueHtml = {
      title: '',
      content: ''
    }

    const userText = ref('现在是游客模式哦')
    const validToken = ref(false)

    onMounted(() => {
      token.value = sessionStorage.getItem("token")
      nickName.value = sessionStorage.getItem("nickName")
      token.value != '' && nickName.value != undefined ? userText.value = "欢迎你：" + nickName.value : validToken.value = true
      token.value == '' || nickName.value == undefined ? userText.value = "现在是游客模式哦" : validToken.value = false
    })

    return {

      valueHtml,
      userText,
      validToken,
      theme,
      // toggleDark,
      isRouterAlive,
      SwitchButton,
      isDark,
      logout,
      isHeaderAlive
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
