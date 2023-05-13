<!--
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-30 21:06:36
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-05-13 00:00:08
 * @FilePath: \blog-web\src\components\BlogContent.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <h1 style="font-size: 28px;">{{ form.title }}</h1>
  <br />
  <p v-highlight v-html="form.content"></p>
  <div></div>
</template>

<script setup lang="ts">
import { useRoute } from 'vue-router'
import { getById } from "@/api/textInfo"
import { getCurrentInstance, reactive } from 'vue'

interface FormItem {
  title: string,
  content: string
}
const route = useRoute()
const form = reactive<FormItem>({
  title: '',
  content: ''
})
const { proxy }: any = getCurrentInstance()
getById(route.query.id).then(res => {
  if (res.data.code === 200) {
    form.title = res.data.data.title
    form.content = res.data.data.content
  } else {
    proxy.$message.error(res.data.msg)
  }
})


</script>
