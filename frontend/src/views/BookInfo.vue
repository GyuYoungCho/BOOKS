<template>
  <div class="bookInfoPage">
    <div class="bookMainInfo">
      <h1>{{bookData.title}}</h1>
      <p>{{bookData.author}},{{bookData.pubDate}}, {{bookData.isbn13}}</p>
      <hr>
    </div>
    <div style="display:flex; flex-direction:row; justify-content:space-between" >
      <img :src="`${bookData.coverFilepath}`" alt="이미지없음">
      <div class="bookInfo">
        <ul>
          <li style="list-style:none;">
            <div style="position:absolute; width: 100px;"> 
              테스트
            </div>
            <div style="margin-left: 100px">
              안녕하세요 여러분?
            </div>
          </li>
        </ul>
        <p>{{bookData.description}}</p>
        <br>
        <p>회원리뷰평점</p>
        <br>
        <p>책소개(api 제공 받을 수 있는 경우에?)</p>
        <br>
        <p>카테고리 분류</p>
        <br>
        <p>상품페이지 URL 주소</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name:'',
  components: {},
  data() {
    return {
      bookData: [],
    };
  },
  beforeCreate() {},
  created() {
    console.log('접근체크')
    this.bookData = this.$store.getters['getBookDetail']
    this.bookData.coverFilepath = this.bookData.coverFilepath.replace("sum","500")
    console.log(this.bookData)
  }, 
  beforeMount() {}, 
  mounted() {}, 
  beforeUpdate() {},
  updated() {
  },
  beforeUnmount() {}, 
  unmounted() {},
  methods: {}
}
</script>

<style scoped>
.bookInfoPage {
  max-width: 1080px;
  margin: 0 auto;
  margin-top: 48px;
  text-align: left;
}

.bookMainInfo {
  margin-bottom: 20px;
}

img {
  width: 500px;
  height: 600px;
  object-fit: cover;
  /* margin-right: 20px; */
  /* border: 1px solid; */
}

.bookInfo {
  width: 500px;
}
</style>