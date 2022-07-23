<template>
  <div class="goods-order">
    <el-card>
      <Table :data="tableData" :column="tableTitle" :operation="{show:true, width: 160,}" :page="page" :loading="loading"
        @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange">
        <template #column="scope">
          
        </template>
      </Table>
    </el-card>
  </div>
</template>
<script setup>
    import {onMounted, reactive, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from "element-plus"
    import Table from '@/components/common/Table.vue'

    const state = reactive({
        loading: false,
        page: {
          current: 1,
          size: 10,
          total: 1,
        },
        tableTitle: [
          {prop:'',label:'',fixed:'left'},
        ],
        tableData:[],
    })

    const {
        loading,page,tableTitle,tableData
    } = toRefs(state)

    onMounted(() => {
        
    })

    const onOrderPage = () =>{
        state.loading = true;
        
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        
    }

    // 删除
    const onDelete = (val) =>{
        
    }
</script>
<style lang="scss" scoped>
</style>