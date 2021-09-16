<template>
  <div ref="appDiv" @click="popup($event)">
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
    };
  },
  // Dom이 생성된 Mounted에서 태그 관련 터치를 진행해야 한다.
  mounted() {
    this.$store.state.tryingLogin = false;
    if (this.$router.currentRoute.name === 'Intro') {
      this.$refs.appDiv.style.backgroundImage = "url('https://images.unsplash.com/photo-1481627834876-b7833e8f5570?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1600&q=80')"
      console.log(this.$refs.appDiv.style.backgroundImage)
      this.$refs.appDiv.style.backgroundSize= "cover"
    }
    

  },
  methods: {
    popup(event) {
      // 클릭한 컴포넌트 정보 찾아서 로그인 컴포넌트라면 작동하게 하고, 아니라면 닫히게 하기.
      if (event.target.id === "loginBtn") {
        this.$store.state.tryingLogin = !this.$store.state.tryingLogin;
      } else if (
        event.target.id === "signInDiv" ||
        event.target.id === "signInDivBody" ||
        event.target.id === "id" ||
        event.target.id === "password" ||
        event.target.id === "loginLogo"
      ) {
        console.log();
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
  /* min-width: 600px;
  max-width: 1080px; */
  margin: 0 auto;
  /* margin-top: 60px; */
}
</style>
