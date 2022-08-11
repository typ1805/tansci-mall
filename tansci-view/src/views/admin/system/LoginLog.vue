<template>
    <div class="loginlog">
        <el-card>
            <Table :data="tableData" :column="tableTitle" :page="page" :loading="loading"
                @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange">
            </Table>
        </el-card>
    </div>
</template>
<script setup>
    import {onMounted, reactive, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from "element-plus"
    import Table from '@/components/common/Table.vue'
    import {loginLogPage} from "@/api/admin/loginLog"

    const state = reactive({
        loading: false,
        page: {
            current: 1,
            size: 10,
            total: 1,
        },
        tableTitle: [
            {prop:'userName',label:'用户名称'},
            {prop:'loginTime',label:'登录时间'},
            {prop:'os',label:'操作系统'},
            {prop:'browser',label:'浏览器'},
            {prop:'ip',label:'请求IP'},
            {prop:'createTime',label:'创建时间'}
        ],
        tableData:[],
    })

    const {
        loading,page,tableTitle,tableData
    } = toRefs(state)

    onMounted(() => {
        onLoginLogPage();
    })

    const onLoginLogPage = () =>{
        state.loading = true;
        loginLogPage({}).then(res=>{
            state.loading = false;
            state.tableData = res.result.records;
            state.page.current = res.result.current;
            state.page.size = res.result.size;
            state.page.total = res.result.total;
        })
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onLoginLogPage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onLoginLogPage();
    }
</script>
<style lang="scss" scoped>
</style>