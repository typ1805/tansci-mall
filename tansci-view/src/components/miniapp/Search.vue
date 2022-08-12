<template>
    <div class="search-container">
        <el-input @focus="toSearch" v-model="keyword" size="large" prefix-icon="Search" placeholder="请输入关键字">
            <template #suffix>
                <el-button @click="onSearch" type="danger" round style="margin-right: -10px;">搜索</el-button>
            </template>           
        </el-input>
    </div>
</template>
<script setup>
    import {defineEmits, reactive, toRefs} from 'vue'
    import {useRouter} from 'vue-router'

    const router = useRouter()
    const emit = defineEmits([
        'onSearch',
    ])

    const state = reactive({
        keyword: null
    })

    const {
        keyword,
    } = toRefs(state)

    const onSearch = () =>{
        if(!state.keyword){
            router.push({path: '/app/search'});
            return;
        }
        emit('onSearch', state.keyword);
    }

    const toSearch = () =>{
        router.push({path: '/app/search'});
    }

</script>
<style lang="scss">
    .search-container{
        .el-input .el-input__wrapper{
            border-radius: 20px !important;
        }
        .el-input__wrapper:hover{
            box-shadow: 0 0 0 1px #fff inset;
        }
    }
</style>