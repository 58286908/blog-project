<template>
  <el-dialog v-model="sortOpen" title="新增菜单" width="30vw" @open="openDialog" :close="closeMenuDialog">
    <el-form :model="menuForm" label-width="80px">

      <el-form-item label="父级菜单">
        <el-col :span="11">
          <el-cascader style="width: 100%;" v-model="value" :options="options" @change="handleChange"
            @visible-change="visibleChange" :props="cascaderProp" clearable />
        </el-col>
      </el-form-item>
      <el-form-item label="菜单">
        <el-col :span="11">
          <el-input v-model="menuForm.menuName"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="权限标识">
        <el-col :span="11">
          <el-input v-model="menuForm.perms"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="saveMenu">确定</el-button>
        <el-button @click="closeMenuDialog">取消</el-button>

      </el-form-item>
    </el-form>

  </el-dialog>
</template>

<script>
import { useVModel } from '@vueuse/core'
import { onMounted, reactive, getCurrentInstance } from 'vue'
import { listByMenu } from "@/api/SysMenu"
export default {
  props: {
    menuFormProp: {
      type: Object,
      default: () => { },
      require: true
    },
    menuOptions: {
      type: Array,
      default: () => []
    },
    closeMenuDialog: {
      type: Function,
      default: (() => { })
    },
    menuDialog: {
      type: Boolean,
      default: false
    },
    saveMenu: {
      type: Function,
      default: (() => { })
    },
    // updateMenu: {
    //   type: Function,
    //   default: (() => { })
    // }
    // beforeClose: {
    //   type: Function,
    //   default: (() => { })
    // },
  },

  setup (props, { emit }) {


    //弹窗显示
    const sortOpen = useVModel(props, 'menuDialog', emit)

    //弹窗表单
    const menuForm = useVModel(props, 'menuFormProp', emit)


    const menuName = 'menuName'

    const cascaderProp = reactive({
      value: 'id',
      label: 'menuName',
      expandTrigger: 'hover'
    })

    //打开弹窗触发
    const openDialog = (() => {
      listMenu()
    })

    const value = reactive([])
    //下拉框选择值改变时
    const handleChange = (val) => {
      const id = val[0]
      menuForm.value.parentId = id
      // console.log(id)
      // console.log(menuForm)
    }

    //
    const visibleChange = ((value) => {
      console.log(value)
    })

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

    onMounted(() => {

    })


    return {
      value,
      options,
      handleChange,
      sortOpen,
      menuForm,
      menuName,
      visibleChange,
      openDialog,
      cascaderProp,
    }

  },

}

</script>

<style></style>