<template>
  <div>
    <h1>if you search books, we will show you this page</h1>
    <div id="columns">
      <figure v-for="(data, index) in books" :key="index">
        <img :src="`${data.coverfilepath}`" @click="bookdetail(data.isbn)"/>
        <figcaption>
          {{ data.title }}
        </figcaption>
      </figure>
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
      books: [
        [
          "//s3-us-west-2.amazonaws.com/s.cdpn.io/4273/cinderella.jpg",
          "Cinderella wearing European fashion of the mid-1860’s",
        ],
        [
          "//s3-us-west-2.amazonaws.com/s.cdpn.io/4273/rapunzel.jpg",
          "Rapunzel, clothed in 1820’s period fashion",
        ],
        [
          "//s3-us-west-2.amazonaws.com/s.cdpn.io/4273/belle.jpg",
          "Belle, based on 1770’s French court fashion",
        ],
        [
          "//s3-us-west-2.amazonaws.com/s.cdpn.io/4273/sleeping-beauty.jpg",
          "Sleeping Beauty, based on European fashions in 1485",
        ],
        [
          "//s3-us-west-2.amazonaws.com/s.cdpn.io/4273/mulan_2.jpg",
          "Mulan, based on the Ming Dynasty period",
        ],
        [
          "//s3-us-west-2.amazonaws.com/s.cdpn.io/4273/sleeping-beauty.jpg",
          "Sleeping Beauty, based on European fashions in 1485",
        ],
        [
          "//s3-us-west-2.amazonaws.com/s.cdpn.io/4273/pocahontas_2.jpg",
          "Pocahontas based on 17th century Powhatan costume",
        ],
        [
          "//s3-us-west-2.amazonaws.com/s.cdpn.io/4273/snow-white.jpg",
          "Snow White, based on 16th century German fashion",
        ],
        [
          "//s3-us-west-2.amazonaws.com/s.cdpn.io/4273/ariel.jpg",
          "Ariel wearing an evening gown of the 1890’s",
        ],
        [
          "//s3-us-west-2.amazonaws.com/s.cdpn.io/4273/tiana.jpg",
          "Tiana wearing the <i>robe de style</i> of the 1920’s",
        ],
      ],
    };
  },
  beforeCreate() {},
  created() {
    this.books = this.$store.state.book.searchBook
    console.log(this.books)
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeUnmount() {},
  unmounted() {},
  methods: {
    bookdetail(isbn) {
      this.$store.dispatch('bookDetail', isbn)
    }
  },
};
</script>

<style lang="scss" scoped>
@import "../css/searchresult.scss";
</style>
