<template >
  <el-dialog class="surveyAU"
             :title="!dataForm.prjId ? '新建工程项目' : '编辑工程项目'"
             :close-on-click-modal="false"
             :visible.sync="visible"
             top="4%">
    <el-form :model="dataForm"
             :rules="dataRule"
             ref="dataForm"
             @keyup.enter.native="dataFormSubmit()"
             label-width="95px">
      <el-form-item label="工程名称"
                    prop="prjName">
        <el-input v-model="dataForm.prjName"
                  placeholder="请录入工程名称"></el-input>
      </el-form-item>
      <el-form-item label="勘测编号"
                    prop="surveyNum">
        <el-input v-model="dataForm.surveyNum"
                  placeholder="请录入勘察编号"></el-input>
      </el-form-item>
      <el-form-item label="合同编号"
                    prop="conNum">
        <el-input v-model="dataForm.conNum"
                  placeholder="请录入合同编号"></el-input>
      </el-form-item>
      <el-form-item label="时间"
                    prop="time">
        <el-date-picker v-model="dataForm.time"
                        type="datetime"
                        placeholder="请录入项目时间"
                        value-format="yyyy-MM-dd hh:mm:ss"></el-date-picker>
      </el-form-item>
      <el-form-item label="项目阶段"
                    prop="stage">
        <el-radio-group v-model="dataForm.stage">
          <el-radio-button label="初设"></el-radio-button>
          <el-radio-button label="建成"></el-radio-button>
          <el-radio-button label="方案"></el-radio-button>
          <el-radio-button label="可研"></el-radio-button>
          <el-radio-button label="施工图"></el-radio-button>
          <el-radio-button label="其他"></el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="河道名称"
                    prop="river">
        <el-input v-model="dataForm.river"
                  placeholder="请录入项目所属河道"></el-input>
      </el-form-item>
      <el-form-item label="地貌"
                    prop="landform">
        <el-radio-group v-model="dataForm.landform">
          <el-radio-button label="冲积"></el-radio-button>
          <el-radio-button label="岗地"></el-radio-button>
          <el-radio-button label="丘陵"></el-radio-button>
          <el-radio-button label="山脉"></el-radio-button>
          <el-radio-button label="其他"></el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="岩土特征"
                    prop="geoFeature">
        <el-input v-model="dataForm.geoFeature"
                  placeholder="请录入岩土特征"></el-input>
      </el-form-item>
      <el-form-item label="所在地"
                    prop="location">
        <el-input v-model="dataForm.location"
                  placeholder="街道，村"
                  class="input-with-select"
                  clearable>
          <el-select v-model="dataForm.select"
                     slot="prepend"
                     placeholder="请选择所在区域">
            <el-option label="鼓楼区"
                       value="鼓楼区"></el-option>
            <el-option label="玄武区"
                       value="玄武区"></el-option>
            <el-option label="建邺区"
                       value="建邺区"></el-option>
            <el-option label="秦淮区"
                       value="秦淮区"></el-option>
            <el-option label="雨花台区"
                       value="雨花台区"></el-option>
            <el-option label="栖霞区"
                       value="栖霞区"></el-option>
            <el-option label="六合区"
                       value="六合区"></el-option>
            <el-option label="浦口区"
                       value="浦口区"></el-option>
            <el-option label="江宁区"
                       value="江宁区"></el-option>
            <el-option label="溧水区"
                       value="溧水区"></el-option>
            <el-option label="高淳区"
                       value="高淳区"></el-option>
          </el-select>
        </el-input>
      </el-form-item>
    </el-form>
    <span slot="footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary"
                 @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  data () {
    return {
      visible: false,
      dataForm: {
        prjId: 0,
        prjName: '',
        surveyNum: '',
        conNum: '',
        time: '',
        stage: '',
        river: '',
        landform: '',
        geoFeature: '',
        location: '',
        select: ''
      },
      dataRule: {
        prjName: [
          { required: true, message: '项目名称不能为空', trigger: 'blur' }
        ],
        surveyNum: [
          { required: true, message: '勘测编号不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    init (prjId) {
      console.log('this.dataForm 的内容为', this.dataForm)
      this.dataForm.prjId = prjId || 0
      // 获取()
      this.$http({
        url: this.$http.adornUrl('/survey/list'),
        method: 'get',
        params: this.$http.adornParams()
      }).then(({ data }) => {
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
        })
        if (this.dataForm.prjId) {
          this.$http({
            url: this.$http.adornUrl(`/survey/info/${this.dataForm.prjId}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.dataForm = data.prj
            }
          })
        }
      })
    },
    // 表单提交
    dataFormSubmit () {
      console.log(this.dataForm)
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(`/survey/${!this.dataForm.prjId ? 'add' : 'update'}`),
            method: 'post',
            data: this.$http.adornData({
              'prjId': this.dataForm.prjId || undefined,
              'prjName': this.dataForm.prjName,
              'surveyNum': this.dataForm.surveyNum,
              'conNum': this.dataForm.conNum,
              'time': this.dataForm.time,
              'stage': this.dataForm.stage,
              'river': this.dataForm.river,
              'landform': this.dataForm.landform,
              'geoFeature': this.dataForm.geoFeature,
              'location': this.dataForm.select + this.dataForm.location
            })
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.visible = false
                  this.$emit('refreshDataList')
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
.surveyAU .el-dialog {
  width: 700px;
  font-weight: bold;
  border-radius: 15px;
}
.surveyAU .el-dialog__body {
  padding: 30px 60px 0 20px;
}
.surveyAU .el-dialog__footer {
  padding: 0 35px 25px;
}
.surveyAU .el-select .el-input {
  width: 130px;
}
.surveyAU.input-with-select .el-input-group__prepend {
  background-color: #fff;
}
</style>