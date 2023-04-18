<!--
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-30 11:11:18
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-04-19 02:25:42
 * @FilePath: \blog-web\src\components\BlogMain.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <!-- <el-button @click="openEditorDialog">发布博客</el-button> -->

  <WangEditor :open="openEditor" :closeDialog="closeDialog" @on-change="cancel" />
  <el-scrollbar max-height="90vh" view-style="background-color: #f2f3f5;padding-left: 20px;padding-right: 20px;">
  <div style="background-color:#B5B8BD;padding: 20px;">
    <el-card style="background-color: #545C64;height: 32vh;">123</el-card>
    <el-button type="primary" @click="openEditorDialog">发布博客</el-button>
  </div>
  <el-row :gutter="24">
      <el-col :xl="8" :lg="12" :xs="24" v-for="item in lists" :key="item.title">
        <el-card style="opacity: 0.9;margin-top: 20px;width: 100%;height: 25vh;font-size: 18px;">
          <!-- <template>
                                                                                                                                                                                      <div>
                                                                                                                                                                                        <span>Card name</span>
                                                                                                                                                                                        <el-button class="button">Operation button</el-button>
                                                                                                                                                                                      </div>
                                                                                                                                                                                    </template> -->
          <router-link style="align:left;" to="/BlogContent"><span class="title-font">{{ item.title
          }}</span></router-link>
          <div>
            <span class="time" v-html="item.content"></span>
          </div>
          <div style="float: right;">
            <el-button type="primary" @click="openEditorDialog">阅读</el-button>
            <el-button type="primary" @click="openEditorDialog">编辑</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>

  </el-scrollbar>
</template>

<script>
import WangEditor from "@/components/WangEditor";
import { ref, onMounted } from "vue";
import { list } from "@/api/textInfo"
export default {
  components: { WangEditor },
  setup () {
    // const lists = ref(
    //   [
    //     {
    //       imgUrl:
    //         "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg",
    //       name: "鹿",
    //     },
    //     {
    //       imgUrl:
    //         "https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg",
    //       name: "马",
    //     },
    //     {
    //       imgUrl:
    //         "https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg",
    //       name: "山狮",
    //     },
    //     {
    //       imgUrl:
    //         "https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg",
    //       name: "马",
    //     },
    //     {
    //       imgUrl:
    //         "https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg",
    //       name: "山狮",
    //     },
    //     {
    //       imgUrl:
    //         "https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg",
    //       name: "马",
    //     },
    //     {
    //       imgUrl:
    //         "https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg",
    //       name: "山狮",
    //     },
    //     {
    //       imgUrl:
    //         "https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg",
    //       name: "马",
    //     },
    //     {
    //       imgUrl:
    //         "https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg",
    //       name: "山狮",
    //     },
    //     {
    //       imgUrl:
    //         "https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg",
    //       name: "马",
    //     },
    //     {
    //       imgUrl:
    //         "https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg",
    //       name: "山狮",
    //     },
    //   ]
    // )
    let currentDate = "2021-06-01"

    const openEditor = ref(false)

    const cancel = ((value) => {
      console.log(value)
    });
    const saveBlogs = (() => {
    });

    let lists = ref([])

    const openEditorDialog = (() => {
      openEditor.value = true
    });

    const closeDialog = (() => {
      openEditor.value = false
      console.log(openEditor.value)
    });

    list().then(res => {
      // console.log(res)
      lists.value = res.data.data
      console.log(lists)
    })
    onMounted(() => {


    })

    return {
      cancel,
      saveBlogs,
      openEditorDialog,
      closeDialog,
      lists,
      currentDate,
      openEditor,
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
</style>