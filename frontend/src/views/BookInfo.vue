<template>
  <div class="bookInfoPage">
    <div class="bookMainInfo">
      <h1>{{ bookData.title }}</h1>
      <p>{{ bookData.author }}</p>
      <hr>
    </div>
    <div style="display:flex; flex-direction:row; justify-content:space-between" >
      <img :src="`${bookData.coverFilepath}`" alt="이미지없음">
      <div class="bookInfo">
        <ul>
          <li style="list-style:none; margin-bottom: 10px">
            <div style="position:absolute; width: 100px;"> 
              출판사
            </div>
            <div style="margin-left: 100px">
              {{ bookData.publisher }}
            </div>
          </li>
          <li style="list-style:none; margin-bottom: 10px">
            <div style="position:absolute; width: 100px;"> 
              ISBN 번호
            </div>
            <div style="margin-left: 100px">
              {{ bookData.isbn13 }}
            </div>
          </li>
          <li style="list-style:none; margin-bottom: 10px">
            <div style="position:absolute; width: 100px;"> 
              출간일
            </div>
            <div style="margin-left: 100px">
              {{ bookData.pubDate }}
            </div>
          </li>
        </ul>
        <p>{{bookData.description}}</p>
        <br>
        <a :href="`${bookData.link}`">알라딘으로!</a>
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
  object-fit: contain;
  /* margin-right: 20px; */
  /* border: 1px solid; */
}

.bookInfo {
  width: 500px;
}

h1 {
  font-size: 2.5rem;
}

li {
  font-size: 1.3rem;
}
</style>