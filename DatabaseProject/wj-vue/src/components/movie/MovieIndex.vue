<template>
  <el-container>
    <el-aside style="width: 200px;margin-top: 20px">
      <switch></switch>
      <SideMenu @indexSelect="listByMcategory" ref="sideMenu"></SideMenu>
    </el-aside>
    <el-main>
      <movies class="movies-area" ref="moviesArea"></movies>
    </el-main>
  </el-container>
</template>

<script>
import SideMenu from './SideMenu'
import Movies from './Movies'

export default {
  name: 'AppMovie',
  components: {Movies, SideMenu},
  methods: {
    listByMcategory () {
      var _this = this
      var cid = this.$refs.sideMenu.cid
      var url = 'mcategories/' + cid + '/movies'
      this.$axios.get(url).then(resp => {
        if (resp && resp.data.code === 200) {
          _this.$refs.moviesArea.movies = resp.data.result
          _this.$refs.moviesArea.currentPage = 1
        }
      })
    }
  }
}
</script>

<style scoped>
.movies-area {
  width: 990px;
  margin-left: auto;
  margin-right: auto;
}
</style>
