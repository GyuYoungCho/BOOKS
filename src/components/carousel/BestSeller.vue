<template>
  <div class="bestSellerDiv">
    <!-- Library -->
    <h1>this is bestseller page</h1>

    <carousel-3d :width="200" :height="300" :display="9" :autoplay="true" :autoplayHoverPause="true" :inverseScaling="100" :onMainSlideClick="test">
      <slide v-for="(slide, i) in slides" :index="i" :key="i" :id="`slide${i}`" style="border-radius: 7px;">
        <img src="../../assets/BOOKS-logo.png" alt="">
        <span class="title">You know</span>
        <p>
          {{ i }}
        </p>
      </slide>
    </carousel-3d>
  </div>
</template>

<script>
import { Carousel3d, Slide } from "vue-carousel-3d";

export default {
  name: "",
  components: {
    Carousel3d,
    Slide,
  },
  data() {
    return {
      sampleData: "",
      slides: 20,
    };
  },
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeUnmount() {},
  unmounted() {},
  methods: {
    test() {
      alert('클릭함')
    },
    // clicked(idx) {
    //   console.log(idx)
    //   var em = document.getElementById(`slide${idx}`)
    //   if (window.getComputedStyle(em).zIndex === '999') {
    //     console.log('클릭됨');
    //   }
    // }
  },
};
</script>

<style lang="scss" scoped>
@import "../css/bestseller.scss";
</style>
