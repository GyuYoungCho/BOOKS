import Vue from "vue";
import VueRouter from "vue-router";
import MainPage from "../views/MainPage.vue";
import SignUp from "../components/SignUp.vue";
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
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
