import Vue from "vue";
import VueRouter from "vue-router";
import MainPage from "../views/MainPage.vue";
import SignUp from "../views/accounts/SignUp.vue";
import Setting from "../views/accounts/Setting.vue";
import BookInfo from "../views/BookInfo.vue";

// import SignIn from "../components/modal/SignIn.vue";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "MainPage",
    component: MainPage,
    children: [
      // {
      //   path: "/signin",
      //   name: "SignIn",
      //   component: SignIn,
      // },
    ],
  },
  {
    path: "/signup",
    name: "SignUp",
    component: SignUp,
  },
  {
    // 동적 라우팅
    path: "/:id/setting",
    name: "Setting",
    component: Setting,
  },

  // 책 상세정보
  {
    path: "/book/:isbn",
    name: "BookInfo",
    component: BookInfo,
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
