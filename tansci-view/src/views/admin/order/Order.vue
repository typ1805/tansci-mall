<template>
  <div class="order">
    <el-card>
      <Table :data="tableData" :column="tableTitle" :operation="{show:true, width:160,}" :page="page" :loading="loading"
        @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange">
        <template #search>
            <div><el-input v-model="searchForm.orderId" placeholder="请输入订单ID"></el-input></div>
            <div><el-button @click="onRefresh" icon="RefreshRight" circle></el-button></div>
            <div><el-button @click="onSearch" type="primary" icon="Search">查询</el-button></div>
        </template>
        <template #column="scope">
          <el-button @click="onEdit(scope)" type="text" style="color:var(--edit)">更新订单状态</el-button>
          <el-button @click="onDelete(scope)" type="text" style="color:var(--delete)">删除</el-button>
        </template>
      </Table>
    </el-card>
  </div>
</template>
<script setup>
    import {onMounted, reactive, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from "element-plus"
    import Table from '@/components/common/Table.vue'
    import {orderPage, updateOrder, delOrder} from "@/api/admin/order"

    const state = reactive({
        searchForm:{
            orderId: null,
        },
        loading: false,
        page: {
          current: 1,
          size: 10,
          total: 1,
        },
        tableTitle: [
          {prop:'orderNo',label:'订单ID'},
          {prop:'orderNo',label:'订单号'},
          {prop:'price',label:'订单金额',type:'price'},
          {prop:'discount',label:'优惠金额',type:'price'},
          {prop:'payStatus',alias:'payStatusName',label:'支付状态',type:'tag',option:{type:'info',size:'small',effect:'plain'}},
          {prop:'orderStatus',alias:'orderStatusName',label:'订单状态',type:'tag',option:{type:'info',size:'small',effect:'plain'}},
          {prop:'payType',alias:'payTypeName',label:'支付类型',type:'tag',option:{type:'warning',size:'small',effect:'plain'}},
          {prop:'payTime',label:'支付时间'},
          {prop:'userName',label:'商户名称'},
          {prop:'updateTime',label:'更新时间'},
          {prop:'createTime',label:'创建时间'},
          {prop:'remarks',label:'备注'}
        ],
        tableData:[],
    })

    const {
        searchForm,loading,page,tableTitle,tableData
    } = toRefs(state)

    onMounted(() => {
        onOrderPage();
    })

    const onOrderPage = () =>{
        state.loading = true;
        orderPage(Object.assign(state.page,state.searchForm)).then(res=>{
            state.loading = false;
            state.tableData = res.result.records;
            state.page.current = res.result.current;
            state.page.size = res.result.size;
            state.page.total = res.result.total;
        })
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onOrderPage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onOrderPage();
    }
    const onRefresh = () =>{
        state.searchForm = {
            orderId: null,
        }
        onOrderPage();
    }
    const onSearch = () =>{
        onOrderPage();
    }

    // 编辑状态
    const onEdit = (val) =>{

    }

    // 删除
    const onDelete = (val) =>{
        ElMessageBox.confirm('此操作将永久删除, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            delOrder({id: val.column.row.id}).then(res=>{
              if(res){
                  ElMessage.success('删除成功!');
                  onOrderPage();
              }
            })
        }).catch(e=>{
            console.log(e)
        })
    }
</script>
<style lang="scss" scoped>
</style>