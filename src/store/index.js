import Vue from "vue";
import Vuex from "vuex";
// import router from '@/router';

import accounts from './modules/accounts'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    tryingLogin: false,
  },
  mutations: {},
  actions: {
  },
  modules: {
    accounts,
  },
});
