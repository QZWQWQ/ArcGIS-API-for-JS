<template>
  <el-dialog class="bdAU"
             :title="!bdDataForm.bdId ? '新增建筑物' : '修改建筑物'"
             :close-on-click-modal="false"
             :visible.sync="visible"
             top="4%">
    <el-form :model="bdDataForm"
             :rules="bdDataRule"
             ref="bdDataForm"
             @keyup.enter.native="bdDataFormSubmit()"
             label-width="95px">
      <el-form-item label="建筑物名称"
                    prop="bdName">
        <el-input v-model="bdDataForm.bdName"
                  placeholder="请录入建筑物名称"></el-input>
      </el-form-item>
      <el-form-item label="建筑物类型"
                    prop="landform">
        <el-radio-group v-model="bdDataForm.bdType">
          <el-radio-button label="泵站"></el-radio-button>
          <el-radio-button label="涵洞"></el-radio-button>
          <el-radio-button label="水闸"></el-radio-button>
          <el-radio-button label="桥梁"></el-radio-button>
          <el-radio-button label="滚水坝"></el-radio-button>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <span slot="footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary"
                 @click="bdDataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  data () {
    return {
      visible: false,
      bdDataForm: {
        bdId: 0,
        bdNum: '',
        bdName: '',
        bdPos: '',
        bdType: '',
        prjId: this.$route.params.prjId,
        prjName: this.$route.params.prjName
      },
      bdDataRule: {
        bdName: [
          { required: true, message: '建筑物名称不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    // 新增建筑物
    add (geometry) {
      this.visible = true
      this.bdDataForm = {}
      var list = []
      geometry.rings[0].forEach(item => {
        list += ('[' + item.toString() + '],')
      })
      this.bdDataForm.bdPos = '[[' + list.slice(0, list.length - 1) + ']]'
    },
    // 修改建筑物
    init (bdId) {
      console.log('this.bdDataForm 的内容为', this.bdDataForm)
      this.bdDataForm.bdId = bdId || 0
      // 获取()
      this.$http({
        url: this.$http.adornUrl(`/bd/bd/infoByPrjId/${this.bdDataForm.prjId}`),
        method: 'get',
        params: this.$http.adornParams()
      }).then(({ data }) => {
        this.visible = true
        this.$nextTick(() => {
          this.$refs['bdDataForm'].resetFields()
        })
        if (this.bdDataForm.bdId) {
          this.$http({
            url: this.$http.adornUrl(`/bd/bd/info/${this.bdDataForm.bdId}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.bdDataForm = data.bd
            }
          })
        }
      })
    },
    // 表单提交
    bdDataFormSubmit () {
      this.$refs['bdDataForm'].validate((valid) => {
        if (valid) {
          console.log(this.bdDataForm.bdId)
          this.$http({
            url: this.$http.adornUrl(`/bd/bd/${!this.bdDataForm.bdId ? 'save' : 'update'}`),
            method: 'post',
            data: this.$http.adornData({
              'bdId': this.bdDataForm.bdId || undefined,
              'bdNum': this.bdDataForm.bdNum,
              'bdName': this.bdDataForm.bdName,
              'bdPos': this.bdDataForm.bdPos,
              'bdType': this.bdDataForm.bdType,
              'prjId': this.$cookie.get('prjId'),
              'prjName': this.$cookie.get('prjName')
            })
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 500,
                onClose: () => {
                  this.visible = false
                  this.$emit('refreshBdDataList')
                  this.$parent.showBd()
                  // this.$parent.markBd()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        }
      })
    }
  }
}
</script>

<style lang="scss">
.bdAU .el-dialog {
  top: 10%;
  width: 535px;
  font-weight: bold;
  border-radius: 15px;
}
.bdAU .el-dialog__body {
  padding: 30px 60px 0 20px;
}
.bdAU .el-dialog__footer {
  padding: 0 35px 25px;
}
</style>