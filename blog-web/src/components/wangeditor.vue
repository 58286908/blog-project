<template>
  <div>
    <el-dialog ref="editorDialog" @open="openDlg" v-model="openDialog" :before-close="closeDialog" destroy-on-close
      draggable align-center :close-on-click-modal="false" title="发布博客" width="80vw">
      <!-- <el-button @click="btnClick"></el-button> -->
      <el-form ref="wangForm" :model="form" status-icon>
        <el-form-item label="分组" prop="menuId">
          <el-col :span="8">
            <el-cascader style="width: 100%;" v-model="form.menuId" :options="options" @change="handleChange"
              :props="cascaderProp" clearable />
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

import { onBeforeUnmount, shallowRef, onMounted, reactive, getCurrentInstance } from "vue";
import { FormRules } from 'element-plus';
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import { useVModel } from '@vueuse/core';
import { save, update } from '@/api/textInfo'
import { listByMenu } from "@/api/SysMenu"
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
    },
    wangForm: {
      type: Object,
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
    const form = useVModel(props, "wangForm", emit)
    // const value = reactive([])
    //下拉框选择值改变时
    const handleChange = (val) => {
      const id = val[(val.length - 1)]
      form.value.menuId = id
    }

    let options = reactive([])

    const { proxy } = getCurrentInstance()

    async function listMenu () {
      options.length = 0
      await listByMenu().then((res) => {
        // res.data.data.map(res => {
        //   res.label = res.menuName
        //   res.value = res.menuName
        // })
        options.push(...res.data.data)
        // console.log(options)
        if (res.data.code !== 200)
          proxy.$message.error(res.data.msg)
      })
    }

    const btnClick = () => {
      emit('on-change', '加油')
    }

    // 编辑器实例，必须用 shallowRef
    const editorRef = shallowRef();

    // 发布框form
    // const form = reactive({});

    // 模拟 ajax 异步获取内容
    // onMounted(() => {
    // setTimeout(() => {
    // valueHtml.value = "<p>模拟 Ajax 异步设置内容</p>";
    // }, 1500);
    // });

    const cascaderProp = reactive({
      value: 'id',
      label: 'menuName',
      expandTrigger: 'hover'
    })

    const toolbarConfig = {};
    const editorConfig = { placeholder: "请输入内容..." };

    function saveContent () {
      const request = form.value.id ? update : save
      request(form.value).then(res => {
        if (res.data.code === 200) {
          proxy.$message.success(res.data.msg)
          proxy.$parent.cardChangeCard()
        } else {
          proxy.$message.error(res.data.msg)
        }
      })
    }

    // 组件销毁时，也及时销毁编辑器
    onBeforeUnmount(() => {
      const editor = editorRef.value;
      if (editor == null) return;
      editor.destroy();
    });

    //打开弹窗触发
    const openDlg = (() => {
      listMenu()
    })

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
      openDialog,
      // title,
      // content,
      btnClick,
      form,
      rules,
      saveContent,
      handleChange,
      cascaderProp,
      options,
      openDlg
    };
  },
};
</script>
