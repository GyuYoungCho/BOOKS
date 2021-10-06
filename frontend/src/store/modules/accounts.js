// import axios from 'axios'
import router from '@/router'
import axios from 'axios'
// import axios from 'axios'


const state = {
  authToken: "",
  loginId: "",
}
const getters = {

}
const mutations = {
  SET_AUTHTOKEN(state, token) {
    state.authToken = token
  },
  SET_ID(state, id) {
    state.loginId = id
  },
  RESET_AUTHTOKEN(state) {
    state.authToken = null
  }
}
const actions = {
  // 가입
  async signUp(context, userInfo) {
    console.log(userInfo)
    // const res = await axios.post('http://localhost:8500/api/user/signup', {params: {id: userInfo[0], password: userInfo[1], nickname: userInfo[2]}} )
    const res = await axios.post('http://localhost:8500/api/user/signup', userInfo, {register: userInfo})
    console.log(res)
  },
  // 로그인
  async signIn(context, loginData) {
    console.log(loginData)
    const res = await axios.post('http://localhost:8500/api/user/login', loginData, {login: loginData})
    console.log(res)
    localStorage.setItem("key", res.data.accessToken)
    context.commit('SET_AUTHTOKEN', localStorage.getItem("key"))
    
    context.commit('SET_ID', res.data.user_id)

    localStorage.setItem("primarykey", res.data.user_id)
    // 로그인 아이디 보내서 저장하기
   //  router.go()
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
    const res = await axios.put(`http://localhost:8500/api/user/category/${info.userId}`, info.category)
    console.log(res)
  }
}

export default {
  state, getters, mutations, actions
}