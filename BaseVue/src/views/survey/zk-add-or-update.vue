<template>
  <el-dialog class="zkAU"
             :title="!zkDataForm.zkId ? '新增钻孔项目' : '修改钻孔项目'"
             :close-on-click-modal="false"
             :visible.sync="visible"
             top="4%">
    <el-form :model="zkDataForm"
             :rules="zkDataRule"
             ref="zkDataForm"
             @keyup.enter.native="zkDataFormSubmit()"
             label-width="95px">
      <el-form-item label="钻孔编号"
                    prop="zkNum">
        <el-input v-model="zkDataForm.zkNum"
                  placeholder="请录入钻孔编号"></el-input>
      </el-form-item>
      <el-form-item label="钻孔位置"
                    prop="zkPos">
        <el-input v-model="zkDataForm.zkPos"
                  placeholder="请录入钻孔位置"
                  clearable></el-input>
      </el-form-item>
      <el-form-item label="钻孔高程"
                    prop="zkElv">
        <el-input v-model="zkDataForm.zkElv"
                  placeholder="请录入钻孔高程"></el-input>
      </el-form-item>
      <el-form-item label="钻孔深度"
                    prop="zkDep">
        <el-input v-model="zkDataForm.zkDep"
                  placeholder="请录入钻孔深度"></el-input>
      </el-form-item>
      <el-form-item label="地层描述"
                    prop="zkDsp">
        <el-input v-model="zkDataForm.zkDsp"
                  placeholder="请描述钻孔所在地层特征"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary"
                 @click="zkDataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  data () {
    return {
      visible: false,
      innerVisible: false,
      zkDataForm: {
        zkId: 0,
        zkNum: '',
        zkPos: '',
        zkElv: '',
        zkDep: '',
        zkDsp: ''
      },
      zkDataRule: {
        zkNum: [
          { required: true, message: '钻孔编号不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  props: ['flag'],
  methods: {
    // 新增钻孔
    add (geometry) {
      this.visible = true
      this.zkDataForm = {}
      this.zkDataForm.zkPos = geometry.x.toFixed(8) + ',' + geometry.y.toFixed(8)
    },
    // 修改钻孔
    init (zkId) {
      this.zkDataForm.zkId = zkId || 0
      // 获取()
      this.$http({
        url: this.$http.adornUrl(`/zk/zk/infoByPrjId/${this.zkDataForm.prjId}`),
        method: 'get',
        params: this.$http.adornParams()
      }).then(({ data }) => {
        this.visible = true
        this.$nextTick(() => {
          this.$refs['zkDataForm'].resetFields()
        })
        if (this.zkDataForm.zkId) {
          this.$http({
            url: this.$http.adornUrl(`/zk/zk/info/${this.zkDataForm.zkId}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.zkDataForm = data.zk
            }
          })
        }
      })
    },
    // 表单提交
    zkDataFormSubmit () {
      let that = this
      this.$refs['zkDataForm'].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(`/zk/zk/${!this.zkDataForm.zkId ? 'save' : 'update'}`),
            method: 'post',
            data: this.$http.adornData({
              'zkId': this.zkDataForm.zkId || undefined,
              'zkNum': this.zkDataForm.zkNum,
              'zkPos': this.zkDataForm.zkPos,
              'zkElv': this.zkDataForm.zkElv,
              'zkDep': this.zkDataForm.zkDep,
              'zkDsp': this.zkDataForm.zkDsp,
              'prjId': that.$cookie.get('prjId'),
              'prjName': that.$cookie.get('prjName')
            })
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 500,
                onClose: () => {
                  this.visible = false
                  this.$emit('refreshZkDataList')
                  this.$parent.showZk()
                  // this.$parent.markZk()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        }
      })
    },
    // excel表格导入
    saveExcel (zk) {
      this.$http({
        url: this.$http.adornUrl(`/zk/zk/saveExcel`),
        method: 'post',
        data: this.$http.adornData(zk, false)
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: '操作成功',
            type: 'success',
            duration: 500,
            onClose: () => {
              this.visible = false
              this.$emit('refreshZkDataList')
              this.$parent.showZk()
              // this.$parent.markZk()
            }
          })
        } else {
          this.$message.error(data.msg)
        }
      })
    }
  }
}
</script>

<style lang="scss">
.zkAU .el-dialog {
  width: 650px;
  font-weight: bold;
  border-radius: 15px;
}
.zkAU .el-dialog__body {
  padding: 30px 60px 0 20px;
}
.zkAU .el-dialog__footer {
  padding: 0 35px 25px;
}
.zkAU .el-dialog .mapContainer {
  height: 500px;
}
</style>