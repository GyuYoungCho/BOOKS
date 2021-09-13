<template>
  <div>
    <nav>
      <ul class="nav-container">
        <div class="nav-left">
          <li class="nav-item nav-logo">
            <router-link to="/main">
              <img class="logo" src="../assets/imsi.png" alt="Sopoong logo" />
            </router-link>
          </li>
          <router-link to="/main" class="text-logo"> BOOKS </router-link>
          <li class="nav-item nav-newsfeed">
            <router-link to="/feed">NewsFeed</router-link>
          </li>
          <li
            v-if="isSignedIn"
            class="nav-item nav-followfeed"
            @click="toFollowFeed()"
          >
            FollowFeed
          </li>
        </div>
        <div class="nav-right">
          <div class="search-bar" id="search-bar">
            <input
              type="search"
              v-model="searchWord"
              @keydown.enter="onEnter"
            />
          </div>
          <div v-if="!isSignedIn" class="nav-right">
            <li class="nav-item nav-login">
              <router-link to="/signin">Login</router-link>
            </li>
            <li class="nav-item nav-signup">
              <router-link to="/signup">Signup</router-link>
            </li>
          </div>
          <div v-else class="nav-right">
            <li class="nav-item nav-login" @click="signOut">Logout</li>
          </div>
        </div>
      </ul>
    </nav>
  </div>
</template>

<script>
import "@/components/css/navbar.scss";

export default {
  name: "Navbar",
  components: {},
  data() {
    return {
      sampleData: "",
      dialog: false,
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

<style></style>
