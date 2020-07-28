<template >
  <el-dialog class="kanceAU"
             :title="!dataForm.id ? '新建项目' : '修改项目'"
             :close-on-click-modal="false"
             :visible.sync="visible"
             top="2%"
             style="font-weight:bold">
    <el-form :model="dataForm"
             :rules="dataRule"
             ref="dataForm"
             @keyup.enter.native="dataFormSubmit()"
             label-width="95px">
      <el-form-item label="工程名称"
                    prop="prjName">
        <el-input v-model="dataForm.prjName"
                  placeholder="请输入工程名称"></el-input>
      </el-form-item>
      <el-form-item label="勘查编号"
                    prop="prjNum">
        <el-input v-model="dataForm.prjNum"
                  placeholder="请输入勘察编号"></el-input>
      </el-form-item>
      <el-form-item label="合同编号"
                    prop="contractNum">
        <el-input v-model="dataForm.contractNum"
                  placeholder="请输入合同编号"></el-input>
      </el-form-item>
      <el-form-item label="时间"
                    prop="prjTime">
        <el-date-picker v-model="dataForm.prjTime"
                        type="datetime"
                        placeholder="请输入项目时间"
                        value-format="yyyy-MM-dd hh:mm:ss"></el-date-picker>
      </el-form-item>
      <el-form-item label="项目阶段"
                    prop="prjStage">
        <el-radio-group v-model="dataForm.prjStage">
          <el-radio label="建成"></el-radio>
          <el-radio label="初设"></el-radio>
          <el-radio label="方案"></el-radio>
          <el-radio label="可研"></el-radio>
          <el-radio label="施工图"></el-radio>
          <el-radio label="其他"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="工程起点"
                    prop="prjStart">
        <el-input v-model="dataForm.prjStart"
                  placeholder="请输入工程起点"></el-input>
      </el-form-item>
      <el-form-item label="工程终点"
                    prop="prjEnd">
        <el-input v-model="dataForm.prjEnd"
                  placeholder="请输入工程终点"></el-input>
      </el-form-item>
      <el-form-item label="附属建筑物"
                    prop="buildingName">
        <el-input v-model="dataForm.buildingName"
                  placeholder="请输入附属建筑物名称"></el-input>
      </el-form-item>
      <el-form-item label="建筑物位置"
                    prop="buildingPos">
        <el-input v-model="dataForm.buildingPos"
                  placeholder="请输入附属建筑物位置"></el-input>
      </el-form-item>
      <el-form-item label="钻孔数量"
                    prop="zkQty">
        <el-input v-model="dataForm.zkQty"
                  type="number"
                  placeholder="请输入钻孔数量"></el-input>
      </el-form-item>
      <el-form-item label="钻孔位置"
                    prop="zkPos">
        <el-input v-model="dataForm.zkPos"
                  placeholder="请输入钻孔位置"></el-input>
      </el-form-item>
      <el-form-item label="河道名称"
                    prop="river">
        <el-input v-model="dataForm.river"
                  placeholder="请输入项目所属河道"></el-input>
      </el-form-item>
      <el-form-item label="所在地"
                    prop="area">
        <el-input v-model="dataForm.area"
                  placeholder="请输入所在地（区，街道，村）"></el-input>
      </el-form-item>
      <el-form-item label="岩土特征"
                    prop="landOverview">
        <el-input v-model="dataForm.landOverview"
                  placeholder="请描述岩土特征"></el-input>
      </el-form-item>
      <el-form-item label="地貌"
                    prop="landform">
        <el-radio-group v-model="dataForm.landform">
          <el-radio label="冲击"></el-radio>
          <el-radio label="岗地"></el-radio>
          <el-radio label="丘陵"></el-radio>
          <el-radio label="山脉"></el-radio>
          <el-radio label="其他"></el-radio>
        </el-radio-group>
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
      roleList: [],
      dataForm: {
        id: 0,
        prjName: '',
        prjNum: '',
        prjTime: '',
        prjStart: '',
        prjEnd: '',
        buildingName: '',
        buildingPos: '',
        zkQty: '',
        zkPos: '',
        landform: '',
        landOverview: '',
        river: '',
        area: '',
        prjStage: '',
        contractNum: ''
      },
      dataRule: {
        prjName: [
          { required: true, message: '项目名称不能为空', trigger: 'blur' }
        ],
        prjNum: [
          { required: true, message: '勘察编号不能为空', trigger: 'blur' }
        ],
        contractNum: [
          { required: true, message: '合同编号不能为空', trigger: 'blur' }
        ]
        // prjTime: [
        //   { required: true, message: '项目时间不能为空', trigger: 'blur' }
        // ]
        // landform: [
        //   { required: true, message: '地貌不能为空', trigger: 'blur' }
        // ],
        // landOverview: [
        //   { required: true, message: '地层概述不能为空', trigger: 'blur' }
        // ],
        // zkQty: [
        //   { required: true, message: '请输入钻孔数量', trigger: 'blur' }
        // ],
        // zkStart: [
        //   { required: true, message: '请输入钻孔起点', trigger: 'blur' }
        // ],
        // zkEnd: [
        //   { required: true, message: '请输入钻孔终点', trigger: 'blur' }
        // ],
        // river: [
        //   { required: true, message: '所属河道不能为空', trigger: 'blur' }
        // ],
        // area: [
        //   { required: true, message: '所在地区不能为空', trigger: 'blur' }
        // ]
      }
    }
  },
  methods: {
    init (id) {
      console.log('1234567890', this.dataForm)
      this.dataForm.id = id || 0
      // 获取()
      this.$http({
        url: this.$http.adornUrl('/kance/list'),
        method: 'get',
        params: this.$http.adornParams()
      }).then(({ data }) => {
        this.roleList = data && data.code === 0 ? data.list : []
      }).then(() => {
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
        })
      }).then(() => {
        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl(`/kance/info/${this.dataForm.id}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.dataForm.prjName = data.prj.prjName
              this.dataForm.prjNum = data.prj.prjNum
              this.dataForm.prjTime = data.prj.prjTime
              this.dataForm.prjStart = data.prj.prjStart
              this.dataForm.prjEnd = data.prj.prjEnd
              this.dataForm.buildingName = data.prj.buildingName
              this.dataForm.buildingPos = data.prj.buildingPos
              this.dataForm.zkQty = data.prj.zkQty
              this.dataForm.zkPos = data.prj.zkPos
              this.dataForm.river = data.prj.river
              this.dataForm.area = data.prj.area
              this.dataForm.landform = data.prj.landform
              this.dataForm.landOverview = data.prj.landOverview
              this.dataForm.prjStage = data.prj.prjStage
              this.dataForm.contractNum = data.prj.contractNum
            }
          })
        }
      })
    },
    // 表单提交
    dataFormSubmit () {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(`/kance/${!this.dataForm.id ? 'add' : 'update'}`),
            method: 'post',
            data: this.$http.adornData({
              'prjId': this.dataForm.id || undefined,
              'prjName': this.dataForm.prjName,
              'prjNum': this.dataForm.prjNum,
              'prjTime': this.dataForm.prjTime,
              'prjStart': this.dataForm.prjStart,
              'prjEnd': this.dataForm.prjEnd,
              'buildingName': this.dataForm.buildingName,
              'buildingPos': this.dataForm.buildingPos,
              'zkQty': this.dataForm.zkQty,
              'zkPos': this.dataForm.zkPos,
              'river': this.dataForm.river,
              'area': this.dataForm.area,
              'landform': this.dataForm.landform,
              'landOverview': this.dataForm.landOverview,
              'prjStage': this.dataForm.prjStage,
              'contractNum': this.dataForm.contractNum
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
.kanceAU .el-dialog__body {
  padding: 20px 20px 0;
}
.kanceAU .el-dialog__footer {
  padding: 0 35px 35px;
}
</style>