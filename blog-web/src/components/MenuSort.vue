<template>
  <el-dialog ref="menuInsert" v-model="sortOpen" title="新增菜单" width="30vw" @open="openDialog" :close="closeMenuDialog">
    <el-form :model="menuForm" label-width="80px">

      <el-form-item label="父级菜单">
        <el-col :span="12">
          <el-cascader style="width: 100%;" v-model="menuForm.parentId" :options="options" @change="handleChange"
            @visible-change="visibleChange" :props="cascaderProp" clearable />
        </el-col>
      </el-form-item>
      <el-form-item label="菜单">
        <el-col :span="12">
          <el-input v-model="menuForm.menuName"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="权限标识">
        <el-col :span="12">
          <el-input v-model="menuForm.perms"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="菜单图标">
        <el-col :span="12">
          <el-input v-model="menuForm.icon" placeholder="请输入Element图标"></el-input>
        </el-col>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="saveMenu">确定</el-button>
        <el-button @click="closeMenuDialog">取消</el-button>

      </el-form-item>
    </el-form>

  </el-dialog>
</template>

<script setup lang="ts">
// import { useVModel } from '@vueuse/core'
import { ref, onMounted, reactive, getCurrentInstance, toRef } from 'vue'
import { listByMenu } from "@/api/SysMenu"
/*global defineExpose,defineProps*/
const props = defineProps({
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
  saveMenu: {
    type: Function,
    default: (() => { })
  },
})


const { proxy }: any = getCurrentInstance()
//弹窗显示
const sortOpen = ref<boolean>(false)
// useVModel(props, 'menuDialog', emit)

//弹窗表单
const menuForm = toRef(props, 'menuFormProp')

// function beforeClose(done) {
//   console.log(done)
//   console.log(done.shouldCancel)
//   sortOpen.value = false
// }

// const menuName = 'menuName'

const cascaderProp = reactive({
  checkStrictly: true,
  value: 'id',
  label: 'menuName',
  expandTrigger: 'hover'
})

//打开弹窗触发
const openDialog = (() => {
  listMenu()
})

//下拉框选择值改变时
const handleChange = (val: any) => {
  const id = val[0]
  menuForm.value.parentId = id
  // console.log(id)
  // console.log(menuForm)
}

//
const visibleChange = ((value: any) => {
  console.log(value)
})

let options = reactive<object[]>([])



async function listMenu() {
  options.length = 0
  await listByMenu().then((res) => {
    options.push(...res.data.data)
    if (res.data.code !== 200)
      proxy.$message.error(res.data.msg)
  })
}

onMounted(() => {

})
defineExpose({ sortOpen })
</script>

<style></style>