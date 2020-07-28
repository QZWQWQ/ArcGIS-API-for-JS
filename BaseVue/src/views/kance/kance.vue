<template>
  <div class="">
    <el-form :inline="true"
             :model="dataForm"
             @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.prjName"
                  prefix-icon="el-icon-search"
                  placeholder="请输入关键字"
                  clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button type="success"
                   @click="getDataListOnMap()"
                   router>地图查询</el-button>
        <el-button type="primary"
                   @click="addOrUpdateHandle()">新增</el-button>
        <el-button type="danger"
                   @click="deleteHandle()"
                   :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="dataList"
              border
              v-loading="dataListLoading"
              @selection-change="selectionChangeHandle"
              style="width: 100%;"
              stripe
              :header-cell-style="{background:'#F2F6FC',color:'#606266'}">
      <el-table-column type="selection"
                       header-align="center"
                       align="center"
                       width="50">
      </el-table-column>
      <el-table-column prop="prjName"
                       header-align="center"
                       align="center"
                       label="工程名称">
      </el-table-column>
      <el-table-column prop="prjNum"
                       header-align="center"
                       align="center"
                       label="勘查编号">
      </el-table-column>
      <el-table-column prop="contractNum"
                       header-align="center"
                       align="center"
                       label="合同编号">
      </el-table-column>
      <el-table-column prop="prjTime"
                       header-align="center"
                       align="center"
                       label="时间">
      </el-table-column>
      <el-table-column prop="prjStage"
                       header-align="center"
                       align="center"
                       width="80px"
                       label="项目阶段">
      </el-table-column>
      <el-table-column prop="prjStart"
                       header-align="center"
                       align="center"
                       label="工程起点">
      </el-table-column>
      <el-table-column prop="prjEnd"
                       header-align="center"
                       align="center"
                       label="工程终点">
      </el-table-column>
      <el-table-column prop="buildingName"
                       header-align="center"
                       align="center"
                       label="附属建筑物">
      </el-table-column>
      <el-table-column prop="buildingPos"
                       header-align="center"
                       align="center"
                       label="建筑物位置">
      </el-table-column>
      <el-table-column prop="zkQty"
                       header-align="center"
                       align="center"
                       width="60px"
                       label="钻孔数量">
      </el-table-column>
      <el-table-column prop="zkPos"
                       header-align="center"
                       align="center"
                       label="钻孔位置">
      </el-table-column>
      <el-table-column prop="river"
                       header-align="center"
                       align="center"
                       label="河道名称">
      </el-table-column>
      <el-table-column prop="area"
                       header-align="center"
                       align="center"
                       width="200px"
                       label="所在地（区、街道、村）">
      </el-table-column>
      <el-table-column prop="landOverview"
                       header-align="center"
                       align="center"
                       label="岩土特征">
      </el-table-column>
      <el-table-column prop="landform"
                       header-align="center"
                       align="center"
                       width="65px"
                       label="地貌">
      </el-table-column>
      <el-table-column fixed="right"
                       header-align="center"
                       align="center"
                       label="操作"
                       width="88px">
        <template slot-scope="scope">
          <el-button type="text"
                     size="small"
                     @click="addOrUpdateHandle(scope.row.prjId)">修改</el-button>
          <el-button type="text"
                     size="small"
                     style="color:#FF5151"
                     @click="deleteHandle(scope.row.prjId,scope.row.prjName)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination @size-change="sizeChangeHandle"
                   @current-change="currentChangeHandle"
                   :current-page="pageIndex"
                   :page-sizes="[10, 20, 50, 100]"
                   :page-size="pageSize"
                   :total="totalPage"
                   layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>

    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible"
                   ref="addOrUpdate"
                   @refreshDataList="getDataList">
    </add-or-update>
  </div>
</template>

<script>
import AddOrUpdate from './kance-add-or-update'
export default {
  data () {
    return {
      dataForm: {
        // content: '',
        prjName: ''
        // area: '',
        // buildingName: ''
        // Id: '',
        // prjName: '',
        // prjTime: '',
        // landform: '',
        // landOverview: '',
        // zkQty: '',
        // zkStart: '',
        // zkEnd: '',
        // prjNum: '',
        // river: '',
        // area: ''
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false
    }
  },
  components: {
    AddOrUpdate
  },
  activated () {
    this.getDataList()
  },
  methods: {
    // 获取数据列表
    getDataList () {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/kance/list'),
        method: 'get',
        params: this.$http.adornParams({
          'page': this.pageIndex,
          'limit': this.pageSize,
          'prjName': this.dataForm.prjName
        })
      }).then(({ data }) => {
        if (data && data.code === 0) {
          // 该页只显示指定的条目
          this.dataList = data.page.list
          // 总页数
          this.totalPage = data.page.totalCount
        } else {
          this.dataList = []
          this.totalPage = 0
        }
        this.dataListLoading = false
      })
    },
    getDataListOnMap () {
      this.$router.push('map')
    },
    // 每页数
    sizeChangeHandle (val) {
      this.pageSize = val
      this.pageIndex = 1
      this.getDataList()
    },
    // 当前页
    currentChangeHandle (val) {
      this.pageIndex = val
      this.getDataList()
    },
    // 多选
    selectionChangeHandle (val) {
      this.dataListSelections = val
    },
    // 新增 / 修改
    addOrUpdateHandle (id) {
      this.addOrUpdateVisible = true
      console.log('这是我获取到的信息' + this.addOrUpdateVisible)
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id)
      })
    },
    // 删除
    deleteHandle (id, name) {
      var prjIds = id ? [id] : this.dataListSelections.map(item => {
        return item.prjId
      })
      var prjNames = name ? [name] : this.dataListSelections.map(item => {
        return item.prjName
      })
      this.$confirm(`<div>确定要对项目名为：（ <strong style="color:#409EFF">${prjNames.join('  ,  ')} </strong>） 进行（ <strong style="color:#F56C6C">${id ? '删除' : '批量删除'}</strong> ）操作吗？</div>`, '提示', {
        dangerouslyUseHTMLString: true,
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/kance/delete'),
          method: 'post',
          data: this.$http.adornData(prjIds, false)
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: '删除成功！',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.getDataList()
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
      }).catch(() => { })
    }
  }
}
</script>
