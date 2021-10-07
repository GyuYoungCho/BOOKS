<template>
  <div class="bestSellerDiv">
    <!-- Library -->
    <h1>this is bestseller page</h1>

    <carousel-3d ref="bestseller" :width="300" :height="400" :display="9" :autoplay="true" :inverseScaling="100" :onMainSlideClick="bookDetail">
      <slide v-for="(slide, i) in slides" :index="i" :key="i" :id="`slide${i}`" style="border-radius: 7px;" >
        <img :src="`${bestsellers[i].cover}`" alt="" @mouseover="hoverCheck()" @mouseout="mouseOutCheck()">
        <!-- <span v-show="isHover" class="title">You know what?</span> -->
        <p>
          {{bestsellers[i].title}}
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
      bestsellers: [],

    };
  },
  beforeCreate() {},
  async created() {
    await this.$store.dispatch('bestseller')
    this.bestsellers = await this.$store.getters['getBestseller']
    console.log(this.bestsellers)
    for (var i=0; i < this.bestsellers.length; i++) {
      this.bestsellers[i].cover = this.bestsellers[i].cover.replace("sum", "500")
    }
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
    bookDetail() {
      console.log(this.$refs.bestseller.currentIndex)
      this.$store.dispatch('bookDetail', this.bestsellers[this.$refs.bestseller.currentIndex].isbn)
      // this.$router.push('/book/bookname')
      
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
