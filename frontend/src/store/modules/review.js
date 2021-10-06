import axios from 'axios'

const state = {

}
const getters = {

}
const mutations = {
  
}
const actions = {
  // 리뷰목록 id?
  async bookReview(context, bookId) {
    const res = await axios.get(`review/${bookId}`)
    console.log(res, context)
  },

  async review(context, content) {
    const resC = await axios.post('review/', { content: content.review, rank: content.rank })
    const resU = await axios.put('review/', { reviewId: content.reviewId, content: content.review, rank: content.rank })
    context.log(resC, resU)
  },

  async deleteReview(context, reviewId) {
    const res = await axios.delete(`review/${reviewId}`)
    console.log(res, context)
  }
}

export default {
  state, getters, mutations, actions
}