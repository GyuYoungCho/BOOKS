<template>
  <div @click="popup($event)">
    <Navbar />
    <div class="appDiv">
      <router-view />
    </div>
  </div>
</template>

<script>
import Navbar from "./components/Navbar.vue";

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
  mounted() {
    this.$store.state.tryingLogin = false;
  },
  methods: {
    popup(event) {
      // 클릭한 컴포넌트 정보 찾아서 로그인 컴포넌트라면 작동하게 하고, 아니라면 닫히게 하기.
      // console.log(event.target)
      if (event.target.id === "loginBtn") {
        this.$store.state.tryingLogin = !this.$store.state.tryingLogin;
      } else {
        this.$store.state.tryingLogin = false;
      }
    },
  },
};
</script>

<style scoped>
/* ::v-deep .v-main__wrap {
    background-color: burlywood;
  } */
.appDiv {
  min-width: 600px;
  max-width: 1080px;
  margin: 0 auto;
  margin-top: 50px;
}
</style>
