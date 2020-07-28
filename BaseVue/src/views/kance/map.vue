<template>
  <div id="map">

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

    <!-- 功能框 -->
    <div class="searchCardContainer">
      <!-- 操作框 -->
      <el-card class="search-card">
        <div class="search-card-header">
          <span style="font-size:18px;color:#303133;font-weight:bold">数据操作框</span>
          <el-button @click="toogleSearch()"
                     style="float: right; padding: 3px 0;font-size:17px;color:#909399;font-weight:bold;line-height:0.5"
                     type="text">{{toogleSearchBtn}}</el-button>
        </div>
        <div class="search-card-content"
             v-if="searchStatus">
          <el-divider>字段查询</el-divider>
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
            <el-divider>图形查询</el-divider>
            <el-form-item class="btns1">
              <el-button id=""
                         type="info"
                         @click="spaceSearchByCircle">
                正圆形
              </el-button>
              <el-button id=""
                         type="info"
                         @click="spaceSearchByPolygon">
                多边形
              </el-button>
              <el-button id=""
                         type="danger"
                         @click="spaceSearchClear">
                清除
              </el-button>
            </el-form-item>
            <el-divider>添加信息</el-divider>
            <el-form-item class="btns2">
              <el-button id=""
                         type="info"
                         @click="addPoint">
                添加点
              </el-button>
              <el-button id=""
                         type="info"
                         @click="addLine">
                添加线
              </el-button>
              <el-button id=""
                         type="danger">
                清除
              </el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-card>
      <!-- 查询展示框 -->
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
import baseUrl from '../../mapconfig'

export default {
  name: 'Map',
  data () {
    return {
      map: null,
      search: {
        layer: '',
        fileds: '',
        text: ''
      },
      searchStatus: true,
      searchResult: [],
      layers: [],
      showCardVisible: false,
      point: null
    }
  },
  computed: {
    toogleSearchBtn: function () {
      return this.searchStatus === true ? '收起' : '展开'
    },
    fileds: function () {
      if (this.search.layer == null || this.search.layer === '') { return [] } else {
        return this.layers[parseInt(this.search.layer)].fileds
      }
    }
  },
  methods: {
    spaceSearchClear () {
      console.log(this.map.graphics)
      this.map.graphics.clear()
    },

    searchByAttr (search) {
      let that = this
      loadModules([
        'esri/tasks/FindTask',
        'esri/tasks/FindParameters'
      ])
        .then(
          ([
            FindTask,
            FindParameters
          ]) => {
            console.log('我执行到了searchByAttr()函数')
            console.log('我是searchByAttr()的参数', search)
            // 定义属性查询对象，注意他的参数是整个地图服务，而不是单个图层
            var findTask = new FindTask(baseUrl.baseLayer)
            // 定义属性查询参数对象
            var findParameters = new FindParameters()
            // 是否返回几何信息:如果为“ true”，则结果集包括与每个结果关联的几何
            findParameters.returnGeometry = true
            // 查询的图层,对layerIds进行格式处理：string -> string[]
            findParameters.layerIds = [parseInt(search.layer)]
            // 查询的字段，对fields进行格式处理：string -> string[]
            findParameters.searchFields = [search.fileds]
            // 查询的内容
            findParameters.searchText = search.text
            // 执行属性查询 并传参给展示函数
            findTask.execute(findParameters, that.showQueryResult)
          }
        )
        .catch(err => {
          window.console.log(err)
        })
    },

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
            params.layerIds = [0, 1, 2, 3]
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
          debugger
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

    addPoint () {
      console.log('我被点击了111')
    },

    addLine () {
      console.log('我被点击了222')
    },

    toogleSearch () {
      this.searchStatus = !this.searchStatus
    }

  },
  created () {
    this.layers = baseUrl.layers
    this.baseLayer = baseUrl.baseLayer
  },
  mounted () {
    loadModules(
      [
        'esri/map',
        'esri/geometry/Point',
        'esri/SpatialReference',
        'esri/layers/ArcGISDynamicMapServiceLayer',
        'esri/layers/ArcGISTiledMapServiceLayer',
        'esri/layers/FeatureLayer',
        'esri/dijit/HomeButton',
        'esri/dijit/LocateButton',
        'esri/dijit/Scalebar',
        'esri/toolbars/draw',
        'dojo/domReady!'
      ],
      {
        url: baseUrl.apiUrl,
        css: baseUrl.cssUrl
      }
    )
      .then(
        ([
          Map,
          Point,
          SpatialReference,
          ArcGISDynamicMapServiceLayer,
          ArcGISTiledMapServiceLayer,
          FeatureLayer,
          HomeButton,
          LocateButton,
          Scalebar,
          Draw
        ]) => {
          // 创建底地图图层
          this.map = new Map('mapContainer', {
            logo: false
          })

          // 创建江苏省底图层，并将图层添加到map中
          const baseLayer = new ArcGISTiledMapServiceLayer(baseUrl.baseMap)
          this.map.addLayer(baseLayer)
          const mapcenter = new Point([119.097858, 31.615636], new SpatialReference({ wkid: 4490 }))
          this.map.centerAndZoom(mapcenter, 8)

          // 创建信息图层,并将图层添加到map中
          baseUrl.layers.forEach((layer, index) => {
            console.log(' 创建信息图层,并将图层添加到map中，第', index, '项的layer信息为：', layer)
            var featureLayer = new FeatureLayer(layer.url)
            this.map.addLayer(featureLayer, layer.id)
            console.log('我是featureLayer:', featureLayer)
          })

          // 创建主页按钮
          var homeBtn = new HomeButton({
            map: this.map
          }, 'HomeButton')
          homeBtn.startup()

          // 创建定位按钮
          var locBtn = new LocateButton({
            map: this.map
          }, 'LocateButton')
          locBtn.startup()

          // 创建比例尺控件
          var scaleBar = new Scalebar({
            map: this.map,
            scalebarUnit: 'dual',
            attachTo: 'bottom-left'
          })
          scaleBar.startup()
        })
      .catch(err => {
        window.console.log(err)
      })
  }
}
</script>

<style scoped>
#map {
  height: 90vh;
}
.mapview {
  box-sizing: border-box;
  height: 100%;
}
.search-card {
  position: absolute;
  width: 310px;
  right: 3%;
  top: 5.5%;
  z-index: 10;
  border: 1px solid #dcdfe6;
  border-radius: 10px;
}
.show-card {
  position: absolute;
  width: 600px;
  top: 5.5%;
  right: 3%;
  z-index: 11;
  border: 1px solid #dcdfe6;
  border-radius: 10px;
}
.btns1 {
  margin: 30px 0;
}
.btns2 {
  margin-bottom: 16px;
}
.layer-item {
  width: 250px;
}
.homeButtonContainer {
  position: absolute;
  top: 110px;
  left: 42px;
  z-index: 50;
}
.LocateButtonContainer {
  position: absolute;
  top: 150px;
  left: 42px;
  z-index: 50;
}
.el-divider--horizontal {
  margin: 30px 0;
  background: 0 0;
  border-top: 1px solid #e8eaec;
}
.show-card-divider {
  margin: 24px 0 0 0;
}
</style>
