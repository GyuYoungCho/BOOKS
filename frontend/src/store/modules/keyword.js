import axios from 'axios'

const state = {
  searchedKeyword: null,
}
const getters = {
  getSearchedKeyword: state => state.searchedKeyword,
}
const mutations = {
  SET_SEARCHEDKEYWORD(state, res) {
    state.searchedKeyword = res
  }
}
const actions = {
  async searchedKeyword(context) {
    const res = await axios.get(`/keyword/list/${context.rootState.loginId}`)
    console.log(res)
    context.commit('SET_SEARCHEDKEYWORD', res)

  },
  
  async deleteKeyword(context, keyword) {
    const res = await axios.delete('keyword/',{ userId : context.rootState.loginId, keyword : keyword })
    console.log(res)

  }
}

export default {
  state, getters, mutations, actions
}