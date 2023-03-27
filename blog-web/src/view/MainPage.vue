<!--
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-16 22:08:19
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-03-25 14:21:02
 * @FilePath: \vue3.0\src\view\MainPage.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="bg">
    <el-container>
      <el-header> <h1>喜欢摸鱼把鱼摸出火</h1></el-header>
      <el-container>
        <el-aside width="200px" style="position: relative; left: 200px">
          <el-scrollbar>
            <el-menu
              active-text-color="#ffd04b"
              background-color="#545c64"
              class="el-menu-vertical-demo"
              default-active="2"
              text-color="#fff"
              :router="true"
            >
              <el-sub-menu index="1">
                <template #title>
                  <el-icon><location /></el-icon>
                  <span>Navigator One</span>
                </template>
                <el-menu-item-group title="Group One">
                  <el-menu-item  index="GenShin">
                    <!-- <router-link to="/GenShin"> -->
                      GenShin

                    <!-- </router-link> -->
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
                <span>Navigator Two</span>
              </el-menu-item>
              <el-menu-item index="3" disabled>
                <el-icon><document /></el-icon>
                <span>Navigator Three</span>
              </el-menu-item>
              <el-menu-item index="4">
                <el-icon><setting /></el-icon>
                <span>Navigator Four</span>
              </el-menu-item>
            </el-menu>
          </el-scrollbar></el-aside
        >
        <el-main>
          <el-button @click="openEditorDialog">发布博客</el-button>
          <router-link to="/GenShin">跳转到原神</router-link>
          <router-view></router-view>
          <wangEditor
            :open="openEditor"
            :closeDialog="closeDialog"
            :valueHtml="valueHtml"
            @on-change="cancel"
          ></wangEditor>
          <el-card
            :body-style="{ padding: '0px', marginBottom: '1px' }"
            v-for="item in lists"
            :key="item.name"
            style="
              width: 800px;
              height: 200px;
              display: flex;
              margin: 0px auto 35px;
              opacity: 0.9;
            "
          >
            <div class="inner">
              <span>{{ item.name }}</span>
              <div class="bottom card-header">
                <span class="time">{{ currentDate }}</span>
                <el-button class="button" @click="saveBlogs"
                  >发送请求</el-button
                >
              </div>
            </div>
          </el-card>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
// import save from "@/api/textInfo";
import axios from "axios";
import wangEditor from "@/components/wangEditor";
export default {
  name: "mainPageGrid",
  components: { wangEditor },
  data() {
    return {
      lists: [],
      currentDate: "2021-06-01",
      cardList: [
        {
          title: "123",
          text: "3213213232132111111111111111111111111111111111111111111111111111111111111111",
        },
        {
          title: "qweqeqweqw",
          text: "32132132321321",
        },
        {
          title: "12ytuyt3",
          text: "32132132321321",
        },
      ],
      openEditor: false,
      valueHtml:{
        title:'123',
        content:'<p>test</p>'
      }
    };
  },
  methods: {
    cancel(value){
      console.log(value)
    },
    saveBlogs() {
      axios
        .post("/api/blog/textInfo/save", {})
        .then((res) => [console.log(res)]);
    },
    openEditorDialog() {
      this.openEditor = true;
    },
    closeDialog() {
      this.openEditor = false;
    },
  },
  mounted() {
    this.lists = [
      {
        imgUrl:
          "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg",
        name: "鹿",
      },
      {
        imgUrl:
          "https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg",
        name: "马",
      },
      {
        imgUrl:
          "https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg",
        name: "山狮",
      },
    ];
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
