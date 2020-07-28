<template>
  <div class="survey">
    <!-- 操作框 -->
    <el-form :inline="true"
             @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-tooltip class="item"
                    effect="dark"
                    content="当输入框为空时查询结果为全局"
                    placement="top-start">
          <el-input v-model="prjName"
                    prefix-icon="el-icon-search"
                    placeholder="请输入关键字"
                    clearable
                    @keyup.enter.native="getDataList()"></el-input>
        </el-tooltip>
      </el-form-item>
      <el-form-item>
        <el-button type="primary"
                   @click="getDataList()">查询</el-button>
        <el-button type="success"
                   @click="addOrUpdateHandle()">新建工程</el-button>
        <el-button type="danger"
                   @click="deleteHandle()"
                   :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>

    <!-- 表格内容 -->
    <el-table :data="dataList"
              border
              stripe
              v-loading="dataListLoading"
              @selection-change="selectionChangeHandle"
              style="width: 100%;font-size:16px"
              :header-cell-style="{background:'#F2F6FC',color:'#606266'}">
      <el-table-column type="selection"
                       header-align="center"
                       align="center"
                       width="50">
      </el-table-column>
      <el-table-column prop="prjName"
                       header-align="center"
                       align="center"
                       label="工程名称"></el-table-column>
      <el-table-column prop="surveyNum"
                       header-align="center"
                       align="center"
                       label="勘测编号"></el-table-column>
      <el-table-column prop="conNum"
                       header-align="center"
                       align="center"
                       label="合同编号"></el-table-column>
      <el-table-column prop="time"
                       header-align="center"
                       align="center"
                       label="时间"
                       width="100px"></el-table-column>
      <el-table-column prop="stage"
                       header-align="center"
                       align="center"
                       width="100px"
                       label="项目阶段"></el-table-column>
      <el-table-column header-align="center"
                       align="center"
                       label="附属建筑物 / 钻孔"
                       width="150px">
        <template slot-scope="scope">
          <el-tooltip class="item"
                      effect="dark"
                      content="跳转地图进行操作"
                      placement="top-start">
            <el-button size="mini"
                       style="color:#696969;font-weight:900"
                       @click="updateZkAndBd(scope.row.prjId,scope.row.prjName)">查看 / 编辑</el-button>
          </el-tooltip>
        </template></el-table-column>
      <el-table-column prop="landform"
                       header-align="center"
                       align="center"
                       width="80px"
                       label="地貌"></el-table-column>
      <el-table-column prop="geoFeature"
                       header-align="center"
                       align="center"
                       label="岩土特征"></el-table-column>
      <el-table-column prop="river"
                       header-align="center"
                       align="center"
                       label="河道"></el-table-column>
      <el-table-column prop="location"
                       header-align="center"
                       align="center"
                       width="200px"
                       label="所在地（区、街道、村）"></el-table-column>
      <el-table-column fixed="right"
                       header-align="center"
                       align="center"
                       label="操作"
                       width="150px">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="warning"
                     @click="addOrUpdateHandle(scope.row.prjId)">编辑</el-button>
          <el-button size="mini"
                     type="danger"
                     @click="deleteHandle(scope.row.prjId,scope.row.prjName)">删除</el-button>
        </template></el-table-column>
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
import AddOrUpdate from './survey-add-or-update'
export default {
  data () {
    return {
      prjName: '',
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
  created () {
    this.getDataList()
  },
  methods: {
    // 获取数据列表
    getDataList () {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/survey/list'),
        method: 'get',
        params: this.$http.adornParams({
          'page': this.pageIndex,
          'limit': this.pageSize,
          'prjName': this.prjName
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
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id)
      })
    },
    // 删除工程信息
    deleteHandle (id, name) {
      var prjIds = id ? [id] : this.dataListSelections.map(item => {
        return item.prjId
      })
      var prjNames = name ? [name] : this.dataListSelections.map(item => {
        return item.prjName
      })
      this.$confirm(`<div>确定要<strong style="color:#F56C6C"> ${id ? '删除' : '批量删除'} </strong>名称为：<strong style="color:#409EFF">${prjNames.join('  ,  ')} </strong>的工程项目吗？<br><strong style="color:#F56C6C">注意：</strong>该操作会删除该项目下<strong style="color:#F56C6C">所有</strong>钻孔项目和建筑物项目，请谨慎操作！</div>`, '提示', {
        dangerouslyUseHTMLString: true,
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/survey/delete'),
          method: 'post',
          data: this.$http.adornData(prjIds, false)
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: '删除成功！',
              type: 'success',
              duration: 500,
              onClose: () => {
                this.getDataList()
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
      }).catch(() => { })
    },
    // 更新钻孔和建筑物信息
    updateZkAndBd (prjId, prjName) {
      this.$router.push({ name: 'zkAndBd', params: { prjId, prjName } })
      this.$cookie.set('prjId', prjId)
      this.$cookie.set('prjName', prjName)
    }
  }
}
</script>

