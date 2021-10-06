import axios from 'axios'
import router from '../../router'

const state = {
  searchBook: null,
  bookDetail: null,
}
const getters = {
  getSearchBook: state => state.searchBook,
  getBookDetail: state => state.bookDetail,
}
const mutations = {
  SET_SEARCHBOOK(state, res) {
    state.searchBook = res
  },
  SET_BOOKDETAIL(state, res) {
    state.bookDetail = res
  }
}
const actions = {
  // 책 검색
  async searchBook(context, keyword) {
    console.log(context.rootState)
    const res = await axios.get('http://localhost:8082/book/search/', { params: {keyword : keyword, userId: localStorage.getItem('id') }, headers: {}  })
    console.log(res)
    context.commit('SET_SEARCHBOOK', res.data.content)
    router.go()
  },

  // 유저 책 검색 목록
  async userBookList(context) {
    const res = await axios.get(`book/list/${context.rootState.loginId}`, { params: { userId : context.rootState.loginId } })
    console.log(res)
  },

  // 북 상세 + isbn은 어디서?
  async bookDetail(context, isbn) {
    if (localStorage.getItem("primarykey")) {
      const res = await axios.get(`http://localhost:8082/book/detail/${isbn}/${localStorage.getItem("primarykey")}`)
      console.log(res)
      context.commit('SET_BOOKDETAIL', res.data)
      router.push(`/book/${isbn}`)
    }
    else {
      const res = await axios.get(`http://localhost:8082/book/detail/${isbn}/0`)
      console.log(res)
      context.commit('SET_BOOKDETAIL', res.data)
    }
  }
}

export default {
  state, getters, mutations, actions
}