<template>
  <div class="mainPage">
    <h1>this is main page</h1>
    <search-bar />
    <best-seller />
    <recommend-books v-if="isLoggedIn()"></recommend-books>
    <search-result />
  </div>
</template>

<script>
import SearchResult from '../components/card/SearchResult.vue';
import BestSeller from '../components/carousel/BestSeller.vue';
import RecommendBooks from '../components/carousel/RecommendBooks.vue';
import SearchBar from '../components/SearchBar.vue';

export default {
  name: "MainPage",
  components: {
    SearchBar,
    BestSeller,
    RecommendBooks,
    SearchResult,
  },
  data() {
    return {
      sampleData: "",
      isLogin: false,
    };
  },
  beforeCreate() {},
  created() {
    const token = localStorage.getItem("token");
    if (token) {
      this.isLogin = true;
    }
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeUnmount() {},
  unmounted() {},
  methods: {
    isLoggedIn() {
      if (localStorage.getItem("key")) {
        return true
      }
      return true
    }
  },
};
</script>

<style scoped>
  .mainPage {
    max-width: 1080px;
    margin: 0 auto;
    margin-top: 48px;
    text-align: center;
  }
</style>
