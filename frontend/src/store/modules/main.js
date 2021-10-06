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
    const res = await axios.get('main/best/')
    console.log(res, context)
    context.commit('SET_BESTSELLER', res)
  },
  async recommendBook(context) {
    const res = await axios.get(`main/recommend/${context.rootState.loginId}`)
    console.log(res)
  },
}

export default {
  state, getters, mutations, actions
}