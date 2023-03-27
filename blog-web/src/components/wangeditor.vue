<template>
  <div>
    <el-dialog ref="editorDialog" v-model="openDialog" :before-close="closeDialog">
      <el-button @click="btnClick"></el-button>
    <el-select v-model="groupName" style="display: flex; justify-content: left;margin-bottom: 50px;width: 20%;" placeholder="请选择分组">
      <el-option v-for="(item,index) in groupList" :label="item.label" :value="item.value" :key="index"></el-option>
    </el-select>
    <el-input v-model="title"></el-input>
    <Toolbar
      style="border-bottom: 1px solid #ccc;"
      :editor="editorRef"
      :defaultConfig="toolbarConfig"
      :mode="mode"
    />
    <Editor
      style="height: 500px; overflow-y: hidden;"
      v-model="content"
      :defaultConfig="editorConfig"
      :mode="mode"
      @onCreated="handleCreated"
    />
  </el-dialog>
  </div>
</template>
<script>
import "@wangeditor/editor/dist/css/style.css"; // 引入 css

import { onBeforeUnmount, shallowRef,ref } from "vue";
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import { useVModel } from '@vueuse/core';
export default {
  components: { Editor, Toolbar },
  props: {
    valueHtml: {
      type: Object,
      default:(()=>{}),
      require:true
    },
    open: {
      type: Boolean,
      default:false
    },
    closeDialog:{
      type:Object,
      default:(()=>{})
    }
  },
  setup(props,{emit}) {
    // console.log(emit)
    const openDialog = useVModel(props,'open',emit)
    
    const title = useVModel(props,'valueHtml',emit).value.title
    
    const content = useVModel(props,'valueHtml',emit).value.content

    const btnClick = ()=>{
      emit('on-change','加油')
    }

    //分组名称
    let groupName = ref('');
    
    //分组集合
    const groupList = [
      {
        label: "分组一",
        value: "1",
      },
      {
        label: "分组二",
        value: "2",
      },
      {
        label: "分组三",
        value: "3",
      },
    ];
    // 编辑器实例，必须用 shallowRef
    const editorRef = shallowRef();

    // 内容 HTML
    // const valueHtml = ref("<p></p>");

    // 模拟 ajax 异步获取内容
    // onMounted(() => {
    //   setTimeout(() => {
    //     valueHtml.value = "<p>模拟 Ajax 异步设置内容</p>";
    //   }, 1500);
    // });

    const toolbarConfig = {};
    const editorConfig = { placeholder: "请输入内容..." };

    // 组件销毁时，也及时销毁编辑器
    onBeforeUnmount(() => {
      const editor = editorRef.value;
      if (editor == null) return;
      editor.destroy();
    });

    const handleCreated = (editor) => {
      editorRef.value = editor; // 记录 editor 实例，重要！
    };


    return {
      editorRef,
      mode: "default", // 或 'simple'
      toolbarConfig,
      editorConfig,
      handleCreated,
      groupList,
      groupName,
      openDialog,
      title,
      content,
      btnClick
    };
  },
};
</script>
