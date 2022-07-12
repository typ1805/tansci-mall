<template>
  <div class="payment-code">
    <el-card>
      <Table :data="tableData" :column="tableTitle" :operation="{show:true, width: mobile?80:160,}" :page="page" :loading="loading"
        @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange">
        <template #search>
          <div><el-button type="primary" @click="onAdd">添加收款码</el-button></div>
        </template>
        <template #column="scope">
          <el-button @click="onEdit(scope)" type="text" style="color:var(--edit)">编辑</el-button>
          <el-button @click="onDelete(scope)" type="text" style="color:var(--delete)">删除</el-button>
        </template>
      </Table>
      <el-dialog :title="title" v-model="addVisible" :show-close="false" :width="mobile? '100%':'50%'">
        <el-form :model="addForm" :rules="rules" ref="addRuleForm" status-icon label-width="100px">
          <el-form-item prop="name" label="名称" :rules="[{required: true, message: '请输入名称', trigger: 'blur'}]">
            <el-input v-model="addForm.name" placeholder="请输入名称"></el-input>
          </el-form-item>
          <el-form-item prop="type" label="类型" :rules="[{required: true, message: '请选择类型', trigger: 'change'}]">
            <el-radio-group v-model="addForm.type">
              <el-radio :label="1">微信</el-radio>
              <el-radio :label="2">支付宝</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item prop="qrcode" label="二维码" :rules="[{required: true, message: '请输入二维码', trigger: 'blur'}]">
            <el-input v-model="addForm.qrcode" placeholder="请输入二维码" type="textarea" :rows="6"></el-input>
            <span>请将二维码图片转换成Bsea64码：<el-link type="success" href="http://www.jsons.cn/img2base64" target="_blank">转换地址</el-link></span>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
          <el-button @click="addVisible = false">取消</el-button>
          <el-button type="primary" @click="onSubmit">提交</el-button>
          </span>
        </template>
      </el-dialog>
    </el-card>
  </div>
</template>
<script setup>
    import {onMounted, reactive, ref, unref, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from 'element-plus'
    import Table from '@/components/common/Table.vue'
    import {paymentCodePage, addPaymentCode, updatePaymentCode, delPaymentCode} from '@/api/systemApi'
    import {isMobile} from '@/utils/utils'

    const addRuleForm = ref(null)
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
          {prop:'name',label:'名称'},
          {prop:'type',alias:'typeName',label:'类型'},
          {prop:'qrcode',label:'收款码'},
          {prop:'userName',label:'商户'},
          {prop:'updateTime',label:'更新时间'},
          {prop:'createTime',label:'创建时间'},
        ],
        tableData:[],
        addVisible: false,
        title: '添加收款码',
        operate: 0,
        addForm:{
            id:'',
            name:'',
            type:'',
            qrcode:'',
        },
    })

    const {
        mobile,loading,page,tableHeight,tableTitle,tableData,
        addVisible,title,operate,addForm
    } = toRefs(state)

    onMounted(() => {
        if(isMobile()){
          state.mobile = true;
        }
        onPaymentCodePage();
    })

    // 初始化数据
    const onPaymentCodePage = () =>{
      state.loading = true;
      paymentCodePage({}).then(res=>{
        state.loading = false;
        state.tableData = res.result.records;
        state.page.current = res.result.current;
        state.page.size = res.result.size;
        state.page.total = res.result.total;
      })
    }
    const onSizeChange = (e) =>{
      state.page.size = e;
      onPaymentCodePage();
    }
    const onCurrentChange = (e) =>{
      state.page.current = e;
      onPaymentCodePage();
    }

    // 编辑
    const onEdit = (val) =>{
      state.title = "修改收款码";
      state.operate = 1;
      state.addForm = {
        id: val.column.row.id,
        name: val.column.row.name,
        type: Number(val.column.row.type),
        qrcode: val.column.row.qrcode,
      }
      state.addVisible = true;
    }

    // 删除
    const onDelete = (val) =>{
      ElMessageBox.confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let param = {
          id: val.column.row.id
        }
        delPaymentCode(param).then(res=>{
          if(res){
            ElMessage.success('删除成功!');
            onPaymentCodePage();
          }
        })
      }).catch(e=>{
        console.log(e)
      })
    }

    // 添加用户信息
    const onAdd = () =>{
      state.title = "添加收款码";
      state.operate = 0;
      state.addForm = {
          id:'',
          name:'',
          type:'',
          qrcode:'',
      }
      state.addVisible = true;
    }

    const onSubmit = async () =>{
      const form = unref(addRuleForm);
      if (!form) return;
      await form.validate();

      if(state.operate == 0){
        addPaymentCode(state.addForm).then(res=>{
          if(res){
            ElMessage.success({
              message: '添加成功！',
              type: 'success'
            });
            onPaymentCodePage();
            state.addVisible = false;
          }
        });
      } else {
        updatePaymentCode(state.addForm).then(res=>{
          if(res){
            ElMessage.success({
              message: '修改成功！',
              type: 'success'
            });
            onPaymentCodePage();
            state.addVisible = false;
          }
        });
      }
    }
</script>
<style lang="scss" scoped>
</style>