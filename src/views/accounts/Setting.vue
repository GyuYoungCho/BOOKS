<template>
  <div>
    <div>검색 키워드 관리</div>
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
      {{ selected }}
    </v-card>

    <v-btn :disabled="!valid" color="success" class="mr-4" @click="validate">
      설정 완료
    </v-btn>
  </div>
</template>
<script>
export default {
  components: {},
  data: () => ({
    chips: [
      "Programming",
      "Playing video games",
      "Watching movies",
      "Sleeping",
    ],
    selected: [],
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
  created() {},
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
  },
};
</script>

<style></style>
