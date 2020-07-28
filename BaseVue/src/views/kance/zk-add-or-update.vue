<template>
  <div></div>
  <!-- <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="${column.comments}" prop="prjId">
      <el-input v-model="dataForm.prjId" placeholder="${column.comments}"></el-input>
    </el-form-item>
    <el-form-item label="${column.comments}" prop="zkName">
      <el-input v-model="dataForm.zkName" placeholder="${column.comments}"></el-input>
    </el-form-item>
    <el-form-item label="${column.comments}" prop="zkPos">
      <el-input v-model="dataForm.zkPos" placeholder="${column.comments}"></el-input>
    </el-form-item>
    <el-form-item label="${column.comments}" prop="zkElv">
      <el-input v-model="dataForm.zkElv" placeholder="${column.comments}"></el-input>
    </el-form-item>
    <el-form-item label="${column.comments}" prop="zkDep">
      <el-input v-model="dataForm.zkDep" placeholder="${column.comments}"></el-input>
    </el-form-item>
    <el-form-item label="${column.comments}" prop="zkNum">
      <el-input v-model="dataForm.zkNum" placeholder="${column.comments}"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog> -->
</template>

<script>
  // export default {
  //   data () {
  //     return {
  //       visible: false,
  //       dataForm: {
  //         zkId: 0,
  //         prjId: '',
  //         zkName: '',
  //         zkPos: '',
  //         zkElv: '',
  //         zkDep: '',
  //         zkNum: ''
  //       },
  //       dataRule: {
  //         prjId: [
  //           { required: true, message: '${column.comments}不能为空', trigger: 'blur' }
  //         ],
  //         zkName: [
  //           { required: true, message: '${column.comments}不能为空', trigger: 'blur' }
  //         ],
  //         zkPos: [
  //           { required: true, message: '${column.comments}不能为空', trigger: 'blur' }
  //         ],
  //         zkElv: [
  //           { required: true, message: '${column.comments}不能为空', trigger: 'blur' }
  //         ],
  //         zkDep: [
  //           { required: true, message: '${column.comments}不能为空', trigger: 'blur' }
  //         ],
  //         zkNum: [
  //           { required: true, message: '${column.comments}不能为空', trigger: 'blur' }
  //         ]
  //       }
  //     }
  //   },
  //   methods: {
  //     init (id) {
  //       this.dataForm.zkId = id || 0
  //       this.visible = true
  //       this.$nextTick(() => {
  //         this.$refs['dataForm'].resetFields()
  //         if (this.dataForm.zkId) {
  //           this.$http({
  //             url: this.$http.adornUrl(`/zk/zk/info/${this.dataForm.zkId}`),
  //             method: 'get',
  //             params: this.$http.adornParams()
  //           }).then(({data}) => {
  //             if (data && data.code === 0) {
  //               this.dataForm.prjId = data.zk.prjId
  //               this.dataForm.zkName = data.zk.zkName
  //               this.dataForm.zkPos = data.zk.zkPos
  //               this.dataForm.zkElv = data.zk.zkElv
  //               this.dataForm.zkDep = data.zk.zkDep
  //               this.dataForm.zkNum = data.zk.zkNum
  //             }
  //           })
  //         }
  //       })
  //     },
  //     // 表单提交
  //     dataFormSubmit () {
  //       this.$refs['dataForm'].validate((valid) => {
  //         if (valid) {
  //           this.$http({
  //             url: this.$http.adornUrl(`/zk/zk/${!this.dataForm.zkId ? 'save' : 'update'}`),
  //             method: 'post',
  //             data: this.$http.adornData({
  //               'zkId': this.dataForm.zkId || undefined,
  //               'prjId': this.dataForm.prjId,
  //               'zkName': this.dataForm.zkName,
  //               'zkPos': this.dataForm.zkPos,
  //               'zkElv': this.dataForm.zkElv,
  //               'zkDep': this.dataForm.zkDep,
  //               'zkNum': this.dataForm.zkNum
  //             })
  //           }).then(({data}) => {
  //             if (data && data.code === 0) {
  //               this.$message({
  //                 message: '操作成功',
  //                 type: 'success',
  //                 duration: 1500,
  //                 onClose: () => {
  //                   this.visible = false
  //                   this.$emit('refreshDataList')
  //                 }
  //               })
  //             } else {
  //               this.$message.error(data.msg)
  //             }
  //           })
  //         }
  //       })
  //     }
  //   }
  // }
</script>
