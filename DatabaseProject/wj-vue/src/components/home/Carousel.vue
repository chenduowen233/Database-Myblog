<template>
  <el-card class="card-carousel">
    <div style="font-size: 30px;color: #1b1f23;white-space: nowrap">为世间美好而战</div>
    <div style="font-size: 30px;color: #1b1f23;white-space: nowrap">☆</div>
  <el-carousel  class="push" :interval="4000" arrow="always">
    <el-carousel-item v-for="item in items" :key="item.id">
      <a :href=item.link target="_blank">
        <img :src=item.img alt="" class="carousel_img">
      </a>
      <h3>{{item.title}}</h3>
    </el-carousel-item>
  </el-carousel>
  </el-card>
</template>

<style>
  .el-carousel__item h3 {
    color: black;
    font-size: 15px;
    /*line-height: 300px;*/
    bottom: 0px;
    float: left;
    position: relative;
  }

  .card-carousel {
    /*margin: 0 auto;*/
    margin-left: 0px;
    width: 975px;
    height: 450px;
  }

  .push {
    /*margin-left: -15px;*/
  }

  .el-carousel__item:nth-child(2n) {
    background: black;
  }

  .el-carousel__item:nth-child(2n+1) {
    background: black;
  }

  .carousel_img {
    height: 120%;
    width: 100%;
    text-align: center;
  }
</style>

<script>
  export default {
    name: 'Carousel',
    data: function () {
      return {
        items: [{
          id: 1,

          img: 'https://cdn.jsdelivr.net/gh/chenduowen233/Myblog@main/img/wallhaven-x8j8ol.jpg',
          link: 'https://cdn.jsdelivr.net/gh/chenduowen233/Myblog@main/img/wallhaven-x8j8ol.jpg'},
        {
          id: 2,

          img: 'https://cdn.jsdelivr.net/gh/chenduowen233/Myblog@main/img/i123.jpg',
          link: 'https://cdn.jsdelivr.net/gh/chenduowen233/Myblog@main/img/i123.jpg'
        },
        {
          id: 3,

          img: 'https://cdn.jsdelivr.net/gh/chenduowen233/Myblog@main/img/2197682512.jpg',
          link: 'https://cdn.jsdelivr.net/gh/chenduowen233/Myblog@main/img/2197682512.jpg'
        }]
      }
    }
  }
</script>
