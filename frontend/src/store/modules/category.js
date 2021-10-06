import axios from 'axios'
// import router from '@/router'

const state = {

}
const getters = {

}
const mutations = {

}
const actions = {
  // 왜 필요하지 이거??
  async categoryList(context) {
    const res = await axios.get('category/list')
    context
    console.log(res)
  },
  async categorySearch(context, keyword) {
    context
    const res = await axios.get(`category/search?${keyword}`)
    console.log(res)
  }
}

export default {
  state, getters, mutations, actions
}