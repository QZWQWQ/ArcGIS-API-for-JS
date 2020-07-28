const baseUrl = {
  // API接口
  apiUrl: 'https://js.arcgis.com/3.32/',
  // CSS样式
  cssUrl: 'https://js.arcgis.com/3.32/esri/css/esri.css',
  // 江苏省底地图图层
  baseMap: 'http://218.94.6.92:6080/arcgis/rest/services/jspub_raster_L4_L20/MapServer',
  // 底层信息图层
  baseLayer: 'http://59.83.223.39:6080/arcgis/rest/services/lishuiqu/lslyfqgq_new/MapServer',
  // 信息图层
  layers: [
    {
      id: 0,
      title: '0号图层',
      fileds: [
        'GQBM',
        'GQMC'
      ],
      url: 'http://59.83.223.39:6080/arcgis/rest/services/lishuiqu/lslyfqgq_new/MapServer/0'
    },
    {
      id: 1,
      title: '1号图层',
      fileds: [
        'GQBM',
        'GQMC'
      ],
      url: 'http://59.83.223.39:6080/arcgis/rest/services/lishuiqu/lslyfqgq_new/MapServer/1'
    },
    {
      id: 2,
      title: '2号图层',
      fileds: [
        'GQBM',
        'GQMC'
      ],
      url: 'http://59.83.223.39:6080/arcgis/rest/services/lishuiqu/lslyfqgq_new/MapServer/2'
    },
    {
      id: 3,
      title: '3号图层',
      fileds: [
        'FID',
        'Shape',
        'ID',
        'NAME',
        'SOURCE',
        'GDB_FROM_D',
        'GDB_TO_DAT',
        'SERIAL',
        'FSTNAME',
        'SPID',
        'SPNAME',
        'DATAFROM',
        'RNAME',
        'NAME2',
        'REGION',
        'STARTEND',
        'CHANLEN',
        'VALLEY',
        'FLUX',
        'TRAVERSE',
        'INFIELD',
        'POPU',
        'FUNCTION',
        'SGRADE',
        'KIND',
        'GRADE',
        'COMEOUT',
        'SUBJECTION',
        'SCALE',
        'LABEL',
        'LYBM',
        'SHAPE_AREA',
        'SHAPE_LEN',
        'FID_1',
        'OBJECTID',
        'GQBM',
        'GQMC',
        'SPBM',
        'SPMC',
        'SHAPE_Leng',
        'SHAPE_Ar_1',
        'id_1',
        'NMAE',
        'LOCATION',
        'LENGTH',
        'TOTALAERA',
        'TERRITAREA',
        'RIVERB',
        'RIVERW',
        'RIVERL',
        'LEFTBANKL',
        'LEFTBANKH',
        'LEFTBANKW',
        'RIGHTBANKL',
        'RIGHTBANKH',
        'RIGHTBANKW',
        'SSLY',
        'PUMPFLOW'
      ],
      url: 'http://59.83.223.39:6080/arcgis/rest/services/lishuiqu/lslyfqgq_new/MapServer/3'
    }
  ]
}
export default baseUrl
