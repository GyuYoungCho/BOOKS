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
            transform: `rotateY(  ${30 * index}deg) translateZ(430px)`,
            'background-image': `url(${item})`,
          }"
          @click="bookDetail(item.isbn)"
        >
          <span>This is something</span>
          <span>{{ 30 * index }}</span>
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
        "https://images.pexels.com/photos/1394841/pexels-photo-1394841.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "https://images.pexels.com/photos/1394841/pexels-photo-1394841.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "https://images.pexels.com/photos/1394841/pexels-photo-1394841.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        
      ],
    };
  },
  beforeCreate() {},
  async created() {
    await this.$store.dispatch('recommendBook')
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeUnmount() {},
  unmounted() {},
  computed: {},
  methods: {
    bookDetail(isbn) {
      this.$store.dispatch('bookDetail', isbn)
      this.$router.push('/book/bookname')
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../css/recommendbooks.scss";
</style>
