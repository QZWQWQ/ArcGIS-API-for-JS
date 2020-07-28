<template>
  <div id="map"
       class="global1">

    <!-- 地图界面 -->
    <div id="mapContainer"
         class="mapview">
    </div>

    <!-- 主页按钮 -->
    <div class="homeButtonContainer">
      <div id='HomeButton'></div>
    </div>

    <!-- 定位按钮 -->
    <div class="LocateButtonContainer">
      <div id='LocateButton'></div>
    </div>

    <!-- 查询/展示框 -->
    <div class="searchCardContainer">
      <!-- 查询框 -->
      <el-card class="search-card">
        <div class="search-card-header">
          <span style="font-size:18px;color:#303133;font-weight:bold">数据操作框</span>
          <el-button @click="toogleSearch()"
                     style="float: right; padding: 3px 0;font-size:17px;color:#909399;font-weight:bold;line-height:0.5"
                     type="text">{{toogleSearchBtn}}</el-button>
        </div>
        <div class="search-card-content"
             v-if="searchStatus">
          <el-divider class="search-card-divider">字段查询</el-divider>
          <el-form :model="search">
            <el-form-item>
              <el-select v-model="search.layer"
                         class="layer-item"
                         placeholder="请选择图层">
                <el-option v-for="item in layers"
                           :key="item.id"
                           :label="item.title"
                           :value="item.id"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-select v-model="search.fileds"
                         class="layer-item"
                         filterable
                         placeholder="请选择查询的字段名">
                <el-option v-for="item in fileds"
                           :key="item"
                           :label="item"
                           :value="item"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-input v-model="search.text"
                        @keyup.enter.native="searchByAttr(search)"
                        class="layer-item"
                        placeholder="请输入查询内容">
                <el-button @click="searchByAttr(search)"
                           slot="append"
                           icon="el-icon-search"></el-button>
              </el-input>
            </el-form-item>
            <el-divider class="search-card-divider">图形查询</el-divider>
            <el-form-item class="search-card-btn">
              <el-button type="primary"
                         @click="spaceSearchByCircle">
                正圆形
              </el-button>
              <el-button type="primary"
                         @click="spaceSearchByPolygon">
                多边形
              </el-button>
              <el-button type="danger"
                         @click="spaceSearchClear">
                清除
              </el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-card>
      <!-- 展示框 -->
      <transition name="el-fade-in-linear">
        <el-card class="show-card"
                 v-show="showCardVisible">
          <div class="show-card-header">
            <span style="font-size:18px;color:#303133;font-weight:bold">查询结果</span>
            <el-button style="float:right;padding:3px 0;font-size:17px;color:#909399;font-weight:bold;line-height:0.5"
                       type="text"
                       @click="showCardVisible = false">关闭</el-button>
          </div>
          <el-divider class="show-card-divider"></el-divider>
          <div class="show-card-content">
            <el-table :data="searchResult"
                      stripe
                      border
                      style="width: 100%"
                      height="420">
              <el-table-column prop="GQMC"
                               label="工程名称"></el-table-column>
              <el-table-column prop="GQBM"
                               label="工程编码"></el-table-column>
              <el-table-column prop="DATAFROM"
                               label="数据来源"></el-table-column>
              <el-table-column prop="SCALE"
                               label="规模"></el-table-column>
              <el-table-column prop="layerName"
                               label="图层名称"></el-table-column>
            </el-table>
          </div>
        </el-card>
      </transition>
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
      search: {
        layer: '',
        fileds: '',
        text: ''
      },
      searchStatus: true,
      searchResult: [],
      layers: [],
      showCardVisible: false,
      baseUrl: null
    }
  },
  computed: {
    toogleSearchBtn: function () {
      return this.searchStatus === true ? '收起' : '展开'
    }
  },
  methods: {
    spaceSearchClear () {
      console.log(this.map.graphics)
      this.map.graphics.clear()
    },

    // searchByAttr (search) {
    //   let that = this
    //   loadModules([
    //     'esri/tasks/FindTask',
    //     'esri/tasks/FindParameters'
    //   ])
    //     .then(
    //       ([
    //         FindTask,
    //         FindParameters
    //       ]) => {
    //         console.log('我执行到了searchByAttr()函数')
    //         console.log('我是searchByAttr()的参数', search)
    //         // 定义属性查询对象，注意他的参数是整个地图服务，而不是单个图层
    //         var findTask = new FindTask(baseUrl.baseLayer)
    //         // 定义属性查询参数对象
    //         var findParameters = new FindParameters()
    //         // 是否返回几何信息:如果为“ true”，则结果集包括与每个结果关联的几何
    //         findParameters.returnGeometry = true
    //         // 查询的图层,对layerIds进行格式处理：string -> string[]
    //         findParameters.layerIds = [parseInt(search.layer)]
    //         // 查询的字段，对fields进行格式处理：string -> string[]
    //         findParameters.searchFields = [search.fileds]
    //         // 查询的内容
    //         findParameters.searchText = search.text
    //         // 执行属性查询 并传参给展示函数
    //         findTask.execute(findParameters, that.showQueryResult)
    //       }
    //     )
    //     .catch(err => {
    //       window.console.log(err)
    //     })
    // },

    spaceSearchByCircle () {
      let that = this
      loadModules([
        'esri/toolbars/draw'
      ])
        .then(
          ([
            Draw
          ]) => {
            // 定义绘图对象
            var draw = new Draw(that.map)
            // 激活圆形绘图工具
            draw.activate(Draw.CIRCLE)
            // 给绘图工具绑定绘图完成事件
            draw.on('draw-complete', function (resultDraw) {
              // 获得绘图得到的面
              var geometry = resultDraw.geometry
              // 关闭绘图工具
              draw.deactivate()
              // 执行空间查询
              that.searchBySpace(geometry)
            })
          }
        )
        .catch(err => {
          window.console.log(err)
        })
    },
    spaceSearchByPolygon () {
      let that = this
      loadModules([
        'esri/toolbars/draw'
      ])
        .then(
          ([
            Draw
          ]) => {
            // 定义绘图对象
            var draw = new Draw(that.map)
            // 激活多边形绘图工具
            draw.activate(Draw.POLYGON)
            // 给绘图工具绑定绘图完成事件
            draw.on('draw-complete', function (resultDraw) {
              // 获得绘图得到的面
              // debugger
              var geometry = resultDraw.geometry
              // 关闭绘图工具
              draw.deactivate()
              // 执行空间查询
              that.searchBySpace(geometry)
            })
          }
        )
        .catch(err => {
          window.console.log(err)
        })
    },
    searchBySpace (geometry) {
      let that = this
      loadModules([
        'esri/tasks/IdentifyTask',
        'esri/tasks/IdentifyParameters'
      ])
        .then(
          ([
            IdentifyTask,
            IdentifyParameters
          ]) => {
            console.log('我执行到了searchBySpace()函数')
            console.log('我是searchBySpace()的参数', geometry)
            // 定义空间查询对象，注意他的参数是整个地图服务，而不是单个图层
            var identifyTask = new IdentifyTask(that.baseLayer)
            // 定义空间查询参数对象
            var params = new IdentifyParameters()
            // 设置查询容差:屏幕像素与应在其中执行识别的指定几何图形之间的距离
            params.tolerance = 5
            // 是否返回几何信息:如果为“ true”，则结果集包括与每个结果关联的几何
            params.returnGeometry = true
            // 空间查询的图层:在其上执行标识操作的层，此时是四个图层
            params.layerIds = ['zkGraphicsLayer', 'bdGraphicsLayer', 'zkMarkLayer', 'bdMarkLayer']
            // 空间查询的条件:指定使用"标识"时要使用的图层
            params.layerOption = IdentifyParameters.LAYER_OPTION_ALL
            params.width = that.map.width
            params.height = that.map.height
            // 空间查询的几何对象
            params.geometry = geometry
            params.mapExtent = that.map.extent
            // 执行空间查询 并传参给展示函数
            identifyTask.execute(params, that.showQueryResult)
          }
        )
        .catch(err => {
          window.console.log(err)
        })
    },
    showQueryResult (result) {
      let that = this
      loadModules([
        'esri/Color',
        'esri/symbols/SimpleLineSymbol',
        'esri/symbols/SimpleFillSymbol',
        'esri/graphic'
      ]).then(
        ([
          Color,
          SimpleLineSymbol,
          SimpleFillSymbol,
          Graphic
        ]) => {
          that.searchResult = []
          console.log('我来到了showQueryResult()函数')
          // 接受的参数为很多数组，每个数组中有两个对象元素[object Object]
          console.log('我是result的汇总:', result)
          // debugger
          for (let item in result) {
            console.log('我是一项result的值', result[item])
            // 如果GQMC存在，则纳入对象数组中
            if (result[item].feature.attributes.GQMC) {
              let obj = {
                ...result[item].feature.attributes
              }
              // obj.GQMC = result[item].feature.attributes.GQMC
              // obj.SPMC = result[item].feature.attributes.SPMC
              // 将参数转换为对象数组，并传参给展示框
              that.searchResult.push(obj)
            }
          }
          console.log('我来到了showQueryResult这个函数,后面紧跟的是searchResult的值', that.searchResult)
          // 先清除原有的graphic
          that.map.graphics.clear()

          // 创建线符号
          var lineSymbol = new SimpleLineSymbol(
            // style
            SimpleLineSymbol.STYLE_SOLID,
            // color
            new Color('#FF5151'),
            // width
            2
          )

          // 创建面符号
          var fillSymbol = new SimpleFillSymbol(
            SimpleFillSymbol.STYLE_SOLID,
            // lineSymbol
            lineSymbol,
            new Color('#FFE4B5')
          )

          if (result.length === 0) {
            that.$message.info('没有查询到对应的数据')
          } else {
            // 查询结果卡片展示
            that.showCardVisible = true
            for (let i = 0; i < result.length; i++) {
              // 获得图形graphic
              var graphic = result[i].feature
              // 赋予graphic面符号
              graphic.setSymbol(fillSymbol)
              // 将graphic添加到地图中，从而实现高亮效果
              that.map.graphics.add(graphic)
            }
          }
        }
      ).catch(err => {
        window.console.log(err)
      })
    },
    showZk () {
      var that = this
      loadModules([
        'esri/graphic',
        'esri/geometry/Multipoint'
      ])
        .then(
          ([
            Graphic,
            Multipoint
          ]) => {
            that.zkGraphicsLayer.clear()
            this.$http({
              url: this.$http.adornUrl(`/zk/zk/list`),
              method: 'get'
            }).then(res => {
              console.log(res.data.page.list)
              let zkList = res.data.page.list.map((el, index) => {
                if (el.zkPos) {
                  return el.zkPos.split(',')
                }
              })
              console.log(zkList)
              var mpJsonZk = { 'points': zkList, 'spatialReference': ({ ' wkid': 4326 }) }
              var multiPtZk = new Multipoint(mpJsonZk)
              var graphicZk = new Graphic(multiPtZk, that.zkSimple)
              that.zkGraphicsLayer.add(graphicZk)
              that.map.addLayer(that.zkGraphicsLayer)
            })
          })
    },
    // 展示框展开/收起
    toogleSearch () {
      this.searchStatus = !this.searchStatus
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
        'esri/dijit/HomeButton',
        'esri/dijit/LocateButton',
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
          HomeButton,
          LocateButton,
          Draw
        ]) => {
          let that = this
          // 创建底地图图层
          that.map = new Map('mapContainer', {
            logo: false,
            isDoubleClickZoom: false,
            isKeyboardNavigation: false
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

          // 创建主页按钮
          var homeBtn = new HomeButton({
            map: that.map
          }, 'HomeButton')
          homeBtn.startup()

          // 创建定位按钮
          var locBtn = new LocateButton({
            map: that.map
          }, 'LocateButton')
          locBtn.startup()

          that.showZk()
          // this.showBd()
        })
      .catch(err => {
        window.console.log(err)
      })
  }
}
</script>

<style lang="scss">
.global1 {
  height: 90vh;
}
.global1 .mapview {
  box-sizing: border-box;
  height: 100%;
}
.global1 .search-card {
  position: absolute;
  width: 310px;
  right: 3%;
  top: 5.5%;
  z-index: 10;
  border: 1px solid #dcdfe6;
  border-radius: 10px;
}
.global1 .show-card {
  position: absolute;
  width: 600px;
  top: 5.5%;
  right: 3%;
  z-index: 11;
  border: 1px solid #dcdfe6;
  border-radius: 10px;
}
.global1 .search-card-btn {
  margin: 0;
}
.global1 .layer-item {
  width: 250px;
}
.global1 .homeButtonContainer {
  position: absolute;
  top: 110px;
  left: 42px;
  z-index: 50;
}
.global1 .LocateButtonContainer {
  position: absolute;
  top: 150px;
  left: 42px;
  z-index: 50;
}
.global1 .el-divider--horizontal {
  margin: 30px 0;
  background: 0 0;
  border-top: 1px solid #e8eaec;
}
.global1 .search-card-divider {
  margin: 22px 0;
}
.global1 .show-card-divider {
  margin: 24px 0 0 0;
}
</style>
