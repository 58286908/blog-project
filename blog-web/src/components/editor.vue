<template>
    <div>
        <wangEditor :valueHtml="value"></wangEditor>
    </div>
</template>

<script scoped>
import wangEditor from './wangEditor'
export default {
    name: 'editorGrid',
    components:{wangEditor},
    data(){
        return{
            value:''
        }
    }
}
</script>