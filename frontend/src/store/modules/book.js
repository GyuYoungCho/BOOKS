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
    context.commit('SET_SEARCHBOOK', res)
    router.go()
  },

  // 유저 책 검색 목록
  async userBookList(context) {
    const res = await axios.get(`book/list/${context.rootState.loginId}`, { params: { userId : context.rootState.loginId } })
    console.log(res)
  },

  // 북 상세 + isbn은 어디서?
  async bookDetail(context, isbn) {
    if (context.rootState.loginId) {
      const res = await axios.get(`book/detail/${isbn}/${context.rootState.loginId}`)
      console.log(res)
      context.commit('SET_BOOKDETAIL', res)
    }
    else {
      const res = await axios.get(`book/detail/${isbn}/0`)
      console.log(res)
      context.commit('SET_BOOKDETAIL', res)
    }
  }
}

export default {
  state, getters, mutations, actions
}