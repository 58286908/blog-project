<template>
  <div>
    <el-dialog ref="editorDialog" v-model="openDialog" :before-close="closeDialog" destroy-on-close draggable align-center
      :close-on-click-modal="false" title="发布博客" width="80vw">
      <!-- <el-button @click="btnClick"></el-button> -->
      <el-form ref="wangForm" :model="form" status-icon :rules="rules">
        <el-form-item label="分组" prop="groupName">
          <el-col :span="8">
            <el-select v-model="form.groupName" placeholder="请选择分组" style="width: 100%;">
              <el-option v-for="(item, index) in groupList" :label="item.label" :value="item.value"
                :key="index"></el-option>
            </el-select>
          </el-col>
        </el-form-item>
        <el-form-item label="标题" prop="title">
          <el-col :span="16">
            <el-input v-model="form.title"></el-input>
          </el-col>
        </el-form-item>
        <Toolbar style="border-bottom: 1px solid #ccc;" :editor="editorRef" :defaultConfig="toolbarConfig" :mode="mode" />

        <Editor style="height: 500px; overflow-y: hidden;" v-model="form.content" :defaultConfig="editorConfig"
          :mode="mode" @onCreated="handleCreated" />
        <el-button type="primary" @click="saveContent">发布</el-button>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
import "@wangeditor/editor/dist/css/style.css"; // 引入 css

import { onBeforeUnmount, shallowRef, onMounted, reactive } from "vue";
import { FormRules } from 'element-plus';
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import { useVModel } from '@vueuse/core';
import { save } from '@/api/textInfo'

export default {
  components: { Editor, Toolbar },
  props: {
    // valueHtml: {
    //   type: Object,
    //   default: (() => { }),
    // },
    open: {
      type: Boolean,
      default: false,
      require: true
    },
    closeDialog: {
      type: Function,
      default: (() => { })
    }
  },
  setup (props, { emit }) {
    const rules = reactive < FormRules > ({
      groupName: [
        { required: true, message: 'Please input Activity name', trigger: 'blur' },
      ],
    })
    const openDialog = useVModel(props, 'open', emit)

    const btnClick = () => {
      emit('on-change', '加油')
    }

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

    // 发布框form
    const form = reactive({});

    // 模拟 ajax 异步获取内容
    // onMounted(() => {
    // setTimeout(() => {
    // valueHtml.value = "<p>模拟 Ajax 异步设置内容</p>";
    // }, 1500);
    // });

    const toolbarConfig = {};
    const editorConfig = { placeholder: "请输入内容..." };

    const saveContent = (() => {
      console.log(form)
      save(form).then(res => {
        console.log(res)
      })
    })

    // 组件销毁时，也及时销毁编辑器
    onBeforeUnmount(() => {
      const editor = editorRef.value;
      if (editor == null) return;
      editor.destroy();
    });

    onMounted(() => {

    })
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
      openDialog,
      // title,
      // content,
      btnClick,
      form,
      rules,
      saveContent
    };
  },
};
</script>
