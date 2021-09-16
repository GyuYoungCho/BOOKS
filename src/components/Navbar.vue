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
          <div class="nav-right" v-if="!isLoggedIn()">
            <li class="nav-item nav-login">
              <a id="loginBtn">Login</a>
              <sign-in v-show="this.$store.state.tryingLogin"></sign-in>
            </li>
            <li class="nav-item nav-signup">
              <router-link to="/signup">Signup</router-link>
            </li>
          </div>
          <div class="nav-right" v-if="isLoggedIn()">
            <li class="nav-item nav-mypage">
              <router-link :to="`/${id}/setting`">Mypage</router-link>
            </li>
            <li class="nav-item nav-login">
              <router-link to="/" @click="logout()">Logout</router-link>
            </li>
          </div>
        </div>
      </ul>
    </nav>
  </div>
</template>

<script>
import "@/components/css/navbar.scss";
import SignIn from "./modal/SignIn.vue";
import { mapActions } from "vuex";

export default {
  name: "Navbar",
  components: { SignIn },
  data() {
    return {
      dialog: false,
      id: '동적라우팅',
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
  methods: {
    ...mapActions(["logout"]),
    isLoggedIn() {
      localStorage.setItem("key", "123")
      if (localStorage.getItem("key")) {
        console.log("있음");
        return true;
      } else {
        console.log(localStorage.getItem("key"));
        return false;
      }
    },
  },
};
</script>

<style scoped>
.nav-container {
  min-width: 600px;
}
</style>
