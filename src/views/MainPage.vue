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
<<<<<<< HEAD
import SearchResult from '../components/card/SearchResult.vue';
import BestSeller from '../components/carousel/BestSeller.vue';
import RecommendBooks from '../components/carousel/RecommendBooks.vue';
import SearchBar from '../components/SearchBar.vue';
=======
import BestSeller from "../components/carousel/BestSeller.vue";
import RecommendBooks from "../components/carousel/RecommendBooks.vue";
import SearchBar from "../components/SearchBar.vue";
>>>>>>> 204269853d16012e014c2a4fd8be30b41590f628

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
