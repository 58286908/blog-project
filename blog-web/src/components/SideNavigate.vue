<!--
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-29 22:29:24
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-05-13 01:05:53
 * @FilePath: \blog-web\src\components\Navigate.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <el-aside width="12vw" style="height: 100vh;" v-loading="asideLoading">
    <el-scrollbar ref="scrollbar">
      <el-menu default-active="1" :router="true">
        <!-- {{ menuList }} -->
        <el-sub-menu v-for="item in menuList" :index="item.id + ''" :value="item.id" :label="item.menuName"
          :key="item.id">
          <template #title>
            <el-icon v-if="item.icon != 'Vue'">
              <component v-if="item.icon != '#'" :is="item.icon"></component>
            </el-icon>
            <svg v-if="item.icon == 'Vue'" xmlns="http://www.w3.org/2000/svg" width="17" height="17" viewBox="0 0 256 221"
              style="padding-right: 10px;margin-left: 3px;">
              <path fill="#41B883" d="M204.8 0H256L128 220.8L0 0h97.92L128 51.2L157.44 0h47.36Z" />
              <path fill="#41B883" d="m0 0l128 220.8L256 0h-51.2L128 132.48L50.56 0H0Z" />
              <path fill="#35495E" d="M50.56 0L128 133.12L204.8 0h-47.36L128 51.2L97.92 0H50.56Z" />
            </svg>
            <!-- <el-icon> -->
            <!-- <CoffeeCup></CoffeeCup> -->
            <!-- <component v-if="item.icon != '#'" :is="item.icon"></component> -->
            <!-- </el-icon> -->
            <span style="font-size: medium;">{{ item.menuName }}</span>
          </template>
          <div v-if="item.children != null">
            <el-menu-item-group v-for="o in item.children" :key="o.id">
              <el-menu-item :index="o.id + ''" :route="{ path: '/index', query: { menuName: o.menuName } }">
                <span style="margin-left: 20px;">{{ o.menuName }}</span>
              </el-menu-item>
            </el-menu-item-group>
          </div>
        </el-sub-menu>
      </el-menu>
    </el-scrollbar>
  </el-aside>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { listByMenu } from "@/api/SysMenu"
interface Menu {
  icon: string;
  children: any;
  menuName: string;
  id: number
}
let menuList = ref<Menu[]>([{
  icon: '',
  children: [],
  menuName: '',
  id: 0
}])
const asideLoading = ref(false)
function listMenu() {
  menuList.value = []
  asideLoading.value = true
  listByMenu().then((res) => {
    asideLoading.value = false
    menuList.value.push(...res.data.data)
  })
}

onMounted(() => {
  listMenu()
})
</script>