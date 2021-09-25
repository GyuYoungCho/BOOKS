// import axios from 'axios'
import router from '@/router'
import axios from 'axios'
// import axios from 'axios'


const state = {
  authToken: "",
}
const getters = {

}
const mutations = {
  SET_AUTHTOKEN(state, token) {
    state.authToken = token
  },
  RESET_AUTHTOKEN(state) {
    state.authToken = null
  }
}
const actions = {
  // 가입
  async signUp(context, userInfo) {
    const res = await axios.get('user/reg', {params: userInfo} )
    console.log(res)
  },
  // 로그인
  async signIn(context, loginData) {
    const res = await axios.post('/user/login', loginData, {login: loginData})
    console.log(res)
    localStorage.setItem("key", res.data)
    context.commit('SET_AUTHTOKEN', localStorage.getItem("key"))
    router.push('/')
  },
  // 로그아웃
  logout(context) {
    localStorage.removeItem("key")
    context.commit('RESET_AUTHTOKEN')
    console.log(context, this.$router)
    router.go()
  },
  // 관심분야 설정
  async setCategory(context, info) {
    const res = await axios.put(`user/category/${info.userId}`, info.category)
    console.log(res)
  }
}

export default {
  state, getters, mutations, actions
}