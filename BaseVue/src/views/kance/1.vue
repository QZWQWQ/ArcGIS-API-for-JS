<template>
  <div>
    <div>父组件的City值{{City}}</div>
    <city @showCityName="updateCity"
          :sendData="City"></city>
  </div>
</template>

<script>
import City from './2'
export default {
  components: City,
  data () {
    return {
      City: '北京'
    }
  },
  methods: {
    updateCity (data) {
      this.City = data.cityname
    }
  }

}
</script>

<style>
</style>