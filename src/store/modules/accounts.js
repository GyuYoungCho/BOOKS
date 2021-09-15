// import axios from 'axios'
import router from '@/router'


const state = {

}
const getters = {

}
const mutations = {

}
const actions = {
  logout(context) {
    localStorage.removeItem("key")
    console.log(context, this.$router)
    router.go()
  }
}

export default {
  state, getters, mutations, actions
}