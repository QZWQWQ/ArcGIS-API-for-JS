<template>
  <div style="">

    <el-button type="warning"
               round
               @click="dialogFormVisible = true">新增项目</el-button>
    <el-button type="success"
               round
               @click="selectSpace()">地图查询</el-button>
    <el-input placeholder="请输入项目名称或项目编号......"
              prefix-icon="el-icon-search"
              style="width: 350px;margin-left: 10px"
              clearable
              v-model="txtvalue"
              @keyup.enter.native="selectField()"></el-input>

    <el-table :data="tableData"
              stripe
              style="margin:20px 0px 0px 0px;width:99%;border:1px solid #CCCCCC">
      <el-table-column prop="prjName"
                       label="项目名称"
                       width="140">
      </el-table-column>
      <el-table-column prop="prjNum"
                       label="项目编号"
                       width="100">
      </el-table-column>
      <el-table-column prop="prjTime"
                       label="时间"
                       width="150">
      </el-table-column>
      <el-table-column prop="landform"
                       label="地貌"
                       width="140">
      </el-table-column>
      <el-table-column prop="landOverview"
                       label="地层概述"
                       width="140">
      </el-table-column>
      <el-table-column prop="zkQty"
                       label="钻孔数量"
                       width="80">
      </el-table-column>
      <el-table-column prop="zkStart"
                       label="钻孔起点"
                       width="150">
      </el-table-column>
      <el-table-column prop="zkEnd"
                       label="钻孔终点"
                       width="150">
      </el-table-column>
      <el-table-column prop="river"
                       label="河流"
                       width="150">
      </el-table-column>
      <el-table-column prop="area"
                       label="所在地（区、街道、村）"
                       width="220">
      </el-table-column>
      <el-table-column label="操作"
                       width="150">
        <template slot-scope="scope">
          <el-button size="mini"
                     @click="tableUpdate(scope.row);updateDialogFormVisible=true;">编辑</el-button>
          <el-button size="mini"
                     type="danger"
                     @click="tableDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="勘测数据录入"
               :visible.sync="dialogFormVisible"
               style="top:-7vh">
      <el-form :model="form">
        <el-form-item label="项目名称"
                      :label-width="formLabelWidth">
          <el-input v-model="form.prjName"
                    autocomplete="off"
                    style="width:40%"
                    clearable></el-input>
        </el-form-item>

        <el-form-item label="项目时间"
                      :label-width="formLabelWidth">
          <el-date-picker v-model="form.prjTime"
                          type="date"
                          placeholder="请输入项目时间"></el-date-picker>
        </el-form-item>

        <el-form-item label="地貌"
                      :label-width="formLabelWidth">
          <el-radio-group v-model="form.landform">
            <el-radio label="地貌1"></el-radio>
            <el-radio label="地貌2"></el-radio>
            <el-radio label="地貌3"></el-radio>
            <el-radio label="地貌4"></el-radio>
            <el-radio label="地貌5"></el-radio>
            <el-radio label="地貌6"></el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="地层概述"
                      :label-width="formLabelWidth">
          <el-input v-model="form.landOverview"
                    autocomplete="off"
                    type="textarea"
                    style="width:95%"></el-input>
        </el-form-item>

        <el-form-item label="钻孔数量"
                      :label-width="formLabelWidth">
          <el-input v-model="form.zkQty"
                    autocomplete="off"
                    style="width:20%"
                    clearable></el-input>
        </el-form-item>

        <el-form-item label="钻孔位置"
                      :label-width="formLabelWidth">
          <!-- 起始位置 -->
          <el-input v-model="form.zkStart"
                    autocomplete="off"
                    style="width:40.2%"
                    placeholder="起点位置"></el-input>&nbsp;&nbsp;----&nbsp;
          <!-- 终点位置 -->
          <el-input v-model="form.zkEnd"
                    autocomplete="off"
                    style="width:50.3%"
                    placeholder="终点位置"></el-input>
        </el-form-item>

        <el-form-item label="项目编号"
                      :label-width="formLabelWidth">
          <el-input v-model="form.prjNum"
                    autocomplete="off"
                    style="width:20%"
                    clearable></el-input>
        </el-form-item>

        <el-form-item label="所属河道"
                      :label-width="formLabelWidth">
          <el-input v-model="form.river"
                    type="textarea"
                    autocomplete="off"
                    style="width:95%"></el-input>
        </el-form-item>

        <el-form-item label="所在地"
                      :label-width="formLabelWidth">
          <el-input v-model="form.area"
                    type="textarea"
                    autocomplete="off"
                    style="width:95%"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取&nbsp;&nbsp;消</el-button>
        <el-button type="primary"
                   @click="onSubmit()">创&nbsp;&nbsp;建</el-button>
      </div>
    </el-dialog>

    <el-dialog title="勘测数据修改"
               :visible.sync="updateDialogFormVisible"
               style="top:-7vh">
      <el-form :model="form">
        <el-form-item label="项目名称"
                      :label-width="formLabelWidth">
          <el-input v-model="form.prjName"
                    autocomplete="off"
                    style="width:40%"
                    clearable></el-input>
        </el-form-item>

        <el-form-item label="项目时间"
                      :label-width="formLabelWidth">
          <el-date-picker v-model="form.prjTime"
                          type="datetime"
                          placeholder="请输入项目时间"
                          value-format="yyyy-MM-dd"></el-date-picker>
        </el-form-item>

        <el-form-item label="地貌"
                      :label-width="formLabelWidth">
          <el-radio-group v-model="form.landform">
            <el-radio label="地貌1"></el-radio>
            <el-radio label="地貌2"></el-radio>
            <el-radio label="地貌3"></el-radio>
            <el-radio label="地貌4"></el-radio>
            <el-radio label="地貌5"></el-radio>
            <el-radio label="地貌6"></el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="地层概述"
                      :label-width="formLabelWidth">
          <el-input v-model="form.landOverview"
                    autocomplete="off"
                    type="textarea"
                    style="width:95%"></el-input>
        </el-form-item>

        <el-form-item label="钻孔数量"
                      :label-width="formLabelWidth">
          <el-input v-model="form.zkQty"
                    autocomplete="off"
                    style="width:20%"
                    clearable></el-input>
        </el-form-item>

        <el-form-item label="钻孔位置"
                      :label-width="formLabelWidth">
          <!-- 起始位置 -->
          <el-input v-model="form.zkStart"
                    autocomplete="off"
                    style="width:40.2%"
                    placeholder="起点位置"></el-input>&nbsp;&nbsp;----&nbsp;
          <!-- 终点位置 -->
          <el-input v-model="form.zkEnd"
                    autocomplete="off"
                    style="width:50.3%"
                    placeholder="终点位置"></el-input>
        </el-form-item>

        <el-form-item label="项目编号"
                      :label-width="formLabelWidth">
          <el-input v-model="form.prjNum"
                    autocomplete="off"
                    style="width:20%"
                    clearable></el-input>
        </el-form-item>

        <el-form-item label="所属河道"
                      :label-width="formLabelWidth">
          <el-input v-model="form.river"
                    type="textarea"
                    autocomplete="off"
                    style="width:95%"></el-input>
        </el-form-item>

        <el-form-item label="所在地"
                      :label-width="formLabelWidth">
          <el-input v-model="form.area"
                    type="textarea"
                    autocomplete="off"
                    style="width:95%"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button @click="dialogFormVisible=false">取&nbsp;&nbsp;消</el-button>
        <el-button type="primary"
                   @click="dialogFormVisible=false;updateForm()">修&nbsp;&nbsp;改</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  data () {
    return {
      tableData: [],
      dialogTableVisible: false,
      dialogFormVisible: false,
      updateDialogFormVisible: false,
      form: {
        Id: '',
        prjName: '',
        prjTime: '',
        landform: '',
        landOverview: '',
        zkQty: '',
        zkStart: '',
        zkEnd: '',
        prjNum: '',
        river: '',
        area: ''
      },
      formLabelWidth: '120px',
      txtvalue: ''
    }
  },
  components: {
  },
  computed: {
  },
  created () {

  },
  mounted () {
    this.getKance()
  },
  methods: {
    getKance () {
      let that = this
      this.$http({
        url: this.$http.adornUrl('/kance/kance/list'),
        method: 'get',
        params: this.$http.adornParams({
          shuju: ''
        })
      }).then(({ data }) => {
        that.tableData = data.Kanceshuju
      }).catch((e) => {

      })
    },
    selectField () {
      console.log('你好')
    },
    selectSpace () { },
    onSubmit () {
      this.dialogFormVisible = false
      let that = this
      let formData = that.form

      // JSON.stringify(formData)
      that.$http({
        url: that.$http.adornUrl('/kance/kance/add'),
        method: 'post',
        data: that.$http.adornData(
          JSON.stringify(formData)
        )
      }).then((res) => {
        that.$message.success('添加成功')
        that.tableData = res.formData.Kanceshuju
      }).catch((e) => {
        that.$message.error('添加失败')
      })
    },
    tableDelete (row) {
      this.$http({
        url: this.$http.adornUrl('/kance/kance/delete'),
        method: 'get',
        params: this.$http.adornParams({
          id: row.id
        })
      }).then((res) => {
        this.$message.success('删除成功')
        this.tableData = res.data.Kanceshuju
      }).catch((e) => {
        this.$message.error('删除失败')
      })
    },
    tableUpdate (row) {
      this.form = row
    },
    updateForm () {
      console.log(this.form)
      // debugger
      this.$http({
        url: this.$http.adornUrl('/kance/kance/update'),
        method: 'post',
        data: this.$http.adornParams(
          this.form
        )
      }).then((res) => {
        this.$message.success('修改成功')
        this.updateDialogFormVisible = false
      }).catch((e) => {
        this.$message.error('修改失败')
      })
    }
  }
}
</script>