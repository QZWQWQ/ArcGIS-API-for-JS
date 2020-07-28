<template>
  <div id="map"
       class="global">

    <!-- 地图界面 -->
    <div id="mapContainer"
         class="mapview"
         @refreshMap="refreshMap()">
      <div class="wenzi">
        <span style="font-size:18px;color:#303133;font-weight:bold;">当前坐标点如下：</span>
      </div>
      <el-input v-model="position"
                class="position"
                readonly="readonly"></el-input>
    </div>

    <!-- 钻孔/建筑物信息框 -->
    <div class="showCardContainer">
      <el-card class="show-card">
        <div class="show-card-header">
          <!-- <span style="font-size:18px;color:#303133;font-weight:bold">钻孔 / 建筑物</span> -->
          <el-button @click="toogleShow()"
                     style="float:right;padding:0;font-size:17px;color:#909399;font-weight:bold;line-height:0"
                     type="text">{{toogleShowBtn}}</el-button>
        </div>
        <div class="show-card-content"
             v-if="showStatus">
          <el-divider class="show-card-divider">钻孔信息</el-divider>
          <el-table :data="zkDataList"
                    border
                    stripe
                    v-loading="dataListLoading"
                    style="width:100%;font-size:10px"
                    height="310"
                    :cell-style="{padding:0 +'px'}"
                    :header-cell-style="{background:'#FDF5E6',color:'#606266'}"
                    @row-click="markZk">
            <el-table-column prop="prjName"
                             header-align="center"
                             align="center"
                             label="工程名称"
                             width="120px"></el-table-column>
            <el-table-column prop="zkNum"
                             header-align="center"
                             align="center"
                             label="钻孔编号"></el-table-column>
            <el-table-column prop="zkElv"
                             header-align="center"
                             align="center"
                             label="高程"></el-table-column>
            <el-table-column prop="zkDep"
                             header-align="center"
                             align="center"
                             label="深度"></el-table-column>
            <el-table-column prop="zkDsp"
                             header-align="center"
                             align="center"
                             label="地层描述"></el-table-column>
            <el-table-column fixed="right"
                             header-align="center"
                             align="center"
                             label="操作"
                             width="90px">
              <template slot-scope="scope">
                <el-button size="mini"
                           type="text"
                           style="color:#FF8C00"
                           @click="updateZk(scope.row.zkId)">编辑</el-button>
                <el-button size="mini"
                           type="text"
                           style="color:#FF4500"
                           @click="deleteZk(scope.row.zkId,scope.row.zkNum)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="show-card-content"
             v-if="showStatus">
          <el-divider class="show-card-divider">建筑物信息</el-divider>
          <el-table :data="bdDataList"
                    border
                    stripe
                    v-loading="dataListLoading"
                    style="width: 100%;font-size:10px"
                    height="180"
                    :cell-style="{padding:0 +'px'}"
                    :header-cell-style="{background:'#FDF5E6',color:'#606266'}"
                    @row-click="markBd">
            <el-table-column prop="prjName"
                             header-align="center"
                             align="center"
                             label="工程名称"></el-table-column>
            <el-table-column prop="bdName"
                             header-align="center"
                             align="center"
                             label="建筑物名称"></el-table-column>
            <el-table-column prop="bdType"
                             header-align="center"
                             align="center"
                             label="类型"></el-table-column>
            <el-table-column fixed="right"
                             header-align="center"
                             align="center"
                             label="操作"
                             width="90px">
              <template slot-scope="scope">
                <el-button size="mini"
                           type="text"
                           style="color:#FF8C00"
                           @click="updateBd(scope.row.bdId)">编辑</el-button>
                <el-button size="mini"
                           type="text"
                           style="color:#FF4500"
                           @click="deleteBd(scope.row.bdId,scope.row.bdNum)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="show-card-content"
             v-if="showStatus">
          <el-divider class="show-card-divider"></el-divider>
          <el-tooltip class="item"
                      effect="dark"
                      content="点击地图以添加钻孔"
                      placement="top-start">
            <el-button type="primary"
                       @click="addZk()"
                       class="btn">新增钻孔</el-button>
          </el-tooltip>
          <el-tooltip class="item"
                      effect="dark"
                      content="点击地图以添加建筑物"
                      placement="top-start">
            <el-button type="primary"
                       @click="addBd()"
                       class="btn">新增建筑物</el-button>
          </el-tooltip>
          <el-button type="warning"
                     icon="el-icon-download"
                     @click="exportToExcel"
                     style="float:right;margin:0 12px">批量导出钻孔</el-button>
          <el-upload action="/上传文件的接口"
                     :on-change="handleChange"
                     :auto-upload="false"
                     :show-file-list="false"
                     accept=".xls,.xlsx"
                     style="float:right">
            <el-button type="warning"
                       icon="el-icon-folder-add">批量导入钻孔</el-button>
          </el-upload>

        </div>
      </el-card>
    </div>

    <!-- 钻孔编辑框 -->
    <add-or-update-zk ref="addOrUpdateZk"
                      @refreshZkDataList="getZkDataList()"></add-or-update-zk>

    <!-- 建筑物编辑框 -->
    <add-or-update-bd ref="addOrUpdateBd"
                      @refreshBdDataList="getBdDataList()"></add-or-update-bd>

  </div>
</template>

<script>
import { loadModules } from 'esri-loader'
import AddOrUpdateZk from './zk-add-or-update'
import AddOrUpdateBd from './bd-add-or-update'
import XLSX, { utils } from 'xlsx'
import { exportJsonToExcel } from '../excel/Export2Excel'

export default {
  name: 'Map1',
  data () {
    return {
      zkDataList: [],
      bdDataList: [],
      dataListLoading: false,
      showStatus: true,
      editMapVisible: false,
      map: null,
      zkGraphicsLayer: null,
      bdGraphicsLayer: null,
      zkMarkLayer: null,
      bdMarkLayer: null,
      position: null,
      zkSimple: null,
      zkMarkSimple: null,
      bdSimple: null,
      bdmarkSimple: null,
      drawZk: null,
      drawBd: null,
      fileData: null,
      outputs: [],
      excelDataList: []
    }
  },
  components: {
    AddOrUpdateZk,
    AddOrUpdateBd
  },
  computed: {
    toogleShowBtn: function () {
      return this.showStatus === true ? '收起' : '展开'
    }
  },
  methods: {
    // 获取钻孔列表
    getZkDataList () {
      this.dataListLoading = true
      // 获得到的信息是一个prjId 是以对象的形式存储
      this.$http({
        url: this.$http.adornUrl(`/zk/zk/infoByPrjId/${this.$cookie.get('prjId')}`),
        method: 'get'
      }).then(res => {
        this.zkDataList = res.data.zk
      })
      this.dataListLoading = false
    },
    // 获取建筑物列表
    getBdDataList () {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl(`/bd/bd/infoByPrjId/${this.$cookie.get('prjId')}`),
        method: 'get'
      }).then(res => {
        this.bdDataList = res.data.bd
      })
      this.dataListLoading = false
    },
    // 渲染钻孔
    showZk () {
      var that = this
      loadModules([
        'esri/graphic',
        'esri/geometry/Point',
        'esri/SpatialReference'
      ])
        .then(
          ([
            Graphic,
            Point,
            SpatialReference
          ]) => {
            that.zkGraphicsLayer.clear()
            this.$http({
              url: this.$http.adornUrl(`/zk/zk/infoByPrjId/${that.$cookie.get('prjId')}`),
              method: 'get'
            }).then(res => {
              res.data.zk.forEach(item => {
                if (item.zkPos) {
                  var zk = new Point(item.zkPos.split(','), new SpatialReference({ wkid: 4326 }))
                  var graphicZk = new Graphic(zk, that.zkSimple)
                  that.zkGraphicsLayer.add(graphicZk)
                }
              })
            })
          })
    },
    // 渲染建筑物
    showBd () {
      var that = this
      loadModules([
        'esri/graphic',
        'esri/geometry/Polygon',
        'esri/SpatialReference'
      ])
        .then(
          ([
            Graphic,
            Polygon,
            SpatialReference
          ]) => {
            that.bdGraphicsLayer.clear()
            this.$http({
              url: this.$http.adornUrl(`/bd/bd/infoByPrjId/${that.$cookie.get('prjId')}`),
              method: 'get'
            }).then(res => {
              res.data.bd.forEach(item => {
                var mpJsonBd = { 'rings': JSON.parse(item.bdPos), 'spatialReference': ({ ' wkid': 4326 }) }
                var polygon = new Polygon(mpJsonBd)
                var graphicBd = new Graphic(polygon, that.bdSimple)
                that.bdGraphicsLayer.add(graphicBd)
              })
            })
          })
    },
    // 删除钻孔
    deleteZk (zkId, zkNum) {
      var that = this
      var zkIds = [zkId]
      this.$confirm(`<div>确定要<strong style="color:#F56C6C"> 删除 </strong>编号为：<strong style="color:#409EFF">${zkNum} </strong>的钻孔项目吗</div>`, '提示', {
        dangerouslyUseHTMLString: true,
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/zk/zk/delete'),
          method: 'post',
          data: this.$http.adornData(zkIds, false)
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: '删除成功！',
              type: 'success',
              duration: 1500,
              onClose: () => {
                that.getZkDataList()
                that.showZk()
                that.zkMarkLayer.clear()
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
      }).catch(() => {
        this.$message({ message: '删除失败' })
      })
    },
    // 删除建筑物
    deleteBd (bdId, bdNum) {
      let that = this
      var bdIds = [bdId]
      this.$confirm(`<div>确定要<strong style="color:#F56C6C"> 删除 </strong>编号为：<strong style="color:#409EFF">${bdNum} </strong>的建筑物项目吗</div>`, '提示', {
        dangerouslyUseHTMLString: true,
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/bd/bd/delete'),
          method: 'post',
          data: this.$http.adornData(bdIds, false)
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: '删除成功！',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.getBdDataList()
                this.showBd()
                that.bdMarkLayer.clear()
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
      }).catch(() => {
        this.$message({ message: '删除失败' })
      })
    },
    // 修改钻孔
    updateZk (zkId) {
      this.$nextTick(() => {
        this.$refs.addOrUpdateZk.init(zkId)
      })
    },
    // 修改建筑物
    updateBd (bdId) {
      this.$nextTick(() => {
        this.$refs.addOrUpdateBd.init(bdId)
      })
    },
    // 新增钻孔
    addZk () {
      let that = this
      loadModules([
        'esri/toolbars/draw'
      ])
        .then(
          ([
            Draw
          ]) => {
            that.drawZk = new Draw(that.map)
            that.drawZk.activate(Draw.POINT)
            that.drawZk.on('draw-complete', function (resultDraw) {
              var geometry = resultDraw.geometry
              that.drawZk.deactivate()
              that.$nextTick(() => {
                that.$refs.addOrUpdateZk.add(geometry)
              })
            })
          }
        )
        .catch(err => {
          window.console.log(err)
        })
    },
    // 新增建筑物
    addBd () {
      let that = this
      loadModules([
        'esri/toolbars/draw'
      ])
        .then(
          ([
            Draw
          ]) => {
            that.drawBd = new Draw(that.map)
            that.drawBd.activate(Draw.POLYGON)
            that.drawBd.on('draw-complete', function (resultDraw) {
              var geometry = resultDraw.geometry
              that.drawBd.deactivate()
              that.$nextTick(() => {
                that.$refs.addOrUpdateBd.add(geometry)
              })
            })
          }
        )
        .catch(err => {
          window.console.log(err)
        })
    },
    // 高亮钻孔
    markZk (row) {
      let that = this
      loadModules([
        'esri/graphic',
        'esri/SpatialReference',
        'esri/geometry/Point'
      ])
        .then(
          ([
            Graphic,
            SpatialReference,
            Point
          ]) => {
            that.zkMarkLayer.clear()
            if (row.zkPos) {
              var pt = new Point(row.zkPos.split(','), new SpatialReference({ wkid: 4326 }))
              var zk = new Graphic(pt, that.zkMarkSimple)
              that.zkMarkLayer.add(zk)
            }
          }
        )
    },
    // 高亮建筑物
    markBd (row) {
      let that = this
      loadModules([
        'esri/graphic',
        'esri/geometry/Polygon'
      ])
        .then(
          ([
            Graphic,
            Polygon
          ]) => {
            that.bdMarkLayer.clear()
            if (row.bdPos) {
              var mpJsonBd = { 'rings': JSON.parse(row.bdPos), 'spatialReference': ({ ' wkid': 4326 }) }
              var polygon = new Polygon(mpJsonBd)
              var bd = new Graphic(polygon, that.bdMarkSimple)
              that.bdMarkLayer.add(bd)
            }
          }
        )
        .catch(err => {
          window.console.log(err)
        })
    },
    // 展示框展开/收起
    toogleShow () {
      this.showStatus = !this.showStatus
    },
    // 用Excel导入钻孔
    handleChange (file) {
      this.fileTemp = file.raw
      if (this.fileTemp) {
        if (
          this.fileTemp.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' ||
          this.fileTemp.type === 'application/vnd.ms-excel' ||
          this.fileTemp.type === 'application/wps-office.xls'
        ) {
          this.importExcel(this.fileTemp)
            .then(res => {
              if (this.formatExcelData(res)) {
                window.console.log('1')
              }
            })
            .catch(err => {
              this.$message.error(`文件读取失败！+${err}`)
            })
        } else {
          this.$message.error('文件格式错误，请删除后重新上传！')
        }
      } else {
        this.$message.error('请上传excel文件！')
      }
    },
    async importExcel (file) {
      const workBook = await this.readerWorkBookFromLocal(file)
      const workSheet = workBook.Sheets[workBook.SheetNames[0]]
      const content = utils.sheet_to_json(workSheet)
      const data = {}
      data.title = Object.keys(content[0])
      data.body = content
      return data
    },
    readerWorkBookFromLocal (file) {
      const reader = new FileReader()
      reader.readAsBinaryString(file)
      return new Promise(function (resolve, reject) {
        reader.onload = function (e) {
          const fileData = e.target.result
          if (reader.readyState === 2) {
            const workBook = XLSX.read(fileData, { type: 'binary' })
            resolve(workBook)
          } else {
            reject(new Error('读取文件失败'))
          }
        }
      })
    },
    formatExcelData (data) {
      let that = this
      let res = []
      data.body.forEach(item => {
        let tmp = item
        tmp.prjId = that.$cookie.get('prjId')
        tmp.prjName = that.$cookie.get('prjName')
        res.push(tmp)
      })
      that.$nextTick(() => {
        that.$refs.addOrUpdateZk.saveExcel(res)
      })
    },
    // 导出Excel表格
    exportToExcel () {
      // excel数据导出
      require.ensure([], () => {
        const tHeader = [
          '钻孔ID',
          '钻孔编号',
          '钻孔位置',
          '高程',
          '深度',
          '地层描述',
          '工程ID',
          '工程名称'
        ]
        const filterVal = [
          'zkId',
          'zkNum',
          'zkPos',
          'zkElv',
          'zkDep',
          'zkDsp',
          'prjId',
          'prjName'
        ]
        const list = this.zkDataList
        const data = this.formatJson(filterVal, list)
        exportJsonToExcel(tHeader, data, '钻孔列表excel')
      })
    },
    formatJson (filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]))
    }
  },
  created () {
    this.getZkDataList()
    this.getBdDataList()
  },
  mounted () {
    loadModules(
      [
        'esri/map',
        'esri/layers/ArcGISTiledMapServiceLayer',
        'esri/layers/GraphicsLayer',
        'esri/graphic',
        'esri/SpatialReference',
        'esri/geometry/Point',
        'esri/geometry/Multipoint',
        'esri/symbols/SimpleMarkerSymbol',
        'esri/symbols/SimpleLineSymbol',
        'esri/symbols/SimpleFillSymbol',
        'esri/Color',
        'dojo/domReady!'
      ],
      {
        url: 'https://js.arcgis.com/3.32/',
        css: 'https://js.arcgis.com/3.32/esri/css/esri.css'
      }
    )
      .then(
        ([
          Map,
          ArcGISTiledMapServiceLayer,
          GraphicsLayer,
          Graphic,
          SpatialReference,
          Point,
          Multipoint,
          SimpleMarkerSymbol,
          SimpleLineSymbol,
          SimpleFillSymbol,
          Color
        ]) => {
          let that = this
          // 创建底地图图层
          that.map = new Map('mapContainer', {
            logo: false,
            slider: false,
            isDoubleClickZoom: false,
            isKeyboardNavigation: false,
            fadeOnZoom: true,
            maxZoom: 20, // 最大空间等级
            minZoom: 7 // 最小空间等级
          })

          // 创建江苏省底图层，并将图层添加到map中
          const baseLayer = new ArcGISTiledMapServiceLayer('http://218.94.6.92:6080/arcgis/rest/services/jspub_raster_L4_L20/MapServer')
          that.map.addLayer(baseLayer)
          const mapcenter = new Point([118.802997, 32.070282], new SpatialReference({ wkid: 4490 }))
          that.map.centerAndZoom(mapcenter, 7)
          // 创建graphicsLayer层 准备接受 graphic类信息
          that.zkGraphicsLayer = new GraphicsLayer({ id: 'zkGraphicsLayer' })
          that.bdGraphicsLayer = new GraphicsLayer({ id: 'bdGraphicsLayer' })
          that.zkMarkLayer = new GraphicsLayer({ id: 'zkMarkLayer' })
          that.bdMarkLayer = new GraphicsLayer({ id: 'bdMarkLayer' })
          that.map.addLayer(that.zkGraphicsLayer)
          that.map.addLayer(that.bdGraphicsLayer)
          that.map.addLayer(that.zkMarkLayer)
          that.map.addLayer(that.bdMarkLayer)

          // 地图事件：获取当前点击坐标
          that.map.on('click', function (e) {
            var x = e.mapPoint.x
            var y = e.mapPoint.y
            that.position = x.toFixed(8) + ',' + y.toFixed(8)
          })

          // 钻孔符号类型
          that.zkSimple = new SimpleMarkerSymbol(
            SimpleMarkerSymbol.STYLE_CIRCLE,
            5,
            new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([255, 215, 0]), 3),
            new Color([255, 215, 0])
          )
          // 建筑物符号类型
          that.bdSimple = new SimpleFillSymbol(
            SimpleFillSymbol.STYLE_SOLID,
            new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([255, 215, 0]), 1),
            new Color([255, 165, 0, 0.75])
          )
          // 高亮钻孔
          that.zkMarkSimple = new SimpleMarkerSymbol(
            SimpleMarkerSymbol.STYLE_CIRCLE,
            5,
            new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([255, 0, 0]), 5),
            new Color([0, 0, 255])
          )
          // 高亮建筑物
          that.bdMarkSimple = new SimpleFillSymbol(
            SimpleFillSymbol.STYLE_SOLID,
            new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([255, 0, 0]), 2),
            new Color([255, 165, 0, 0.25])
          )

          this.showZk()
          this.showBd()
        }
      ).catch(err => {
        window.console.log(err)
      })
  }
}
</script>

<style lang="scss">
.global {
  height: 90vh;
}
.global .mapview {
  box-sizing: border-box;
  height: 100%;
}
.global .editMapContainer {
  height: 90%;
  z-index: 60;
}
.global .show-card {
  position: absolute;
  right: 2%;
  top: 4.5%;
  z-index: 10;
  border: 1px solid #dcdfe6;
  border-radius: 10px;
}
.global .homeButtonContainer {
  position: absolute;
  top: 110px;
  left: 42px;
  z-index: 50;
}
.global .LocateButtonContainer {
  position: absolute;
  top: 150px;
  left: 42px;
  z-index: 50;
}
.global .el-divider--horizontal {
  margin: 30px 0;
  background: 0 0;
  border-top: 1px solid #e8eaec;
}
.global .show-card-divider {
  margin: 24px 0;
}
.global .position {
  position: absolute;
  top: 7%;
  left: 2.4%;
  width: 245px;
  z-index: 100;
  display: inline-block;
  font-weight: 700;
  line-height: 30px;
  font-size: 16px;
  opacity: 0.85;
}
.global .wenzi {
  position: absolute;
  font-size: 14px;
  top: 4.3%;
  left: 3.1%;
  z-index: 100;
}
.global .btn {
  margin: 0 10px;
}
</style>