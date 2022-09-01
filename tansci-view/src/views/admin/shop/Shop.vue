<template>
    <div class="shop">
        <el-card>
        <Table :data="tableData" :column="tableTitle" :operation="{show:true, width: 60,}" :page="page" :loading="loading"
            @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange" @onSwitchChange="onSwitchChange">
            <template #search>
                <div><el-input v-model="searchForm.name" placeholder="请输入名称"></el-input></div>
                <div><el-button @click="onRefresh" icon="RefreshRight" circle></el-button></div>
                <div><el-button @click="onSearch" type="primary" icon="Search">查询</el-button></div>
            </template>
            <template #column="scope">
                <el-button @click="onDelete(scope)" type="text" style="color:var(--delete)">删除</el-button>
            </template>
        </Table>
        </el-card>
    </div>
</template>
<script setup>
    import {onMounted, reactive, ref, unref, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from "element-plus"
    import Table from '@/components/common/Table.vue'
    import {shopPage,updateShop,delShop} from "@/api/admin/shop"

    const addFormRef = ref(null)
    const state = reactive({
        searchForm:{
            name: null,
        },
        loading: false,
        page: {
            current: 1,
            size: 10,
            total: 1,
        },
        tableTitle: [
            {prop:'',label:'',fixed:'left'},
            {prop:'name',label:'店铺名称'},
            {prop:'intro',label:'店铺介绍'},
            {prop:'avatar',label:'头像',type:'image'},
            {prop:'level',label:'店铺星级',type:'rate'},
            {prop:'status',label:'状态',type:'switch',
                option:{
                    activeValue:0,activeColor:'#13ce66',activeText:'正常',
                    inactiveValue:1,inactiveColor:'#ff4949',inactiveText:'下线',
                    inlinePrompt: false,size:'large'
                }
            },
            {prop:'username',label:'商户'},
            {prop:'updateTime',label:'更新时间'},
            {prop:'createTime',label:'创建时间'},
            {prop:'remarks',label:'描述'},
        ],
        tableData:[],
    })

    const {
        searchForm,loading,page,tableTitle,tableData
    } = toRefs(state)

    onMounted(() => {
        onShopPage();
    })

    const onShopPage = () =>{
        state.loading = true;
        shopPage(Object.assign(state.page,state.searchForm)).then(res=>{
            state.loading = false;
            state.tableData = res.result.records;
            state.page.current = res.result.current;
            state.page.size = res.result.size;
            state.page.total = res.result.total;
        })
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onShopPage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onShopPage();
    }
    const onRefresh = () =>{
        state.searchForm = {
            name: null,
        }
        onShopPage();
    }
    const onSearch = () =>{
        onShopPage();
    }

    // 下线
    const onSwitchChange = (row) =>{
        updateShop({
            shopId: row.shopId,
            status: row.status
        }).then(res=>{
            if(res){
                ElMessage.success('操作成功！');
                onShopPage();
            }
        });
    }

    // 删除
    const onDelete = (val) =>{
        ElMessageBox.confirm('此操作将永久删除, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            delShop({id: val.column.row.id}).then(res=>{
                if(res){
                    ElMessage.success('删除成功!');
                    onShopPage();
                }
            })
        }).catch(e=>{
            console.log(e)
        })
    }
</script>
<style lang="scss" scoped>
</style>