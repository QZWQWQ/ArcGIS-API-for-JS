<template>
  <div>
    <div>

      <iframe width="320"
              scrolling="no"
              height="80px"
              frameborder="0"
              allowtransparency="true"
              z-index=99
              src="http://i.tianqi.com/index.php?c=code&id=2&icon=1&py=nanjing&num=2&site=12"></iframe>

    </div>

    <div>
      <iframe id=""
              name="weather_inc"
              src="http://typhoon.weather.com.cn/gis/typhoon_full.shtml"
              width="100%"
              height="900px"
              frameborder="0"
              marginwidth="0"
              marginheight="0"
              scrolling="no"
              style="z-index:50"></iframe>
    </div>
  </div>
</template>
<style lang="scss" scoped>
.weather {
  margin: 0;
  box-sizing: border-box;
  margin-top: 0px;
  margin-left: 40%;
  width: 300px;
  height: 100px;
  z-index: 9999;
  border: 5px solid #dc143c;
}
</style>
<script>
// 在页面中引入高德地图
export default {
  components: {
  },
  data () {
    return {
      data: {}
    }
  },
  methods: {

  }
}
</script>