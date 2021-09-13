<template>
  <div>
    <Navbar />
    <MainPage />
  </div>
</template>

<script>
import Navbar from './components/Navbar.vue';
import MainPage from './views/MainPage.vue';

export default {
  name: "App",
  components: {
    Navbar,
    MainPage,
  },
  data: () => ({
    sampleData: false,
    //
  }),
  created() {
    if (process.env.NODE_ENV === "development") {
      console.log("on develop");
    }
  },
  methods: {
  },
};
</script>

<style scoped>
  /* ::v-deep .v-main__wrap {
    background-color: burlywood;
  } */
</style>