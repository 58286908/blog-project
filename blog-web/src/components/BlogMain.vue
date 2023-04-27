<!--
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-30 11:11:18
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-04-27 15:33:44
 * @FilePath: \blog-web\src\components\BlogMain.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <MenuSort :menuFormProp="menuForm" :saveMenu="saveMenu" :menuDialog="menuDialog" :closeMenuDialog="closeMenuDialog" />

  <WangEditor :open="openEditor" :closeDialog="closeDialog" @reload="cardChangeCard" :wangForm="wangForm" />
  <el-scrollbar max-height="90vh" view-style="padding-left: 20px;padding-right: 20px;">
    <div style="background-color:#262727;padding: 20px;">
      <el-card style="background-color: #545C64;height: 32vh;">123</el-card>
      <el-button color="#626aef" :dark="isDark" @click="openEditorDialog">发布博客</el-button>
      <el-button color="#626aef" :dark="isDark" @click="openMenuDialog">新增菜单</el-button>
    </div>
    <el-row :gutter="24" v-if="cardOpen">
      <el-col :xl="8" :lg="12" :xs="24" v-for="item in lists" :key="item.title">
        <el-card style=" opacity: 0.9;margin-top: 20px;width: 100%;height: 25vh;font-size: 18px;">
          <router-link to="/BlogContent">
            <p class="card-title">{{ item.title }}</p>
          </router-link>
          <p class="card-content" v-html="item.content">
          </p>
          <div style="float: right;">
            <el-button color="#626aef" :dark="isDark" @click="blogContentToRead(item.id, item.menuName)">阅读</el-button>
            <el-button color="#626aef" :dark="isDark" @click="blogContentUpdate(item.id)">编辑</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>

  </el-scrollbar>
</template>

<script>
import { save } from "@/api/SysMenu"
import WangEditor from "@/components/WangEditor";
import MenuSort from "@/components/MenuSort";
import { ref, reactive, onMounted, getCurrentInstance, nextTick } from "vue";
import { list, getById } from "@/api/textInfo"
import { useRoute, useRouter } from 'vue-router';
export default {
  components: { WangEditor, MenuSort },
  setup () {
    const isDark = ref(true)
    const { proxy } = getCurrentInstance()
    const cardOpen = ref(true)

    function cardChangeCard () {
      cardOpen.value = false
      openEditor.value = false
      listCard()
      nextTick(() => {
        cardOpen.value = true
      })
    }
    //菜单-新增打开方法
    const openMenuDialog = (() => {
      menuDialog.value = true
    })

    //新增-菜单关闭回调
    const closeMenuDialog = (() => {
      menuDialog.value = false
      // console.log(menuDialog)
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
      content: ''
    })

    //菜单表单
    const menuForm = reactive({
      menuName: '',
      perms: '',
      parentId: ''
    })
    //富文本显示
    const openEditor = ref(false)

    //emit练习
    const cancel = ((value) => {
      console.log(value)
    });

    //保存富文本
    const saveBlogs = (() => {
    });

    //卡片集合
    let lists = ref([])

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
    });

    const route = useRoute()
    const menu = reactive({
      menuName: '无'
    })

    function queryParm () {
      if (route.query.menuName != undefined) {
        menu.menuName = route.query.menuName
      }
    }
    queryParm()
    //查询卡片集合
    function listCard () {
      list(menu.menuName).then(res => {
        lists.value = res.data.data
      })
    }
    listCard()
    const router = useRouter();

    function blogContentToRead (id, menuName) {
      router.push({
        path: '/blogContent',
        query: {
          id: id,
          menuName: menuName
        },
      })
    }

    function blogContentUpdate (id) {
      getById(id).then(res => {
        if (res.data.code === 200) {
          Object.assign(wangForm, res.data.data)
          openEditor.value = true
        }
      })
    }
    onMounted(() => {


    })

    return {
      cancel,
      saveBlogs,
      openEditorDialog,
      closeDialog,
      lists,
      openEditor,
      menuForm,
      saveMenu,
      menuDialog,
      closeMenuDialog,
      openMenuDialog,
      isDark,
      blogContentToRead,
      queryParm,
      wangForm,
      blogContentUpdate,
      cardOpen,
      cardChangeCard
    }
  },
}
</script>
<style scoped>
.title-font {
  color: black;

}

.title-font:hover {
  color: blue;
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