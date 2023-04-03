<!--
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-30 11:11:18
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-03-30 21:09:49
 * @FilePath: \blog-web\src\components\BlogMain.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <el-button @click="openEditorDialog">发布博客</el-button>
  <router-link to="/GenShin">跳转到原神</router-link>
  <!-- <router-view></router-view> -->
  <WangEditor :open="openEditor" :closeDialog="closeDialog" @on-change="cancel" />
  <el-card :body-style="{ padding: '0px', marginBottom: '1px' }" v-for="item in lists" :key="item.name"
    style="width: 800px;height: 200px;margin: 0px auto 35px;opacity: 0.9                                                                                                                                       ">
    <div class="inner">
      <!-- <el-text truncated>{{ item.name }}</el-text> -->
      <router-link to="/BlogContent">{{ item.name }}</router-link>
      <div class="bottom card-header">
        <span class="time">{{ currentDate }}</span>
        <el-button class="button" @click="saveBlogs">发送请求</el-button>
      </div>
    </div>
  </el-card>
</template>

<script>
import axios from "axios";
import WangEditor from "@/components/WangEditor";
import { ref } from "vue";
export default {
  components: { WangEditor },
  setup () {
    const lists = ref([{
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
    ])
    let currentDate = "2021-06-01"
    const cardList = [
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
    ]
    const openEditor = ref(false)
    const cancel = ((value) => {
      console.log(value)
    });
    const saveBlogs = (() => {
      axios
        .post("/api/blog/textInfo/save", {})
        .then((res) => [console.log(res)]);
    });

    const openEditorDialog = (() => {
      openEditor.value = true
    });
    const closeDialog = (() => {
      openEditor.value = false
      console.log(openEditor.value)
    });
    return {
      cancel,
      saveBlogs,
      openEditorDialog,
      closeDialog,
      lists,
      currentDate,
      cardList,
      openEditor,
    }
  },
}
</script>