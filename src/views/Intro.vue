<template>
  <div class="introPage">
    <div class="intro">
      <h1>
        Let's BookS!<br />
      </h1>
      <button>시작하기</button>
    </div>
    <!-- <sign-in /> -->
  </div>
</template>

<script>
// import SignIn from "../components/modal/SignIn.vue";
export default {
  name: "",
  // components: { SignIn },
  data() {
    return {
      sampleData: "",
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
  methods: {},
};
</script>

<style scoped>
.introPage {
  height: 100vh;
}
.intro {
  position: absolute;
  top: 20%;
  left: 30%;
  transform: translate(-50%, -50%);
}

h1 {
  font-size: 5rem;
  color: white;
}
</style>
