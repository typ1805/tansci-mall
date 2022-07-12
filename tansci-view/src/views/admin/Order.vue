<template>
  <div class="order">
    <el-card>
      <Table :data="tableData" :column="tableTitle" :operation="{show:true, width: mobile?80:160,}" :page="page" :loading="loading"
        @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange">
        <template #column="scope">
          <el-button @click="onDelete(scope)" type="text" style="color:var(--delete)">删除</el-button>
        </template>
      </Table>
    </el-card>
  </div>
</template>
<script setup>
    import {onMounted, reactive, toRefs} from 'vue'
    import Table from '@/components/common/Table.vue'
    import {orderPage, delOrder} from "@/api/systemApi";
    import {ElMessage, ElMessageBox} from "element-plus";
    import {isMobile} from '@/utils/utils'

    const state = reactive({
        mobile: false,
        loading: false,
        page: {
          current: 1,
          size: 10,
          total: 1,
        },
        tableTitle: [
          {prop:'',label:'',fixed:'left'},
          {prop:'goodsName',label:'商品名称'},
          {prop:'status',alias:'statusName',label:'状态',type:'tag',option:{type:'info',size:'small',effect:'plain'}},
          {prop:'payDesc',label:'支付说明'},
          {prop:'payTime',label:'支付时间'},
          {prop:'payType',alias:'payTypeName',label:'支付类型',type:'tag',option:{type:'warning',size:'small',effect:'plain'}},
          {prop:'userName',label:'商户'},
          {prop:'updateTime',label:'更新时间'},
          {prop:'createTime',label:'创建时间'},
          {prop:'remarks',label:'备注'}
        ],
        tableData:[],
    })

    const {
        mobile,loading,page,tableTitle,tableData
    } = toRefs(state)

    onMounted(() => {
        if(isMobile()){
          state.mobile = true;
        }
        onOrderPage();
    })

    const onOrderPage = () =>{
        state.loading = true;
        orderPage({}).then(res=>{
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