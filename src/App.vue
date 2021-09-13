<template>
  <div>
    <Navbar />
    <div class="appDiv">
      <router-view />
    </div>
  </div>
</template>

<script>
import Navbar from './components/Navbar.vue';

export default {
  name: "App",
  components: {
    Navbar,
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
  .appDiv {
    max-width: 1080px;
    margin: 0 auto;
    margin-top: 48px;
  }
</style>