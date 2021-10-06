import axios from 'axios'

const state = {
  bestseller: null,
  recommendBook: null,
}
const getters = {
  getBestseller(state) {
    return state.bestseller
  },
  getRecommendBook(state) {
    return state.recommendBook
  }
}
const mutations = {
  SET_BESTSELLER(state, res) {
    state.bestseller = res
  },
  SET_RECOMMENDBOOK(state, res) {
    state.recommendBook = res
  }
}
const actions = {
  async bestseller(context) {
    const res = await axios.get('http://127.0.0.1:8000/main/best')
    console.log(res.data)
    context.commit('SET_BESTSELLER', res.data)
  },
  async recommendBook(context) {
    const res = await axios.get(`http://127.0.0.1:8000/main/recommend/${localStorage.getItem("primarykey")}`)
    console.log(res, context)
  },
}

export default {
  state, getters, mutations, actions
}