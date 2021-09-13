<template>
  <div>
    <nav>
      <ul class="nav-container">
        <div class="nav-left">
          <li class="nav-item nav-logo">
            <router-link to="/">
              <img class="logo" src="../assets/imsi.png" alt="Sopoong logo" />
            </router-link>
          </li>
          <router-link to="/" class="text-logo"> BOOKS </router-link>
        </div>
        <div class="nav-right">
          <!-- <div class="search-bar" id="search-bar">
            <input
              type="search"
              v-model="searchWord"
              @keydown.enter="onEnter"
            />
          </div> -->
          <div class="nav-right">
            <li class="nav-item nav-login">
              <a id="loginBtn">Login</a>
              <sign-in v-show="this.$store.state.tryingLogin"></sign-in>
            </li>
            <li class="nav-item nav-signup">
              <router-link to="/signup">Signup</router-link>
            </li>
          </div>
          <div class="nav-right">
            <li class="nav-item nav-login">Logout</li>
          </div>
        </div>
      </ul>
    </nav>
  </div>
</template>

<script>
import "@/components/css/navbar.scss";
import SignIn from "./modal/SignIn.vue";

export default {
  name: "Navbar",
  components: { SignIn },
  data() {
    return {
      dialog: false,
    };
  },
  beforeCreate() {},
  created() {
  },
  beforeMount() {},
  mounted() {
  },
  beforeUpdate() {},
  updated() {},
  beforeUnmount() {},
  unmounted() {},
  methods: {
  },
};
</script>

<style scoped>
.nav-container {
  min-width: 600px;
}
</style>
