<template>
  <div class="recommendBooksDiv">
    <h1>this is RecommendBooks Page</h1>
    <div class="container">
      <div class="carousel">
        <!-- rotateY x(컴포 개수) x = 360/deg -->
        <div
          v-for="(item, index) in items"
          :key="index"
          class="carousel__face"
          :style="{
            transform: `rotateY(  ${40 * index}deg) translateZ(430px)`,
            'background-image': `url(${item})`,
          }"
        >
          <span @click="clicked()">This is something</span>
          <span>{{ 40 * index }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "",
  components: {},
  data() {
    return {
      sampleData: "",
      items: [
        "https://images.pexels.com/photos/1141853/pexels-photo-1141853.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "https://images.pexels.com/photos/1258865/pexels-photo-1258865.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "https://images.pexels.com/photos/808466/pexels-photo-808466.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "https://images.pexels.com/photos/1394841/pexels-photo-1394841.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "https://images.pexels.com/photos/1394841/pexels-photo-1394841.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "https://images.pexels.com/photos/1394841/pexels-photo-1394841.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "https://images.pexels.com/photos/1394841/pexels-photo-1394841.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "https://images.pexels.com/photos/1394841/pexels-photo-1394841.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "https://images.pexels.com/photos/1394841/pexels-photo-1394841.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        
      ],
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
  computed: {},
  methods: {
    clicked() {
      alert("클릭하셨습니다!");
    },
  },
};
</script>

<style scoped>
.recommendBooksDiv {
  height: 560px;
}

.container {
  position: relative;
  width: 320px;
  /* margin: 100px auto 0 auto; */
  perspective: 1000px;
}

.carousel {
  position: absolute;
  width: 100%;
  height: 100%;
  transform-style: preserve-3d;
  /* 도는 속도 */
  animation: rotate360 25s infinite forwards linear;
}
.carousel__face {
  position: absolute;
  width: 300px;
  height: 187px;
  top: 20px;
  left: 0px;
  right: 0px;
  background-size: cover;
  box-shadow: inset 0 0 0 2000px rgba(0, 0, 0, 0.5);
  display: flex;
  border-radius: 10px;
}

span {
  margin: auto;
  font-size: 2rem;
}

@keyframes rotate360 {
  from {
    transform: rotateY(0deg);
  }
  to {
    transform: rotateY(-360deg);
  }
}
</style>
