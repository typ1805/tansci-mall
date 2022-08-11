<template>
    <el-config-provider :locale="locale">
        <router-view></router-view>
    </el-config-provider>
</template>
<script setup>
    import {reactive, toRefs} from 'vue'
    import zhCn from 'element-plus/lib/locale/lang/zh-cn'

    const state = reactive({
        locale: zhCn
    })

    const {locale} = toRefs(state)
</script>