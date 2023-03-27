<!--
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-24 21:56:12
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-03-24 22:08:30
 * @FilePath: \blog-web\src\view\GenShin.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
    <h1>{{ message }}</h1>
</template>
<script>
import { ref } from "vue";

export default {
  setup() {
    const message = ref("原神nb");

    return {
      message,
    };
  },
};
</script>
