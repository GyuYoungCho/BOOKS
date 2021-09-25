<template>
  <div class="bestSellerDiv">
    <!-- Library -->
    <h1>this is bestseller page</h1>

    <carousel-3d :width="300" :height="400" :display="9" :autoplay="true" :inverseScaling="100" :onMainSlideClick="test">
      <slide v-for="(slide, i) in slides" :index="i" :key="i" :id="`slide${i}`" style="border-radius: 7px;" >
        <img src="../../assets/BOOKS-logo.png" alt="" @mouseover="hoverCheck()" @mouseout="mouseOutCheck()">
        <span v-show="isHover" class="title">You know what?</span>
        <p>
          {{ i }}, 도서명
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
      isHover:false,
      slides: 20,
    };
  },
  beforeCreate() {},
  async created() {
    // await axios.get('/main/best')
      // .then()
  },
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
    // },
    hoverCheck() {
      this.isHover = true;
    },
    mouseOutCheck() {
      this.isHover = false;
    }
  },
};
</script>

<style lang="scss" scoped>
@import "../css/bestseller.scss";
</style>
