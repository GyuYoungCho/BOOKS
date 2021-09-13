<template>
  <div class="mainPage">
    <h1>this is main page</h1>
    <search-bar />
  </div>
</template>

<script>
import SearchBar from '../components/SearchBar.vue';

export default {
  name: "MainPage",
  components: {
    SearchBar,
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
  methods: {},
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
