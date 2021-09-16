<template>
  <div>
    <h1>나다 자식들아</h1>
    <sign-in />
  </div>
</template>

<script>
import SignIn from '../components/modal/SignIn.vue';
export default {
  name:'',
  components: {SignIn},
  data() {
    return {
      sampleData: ''
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
  methods: {}
}
</script>

<style scoped>
  div {
    height: 100vh;
  }
  h1 {
    position: absolute;
  }
</style>