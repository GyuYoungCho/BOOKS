<template>
  <div class="settingPage">
    <div>관심 도서 관리</div>
    <v-combobox
      v-model="chips"
      chips
      clearable
      label="Your favorite hobbies"
      multiple
      solo
    >
      <template v-slot:selection="{ attrs, item, select, selected }">
        <v-chip
          v-bind="attrs"
          :input-value="selected"
          close
          @click="select"
          @click:close="remove(item)"
        >
          <strong>{{ item }}</strong
          >&nbsp;
        </v-chip>
      </template>
    </v-combobox>

    <v-card class="mx-auto">
      <v-card-text>
        <h2 class="text-h6 mb-2">관심있는 해시태그를 선택해 주세요.</h2>

        <v-chip-group v-model="selected" column multiple>
          <v-chip v-for="hashtag in hashtags" :key="hashtag">
            {{ hashtag }}
          </v-chip>
        </v-chip-group>
      </v-card-text>
      
    </v-card>

    <v-btn color="success" class="mr-4" @click="add">
      설정 완료
    </v-btn>
  </div>
</template>
<script>
import axios from 'axios';
export default {
  components: {},
  data: () => ({
    rawChips: [

    ],
    chips: [
      
    ],
    selected: [1, 3],
    hashtags: [
      "가정/요리/뷰티",
      "건강/취미/레저",
      "경제경영",
      "고등학교참고서",
      "고전",
      "과학",
      "달력/기타",
      "대학교재/전문서적",
      "만화",
      "사회과학",
      "소설/시/희곡",
      "수험서/자격증",
      "어린이",
      "에세이",
      "여행",
      "역사",
      "예술/대중문화",
      "외국어",
      "유아",
      "인문학",
      "자기계발",
      "잡지",
      "장르소설",
      "전집/중고전집",
      "종교/역학",
      "좋은부모",
      "중학교참고서",
      "청소년",
      "초등학교참고서",
      "컴퓨터/모바일",
    ],
  }),
  beforeCreate() {},
  async created() {
    if ( !localStorage.getItem('primarykey') ) {
      this.$router.push('/')
    }
    this.$store.dispatch('userBookList')

    const res = await axios.get(`http://localhost:8500/api/category/${localStorage.getItem('primarykey')}`, {params: {user_id: localStorage.getItem('primarykey')}})
    this.selected = []
    for (var j=0; j<res.data.userCategory.length;j++) {
      this.selected.push(res.data.userCategory[j].categoryId)
    }
    
    this.rawChips = this.$store.getters['getUserBookList']
    for ( var i = 0; i < this.rawChips.length; i++) {
      if ( this.chips.indexOf(this.rawChips[i].title) !== -1 ) {
        console.log()
      }
      else {
        this.chips.push(this.rawChips[i].title)
      }
    }
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeUnmount() {},
  unmounted() {},

  computed: {},

  methods: {
    remove(item) {
      this.chips.splice(this.chips.indexOf(item), 1);
      console.log(this.chips)
      this.chips = [...this.chips];
    },
    async add() {
      await axios.get(`http://localhost:8500/api/category/delete/${localStorage.getItem('primarykey')}`,{user_id: localStorage.getItem('primarykey')})
      for (var i=0; i < this.selected.length; i++) {
        // const res = await axios.post(`http://localhost:8500/api/category/add`, {tag: this.selected[i], user_id: localStorage.getItem('primarykey')})
        const res = await axios.post(`http://localhost:8500/api/category/add`, {tag: this.selected[i], user_id: localStorage.getItem('primarykey')})
        res
        this.$router.push('/')
      }
    }
  },
};
</script>

<style scoped>
.settingPage {
  max-width: 1080px;
  margin: 0 auto;
  margin-top: 48px;
  text-align: center;
}
</style>
