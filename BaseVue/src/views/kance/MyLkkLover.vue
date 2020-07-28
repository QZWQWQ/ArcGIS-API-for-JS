<template>
  <div>
    <h3>子组件</h3>
  </div>
</template>

<script>
export default {
  // props: {
  //   parentMessage: {
  //     type: String,
  //     default: '默认显示的信息'
  //   }
  // }
  mounted () {
    this.$emit('childEvent', { name: 'linkeke', age: 18 })
  }

}
</script>

<style>
</style>