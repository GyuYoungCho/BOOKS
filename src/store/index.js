import Vue from "vue";
import Vuex from "vuex";
// import router from '@/router';

import accounts from './modules/accounts'
import book from './modules/book'
import main from './modules/main'
import category from './modules/category'
import review from './modules/review'
import keyword from './modules/keyword'

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
    book,
    main,
    category,
    review,
    keyword
  },
});
