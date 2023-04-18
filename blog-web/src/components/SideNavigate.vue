<!--
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-29 22:29:24
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-04-16 13:56:34
 * @FilePath: \blog-web\src\components\Navigate.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <el-aside width="12vw" style="height: 100vh;background-color: #f7f8fa;">
    <el-scrollbar ref="scrollbar">
      <el-menu active-text-color="#ffd04b" background-color="#f7f8fa" class="el-menu-vertical-demo" default-active="1"
        :router="true">
        <el-sub-menu index="1">
          <template #title>
            <el-icon>
              <location />
            </el-icon>
            <span>Java</span>
          </template>
          <el-menu-item-group title="Group One">
            <el-menu-item index="123456" :route="{ path: '/blog-content', query: { id: 123456 } }">
              Java基础（一）
            </el-menu-item>
            <el-menu-item index="1-2">item two</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="Group Two">
            <el-menu-item index="1-3">item three</el-menu-item>
          </el-menu-item-group>
          <el-sub-menu index="1-4">
            <template #title>item four</template>
            <el-menu-item index="1-4-1">item one</el-menu-item>
          </el-sub-menu>
        </el-sub-menu>
        <el-menu-item index="2">
          <el-icon><icon-menu /></el-icon>
          <span>Redis</span>
        </el-menu-item>
        <el-menu-item index="3" disabled>
          <el-icon>
            <document />
          </el-icon>
          <span>RabbitMQ</span>
        </el-menu-item>
        <el-menu-item index="4">
          <el-icon>
            <setting />
          </el-icon>
          <span>Vue</span>
        </el-menu-item>
      </el-menu>
    </el-scrollbar></el-aside>
</template>
<script>
import { onMounted } from 'vue'
export default {
  setup () {




    onMounted(() => {

    })
    return {
    }
  },
}
</script>