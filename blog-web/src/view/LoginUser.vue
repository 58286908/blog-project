<!--
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-04-03 14:00:51
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-05-04 12:06:23
 * @FilePath: \blog-web\src\components\LoginTemplate.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="login">
    <div class="mylogin" align="center">
      <h4>登录</h4>
      <el-form :model="loginForm" :rules="loginRules" label-width="0px">
        <el-form-item label="" prop="username" style="margin-top: 10px">
          <el-row>
            <!-- <el-col :span="2">
                                                                                                                                                                                                                                                                                                                                                                                                                                <span class="el-icon-s-custom"></span>
                                                                                                                                                                                                                                                                                                                                                                                                                              </el-col> -->
            <el-col :span="22">
              <el-input placeholder="账号" v-model="loginForm.username">
              </el-input>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item label="" prop="password">
          <el-row>
            <!-- <el-col :span="2">
                                                                                                                                                                                                                                                                                                                                                                                                                                <span class="el-icon-lock"></span>
                                                                                                                                                                                                                                                                                                                                                                                                                              </el-col> -->
            <el-col :span="22">
              <el-input type="password" placeholder="密码" v-model="loginForm.password"></el-input>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item style="margin-top: 55px">
          <el-button type="primary" :loading="btnLoading" round class="submitBtn" @click="handlerLogin">登录
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
import { getCurrentInstance, onMounted, reactive, ref } from 'vue';
// import { useVModel } from '@vueuse/core';
import { useCookies } from "vue3-cookies";
import { login } from "@/api/sysUser";
import router from '@/router';

export default {

  setup () {
    const loginForm = reactive({
      username: '',
      password: ''
    })

    const btnLoading = ref(false)
    const { proxy } = getCurrentInstance()

    // const route = router
    // console.log(route)
    const loginRules = {}
    // const login = useVModel(props, 'loginForm', emit)

    const { cookies } = useCookies();
    // cookies.set("username", "123456", "123")
    // console.log(cookies)

    function handlerLogin () {
      btnLoading.value = true
      login(loginForm).then(res => {
        if (res.data.code === 200) {
          sessionStorage.setItem("token", res.data.data.Authorization)
          sessionStorage.setItem("nickName", res.data.data.nickName)
          router.push('/')
          proxy.$message.success('登录成功')
          btnLoading.value = false
        } else {
          proxy.$message.error(res.data.msg)
          btnLoading.value = false
        }
      }).catch(error => {
        btnLoading.value = false
      })
    }



    onMounted(() => {

    })
    return {
      loginForm,
      handlerLogin,
      loginRules,
      cookies,
      btnLoading
    }
  },
}
</script>

<style scoped src="@/assets/styles/login.css"></style>