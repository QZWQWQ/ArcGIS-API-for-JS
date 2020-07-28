<template>
  <div id="map"
       class="show">

    <!-- 地图界面 -->
    <div id="mapContainer"
         class="mapview">
      <div class="wenzi">
        <span style="font-size:18px;color:#303133;font-weight:bold;">当前坐标点如下：</span>
      </div>
      <el-input v-model="position"
                class="position"
                readonly="readonly"></el-input>
    </div>

    <!-- 钻孔/建筑物信息框  -->
    <div class="showCardContainer">
      <el-card class="show-card">
        <div class="show-card-header">
          <el-tooltip class="item"
                      effect="dark"
                      content="当输入框为空时查询结果为全局"
                      placement="top-start">
            <el-input v-model="searchTxt"
                      placeholder="关键字搜索"
                      clearable
                      @keyup.enter.native="getDataList('1')"
                      style="width:210px;margin-right:10px">
              <el-button slot="append"
                         icon="el-icon-search"
                         @click="getDataList('1')"></el-button>
            </el-input>
          </el-tooltip>
          <el-button type="primary"
                     @click="spaceSearch();flag=1">圆形查询</el-button>
          <el-button type="success"
                     @click="spaceSearch();flag=2">多边形查询</el-button>
          <el-button type="danger"
                     @click="clear();flag=0">清除</el-button>
          <el-button @click="toogleShow()"
                     style="float:right;;font-size:20px;color:#909399;font-weight:bold;line-height:0.4"
                     type="text">{{toogleShowBtn}}</el-button>
        </div>
        <div class="show-card-content"
             v-if="showStatus">
          <el-divider class="show-card-divider">钻孔信息</el-divider>
          <el-table :data="zkDataList"
                    border
                    stripe
                    style="width:100%;font-size:15px"
                    height="280"
                    :cell-style="{padding:0 +'px'}"
                    :header-cell-style="{background:'#FDF5E6',color:'#606266'}"
                    @row-click="markZk">
            <el-table-column prop="prjName"
                             header-align="center"
                             align="center"
                             sortable
                             label="所属工程名称"
                             width="140"></el-table-column>
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
          </el-table>
        </div>
        <div class="show-card-content"
             v-if="showStatus">
          <el-divider class="show-card-divider">建筑物信息</el-divider>
          <el-table :data="bdDataList"
                    border
                    stripe
                    style="width: 100%;font-size:15px"
                    height="220"
                    :cell-style="{padding:0 +'px'}"
                    :header-cell-style="{background:'#FDF5E6',color:'#606266'}"
                    @row-click="markBd">
            <el-table-column prop="prjName"
                             header-align="center"
                             align="center"
                             sortable
                             label="所属工程名称"></el-table-column>
            <el-table-column prop="bdName"
                             header-align="center"
                             align="center"
                             label="建筑物名称"></el-table-column>
            <el-table-column prop="bdType"
                             header-align="center"
                             align="center"
                             label="类型"></el-table-column>
          </el-table>
        </div>
      </el-card>
    </div>

    <!-- 工程展示框 -->
    <div class="prjCardContainer"
         v-if="prjStatus">
      <el-card class="prj-card">
        <div slot="header"
             class="clearfix">
          <span style="font-size:17px;color:black;font-weight:bold;line-height:0.4">工程信息</span>
          <el-button @click="tooglePrj()"
                     style="float:right; padding:0;font-size:17px;font-weight:bold;color:#909399;line-height:0.4"
                     type="text">关闭</el-button>
        </div>
        <div>
          <el-row :gutter="20"
                  class="grid-content">
            <el-col :span="8">
              <div style="font-size:16px;color:#909399;font-weight:bold;line-height:2.4">工程名称</div>
            </el-col>
            <el-col :span="16">
              <el-input v-model="prjDataList.prjName"
                        readonly="readonly"></el-input>
            </el-col>
          </el-row>
          <el-row :gutter="20"
                  class="grid-content">
            <el-col :span="8">
              <div style="font-size:16px;color:#909399;font-weight:bold;line-height:2.4">勘测编号</div>
            </el-col>
            <el-col :span="16">
              <el-input v-model="prjDataList.surveyNum"
                        readonly="readonly"></el-input>
            </el-col>
          </el-row>
          <el-row :gutter="20"
                  class="grid-content">
            <el-col :span="8">
              <div style="font-size:16px;color:#909399;font-weight:bold;line-height:2.4">时间</div>
            </el-col>
            <el-col :span="16"
                    class="grid-content">
              <el-input v-model="prjDataList.time"
                        readonly="readonly"></el-input>
            </el-col>
          </el-row>
          <el-row :gutter="20"
                  class="grid-content">
            <el-col :span="8">
              <div style="font-size:16px;color:#909399;font-weight:bold;line-height:2.4">项目阶段</div>
            </el-col>
            <el-col :span="16">
              <el-input v-model="prjDataList.stage"
                        readonly="readonly"></el-input>
            </el-col>
          </el-row>
          <el-row :gutter="20"
                  class="grid-content">
            <el-col :span="8">
              <div style="font-size:16px;color:#909399;font-weight:bold;line-height:2.4">地貌</div>
            </el-col>
            <el-col :span="16">
              <el-input v-model="prjDataList.landform"
                        readonly="readonly"></el-input>
            </el-col>
          </el-row>
          <el-row :gutter="20"
                  class="grid-content">
            <el-col :span="8">
              <div style="font-size:16px;color:#909399;font-weight:bold;line-height:2.4">河道</div>
            </el-col>
            <el-col :span="16">
              <el-input v-model="prjDataList.river"
                        readonly="readonly"></el-input>
            </el-col>
          </el-row>
          <el-row :gutter="20"
                  class="grid-content">
            <el-col :span="8">
              <div style="font-size:16px;color:#909399;font-weight:bold;line-height:2.4">所在地</div>
            </el-col>
            <el-col :span="16">
              <el-input v-model="prjDataList.location"
                        readonly="readonly"></el-input>
            </el-col>
          </el-row>
          <el-row :gutter="20"
                  class="grid-content">
            <el-col :span="16">
              <el-button type="text"
                         style="font-size:16px;color:	#1E90FF;font-weight:bold;margin:0;padding:5px;"
                         @click="getDataList('2')">点击此处只看该工程</el-button>
            </el-col>
          </el-row>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import { loadModules } from 'esri-loader'

export default {
  name: 'Map',
  data () {
    return {
      map: null,
      zkGraphicsLayer: null,
      bdGraphicsLayer: null,
      zkMarkLayer: null,
      bdMarkLayer: null,
      zkSimple: null,
      zkMarkSimple: null,
      bdSimple: null,
      bdmarkSimple: null,
      flag: 0,
      showStatus: true,
      prjStatus: false,
      prjDataList: {},
      zkDataList: [],
      bdDataList: [],
      position: '',
      searchTxt: ''
    }
  },
  computed: {
    toogleShowBtn: function () {
      return this.showStatus === true ? '收起' : '展开'
    }
  },
  methods: {
    // 清除
    clear () {
      this.zkDataList = []
      this.bdDataList = []
      this.zkMarkLayer.clear()
      this.bdMarkLayer.clear()
    },
    // 显示钻孔
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
              url: this.$http.adornUrl(`/zk/zk/list`),
              method: 'get'
            }).then(res => {
              res.data.page.list.forEach(item => {
                if (item.zkPos) {
                  var zk = new Point(item.zkPos.split(','), new SpatialReference({ wkid: 4326 }))
                  var graphicZk = new Graphic(zk, that.zkSimple)
                  graphicZk.attributes = item
                  that.zkGraphicsLayer.add(graphicZk)
                }
              })
            })
          })
    },
    // 显示建筑物
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
              url: this.$http.adornUrl(`/bd/bd/list`),
              method: 'get'
            }).then(res => {
              res.data.page.list.forEach(item => {
                var mpJsonBd = { 'rings': JSON.parse(item.bdPos), 'spatialReference': ({ ' wkid': 4326 }) }
                var polygon = new Polygon(mpJsonBd)
                var graphicBd = new Graphic(polygon, that.bdSimple)
                graphicBd.attributes = item
                that.bdGraphicsLayer.add(graphicBd)
              })
            })
          })
    },
    // 绘图工具获取空间信息并高亮显示
    spaceSearch () {
      let that = this
      loadModules([
        'esri/toolbars/draw',
        'esri/graphic',
        'esri/SpatialReference',
        'esri/geometry/Point',
        'esri/geometry/Polygon'
      ])
        .then(
          ([
            Draw,
            Graphic,
            SpatialReference,
            Point,
            Polygon
          ]) => {
            // 开启画图工具
            var draw = new Draw(that.map)
            // 判断使用哪种作图方式
            switch (that.flag) {
              case 1:
                draw.activate(Draw.CIRCLE)
                break
              case 2:
                draw.activate(Draw.POLYGON)
                break
              default: draw.finishDrawing()
            }
            // 画图结束后的事件
            draw.on('draw-complete', function (resultDraw) {
              that.flag = 0
              var geometry = resultDraw.geometry
              draw.deactivate()
              // 查询出钻孔信息 => push到zkDataList中 => 高亮显示
              that.zkMarkLayer.clear()
              var zkGraphics = that.zkGraphicsLayer.graphics
              that.zkDataList = []
              for (let i = 0; i < zkGraphics.length; i++) {
                if (geometry.contains(zkGraphics[i].geometry)) {
                  that.zkDataList.push(zkGraphics[i].attributes)
                  var pt = new Point(zkGraphics[i].geometry.x, zkGraphics[i].geometry.y, new SpatialReference({ wkid: 4326 }))
                  var zkMark = new Graphic(pt, that.zkMarkSimple)
                  that.zkMarkLayer.add(zkMark)
                }
              }
              // 查询出建筑物信息 => push到bdDataList中 => 高亮显示
              that.bdMarkLayer.clear()
              var bdGraphics = that.bdGraphicsLayer.graphics
              that.bdDataList = []
              for (let i = 0; i < bdGraphics.length; i++) {
                if (geometry.getExtent().intersects(bdGraphics[i].geometry)) {
                  that.bdDataList.push(bdGraphics[i].attributes)
                  var mpJsonBd = { 'rings': bdGraphics[i].geometry.rings, 'spatialReference': ({ ' wkid': 4326 }) }
                  var polygon = new Polygon(mpJsonBd)
                  var bd = new Graphic(polygon, that.bdMarkSimple)
                  that.bdMarkLayer.add(bd)
                }
              }
              // 查询包含该钻孔和建筑物的工程
            })
          })
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
            that.prjStatus = true
            that.zkMarkLayer.clear()
            if (row.zkPos) {
              var pt = new Point(row.zkPos.split(','), new SpatialReference({ wkid: 4326 }))
              var zk = new Graphic(pt, that.zkMarkSimple)
              that.zkMarkLayer.add(zk)
            }
            this.$http({
              url: this.$http.adornUrl(`/survey/info/${row.prjId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({ data }) => {
              this.prjDataList = data.prj
            })
          }
        )
        .catch(err => {
          window.console.log(err)
        })
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
            that.prjStatus = true
            that.bdMarkLayer.clear()
            var mpJsonBd = { 'rings': JSON.parse(row.bdPos), 'spatialReference': ({ ' wkid': 4326 }) }
            var polygon = new Polygon(mpJsonBd)
            var bd = new Graphic(polygon, that.bdMarkSimple)
            that.bdMarkLayer.add(bd)
            this.$http({
              url: this.$http.adornUrl(`/survey/info/${row.prjId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({ data }) => {
              this.prjDataList = data.prj
            })
          }
        )
        .catch(err => {
          window.console.log(err)
        })
    },
    // 属性查询
    getDataList (txt) {
      let name = ''
      if (txt === '1') {
        name = this.searchTxt
      } else if (txt === '2') {
        name = this.prjDataList.prjName || ''
      }
      let that = this
      this.$http({
        url: this.$http.adornUrl('/zk/zk/list'),
        method: 'get',
        params: this.$http.adornParams({
          'name': name
        })
      }).then(({ data }) => {
        that.zkDataList = data.page.list
      })
      this.$http({
        url: this.$http.adornUrl('/bd/bd/list'),
        method: 'get',
        params: this.$http.adornParams({
          'name': name
        })
      }).then(({ data }) => {
        that.bdDataList = data.page.list
      })
    },
    // 展示框展开/收起
    toogleShow () {
      this.showStatus = !this.showStatus
    },
    // 工程框展开/收起
    tooglePrj () {
      this.prjStatus = !this.prjStatus
    }
  },
  created () {

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
        'esri/toolbars/draw',
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
          Color,
          Draw
        ]) => {
          let that = this
          // 创建底地图图层
          that.map = new Map('mapContainer', {
            logo: false,
            isDoubleClickZoom: false,
            isKeyboardNavigation: false,
            fadeOnZoom: true,
            maxZoom: 20, // 最大空间等级
            minZoom: 7, // 最小空间等级
            slider: false
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

          that.showZk()
          that.showBd()
        })
      .catch(err => {
        window.console.log(err)
      })
  }
}
</script>

<style lang="scss">
.show {
  height: 90vh;
}
.show .mapview {
  box-sizing: border-box;
  height: 100%;
}
.show .show-card {
  position: absolute;
  width: 630px;
  top: 3.5%;
  right: 2%;
  z-index: 11;
  border: 1px solid #dcdfe6;
  border-radius: 10px;
}
.show .el-divider--horizontal {
  margin: 30px 0;
  background: 0 0;
  border-top: 1px solid #e8eaec;
}
.show .show-card-divider {
  margin: 22px 0;
}
.show .prj-card {
  position: absolute;
  width: 300px;
  top: 3.5%;
  left: 2%;
  z-index: 11;
  border: 1px solid #dcdfe6;
  border-radius: 10px;
}
.show .prj-card .el-card__body {
  padding: 10px;
}
.show .el-form {
  margin: 0;
  padding: 0;
}
.show .prjCardContainer {
  opacity: 0.8;
}
.show .grid-content {
  margin-bottom: 7px;
}
.show .el-divider__text.is-center {
  font-size: 18px;
  color: black;
}
.show .wenzi {
  position: absolute;
  font-size: 14px;
  bottom: 10%;
  left: 3.1%;
  z-index: 100;
}
.show .position {
  position: absolute;
  bottom: 5%;
  left: 2.4%;
  width: 245px;
  z-index: 100;
  display: inline-block;
  font-weight: 700;
  line-height: 30px;
  font-size: 16px;
  opacity: 0.85;
}
</style>
