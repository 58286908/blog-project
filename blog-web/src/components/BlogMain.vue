<!--
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-30 11:11:18
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-05-21 21:42:31
 * @FilePath: \blog-web\src\components\BlogMain.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <MenuSort ref="menuSort" :menuFormProp="menuForm" :saveMenu="saveMenu" :closeMenuDialog="closeMenuDialog" />

  <WangEditor @on-change="fighting" :open="openEditor" :closeDialog="closeDialog" @reload="cardChangeCard"
    :wangForm="wangForm" />
  <el-scrollbar max-height="90vh" view-style="padding-left: 20px;padding-right: 20px;">
    <div style="background-color:#262727;padding: 20px">
      <el-card style="background-color: #545C64;height: 32vh;position: relative;">
        <transition name="el-fade-in">
          <p class="transition-box">我爱代码，BUG爱我，我们都有光明的未来</p>
        </transition>
        <div style="position: absolute;left: 20px;bottom: 10px;">
          <el-avatar style="border: #000 solid 5px;" :size="120" :src="headUrl" />
        </div>

      </el-card>
      <div style="margin-top: 5px;">
        <el-button color="#626aef" :dark="isDark" @click="openEditorDialog">发布博客</el-button>
        <el-button color="#626aef" :dark="isDark" @click="openMenuDialog">新增菜单</el-button>
      </div>
    </div>
    <el-row :gutter="24" v-if="cardOpen" v-loading="navigationLoading">
      <el-col :xl="8" :lg="12" :xs="24" v-for="item in lists" :key="item.title">
        <el-card style=" opacity: 0.9;margin-top: 20px;width: 100%;height: 25vh;position: relative;">
          <router-link :to="{
            path: '/BlogContent', query: {
              id: item.id,
              menuName: item.menuName
            }
          }">
            <p class="card-title">{{ item.title }}</p>
          </router-link>
          <p class="card-content">
            {{ item.textContent }}
          </p>
          <div style="position: absolute;bottom: 20px; right: 20px">
            <el-button color="#626aef" :dark="isDark" @click="blogContentToRead(item.id, item.menuName)">阅读</el-button>
            <el-button color="#626aef" :dark="isDark" @click="blogContentUpdate(item.id)">编辑</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>

  </el-scrollbar>
</template>

<script setup lang="ts">
import { save } from "@/api/SysMenu"
import WangEditor from "@/components/WangEditor.vue";
import MenuSort from "@/components/MenuSort.vue";
import { ref, reactive, onMounted, getCurrentInstance, nextTick } from "vue";
import { list, getById } from "@/api/textInfo"
import { LocationQueryValue, useRoute, useRouter } from 'vue-router';
import headUrl from "@/utils/HeadImag";
type str = {
  menuName: string | LocationQueryValue[]
}

interface card {
  id: number,
  title: string,
  content: string,
  menuName: string,
  textContent: string
}
const isDark = ref(true)
const { proxy }: any = getCurrentInstance()
const cardOpen = ref(true)

const navigationLoading = ref(false)
function cardChangeCard() {
  cardOpen.value = false
  openEditor.value = false
  listCard()
  nextTick(() => {
    cardOpen.value = true
  })
}
const fighting = (v: string) => {
  console.log(v)
}

//菜单-新增打开方法
const openMenuDialog = (() => {
  proxy.$refs.menuSort.sortOpen = true
  console.log(proxy)
})

//新增-菜单关闭回调
const closeMenuDialog = (() => {
  proxy.$refs.menuSort.sortOpen = false
})

//新增-菜单显示控制
const menuDialog = ref(false)

//菜单-新增
const saveMenu = (() => {
  save(menuForm).then(res => {
    res.data.code === 200 ? proxy.$message.success(res.data.msg) : proxy.$message.error(res.data.msg)
    menuDialog.value = false
  })

  // console.log(menuForm)
});

//wangForm表单
const wangForm = reactive({
  menuId: '',
  title: '',
  content: '',
  id: ''
})

//菜单表单
const menuForm = reactive({
  menuName: '',
  perms: '',
  parentId: ''
})
//富文本显示
const openEditor = ref(false)

//卡片集合
let lists = ref<card[]>([
  {
    id: 0,
    title: '',
    content: '',
    menuName: '',
    textContent: ''
  }
])

//打开富文本
const openEditorDialog = (() => {
  openEditor.value = true
});

//关闭富文本
const closeDialog = (() => {
  openEditor.value = false
  wangForm.id = ''
  wangForm.content = ''
  wangForm.title = ''
  wangForm.menuId = ''
});

const route = useRoute()
const menu = reactive<str>({
  menuName: '无'
})

function queryParm() {
  if (route.query.menuName != undefined) {
    menu.menuName = route.query.menuName
  }
}
queryParm()
//查询卡片集合
function listCard() {
  navigationLoading.value = true
  list(menu.menuName).then(res => {
    if (res.data.code === 200) {
      navigationLoading.value = false
      lists.value = res.data.data
      console.log(lists)
    }
  }).catch(error => {
    navigationLoading.value = false
    console.log(error)
  })

}

listCard()
const router = useRouter();

function blogContentToRead(id: number, menuName: string) {
  router.push({
    path: '/blogContent',
    query: {
      id: id,
      menuName: menuName
    },
  })
}

function blogContentUpdate(id: number) {
  getById(id).then(res => {
    if (res.data.code === 200) {
      Object.assign(wangForm, res.data.data)
      openEditor.value = true
    }
  })
}
onMounted(() => {
})
/*global defineExpose,*/
defineExpose({ cardChangeCard })

</script>
<style scoped>
.transition-box {
  text-align: center;
}

.card-content {
  white-space: normal;
  word-break: break-all;
  display: -webkit-box;
  text-overflow: ellipsis;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 5;
  overflow: hidden;
  width: 100%;
}

.card-title {
  white-space: normal;
  word-break: break-all;
  display: -webkit-box;
  text-overflow: ellipsis;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  overflow: hidden;
  width: 100%;
}
</style>