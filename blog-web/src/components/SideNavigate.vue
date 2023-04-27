<!--
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-29 22:29:24
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-04-27 01:27:19
 * @FilePath: \blog-web\src\components\Navigate.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <el-aside width="12vw" style="height: 100vh;">
    <el-scrollbar ref="scrollbar">
      <el-menu default-active="1" :router="true">
        <el-sub-menu v-for="(item, index) in menuList" :index="index + ''" :value="item.id" :label="item.menName"
          :key="item.id">
          <template #title>
            <el-icon>
              <component v-if="item.icon != '#'" :is="item.icon"></component>
            </el-icon>
            <!-- <el-icon> -->
            <!-- <CoffeeCup></CoffeeCup> -->
            <!-- <component v-if="item.icon != '#'" :is="item.icon"></component> -->
            <!-- </el-icon> -->
            <span style="font-size: medium;">{{ item.menuName }}</span>
          </template>
          <div v-if="item.children != null">
            <el-menu-item-group v-for="(o, index) in item.children" :key="o.id">
              <el-menu-item :index="index + ''" :route="{ path: '/index', query: { menuName: o.menuName } }">
                <span style="margin-left: 20px;">{{ o.menuName }}</span>
              </el-menu-item>
            </el-menu-item-group>
          </div>
        </el-sub-menu>
      </el-menu>
    </el-scrollbar>
  </el-aside>
</template>
<script>
import { onMounted, reactive } from 'vue'
import { listByMenu } from "@/api/SysMenu"
export default {
  setup () {

    const menuList = reactive([])

    function listMenu () {
      listByMenu().then((res) => {
        menuList.push(...res.data.data)
      })
    }

    onMounted(() => {
      listMenu()
    })
    return {
      menuList,
      listMenu
    }
  },
}
</script>