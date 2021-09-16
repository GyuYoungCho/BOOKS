<template>
  <v-app>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-text-field
        v-model="id"
        :counter="10"
        :rules="idRules"
        label="아이디"
        required
      ></v-text-field>
      <v-btn color="success" class="mr-4" @click="validate"> 중복 확인 </v-btn>

      <v-text-field
        v-model="password"
        :rules="passwordRules"
        :type="'password'"
        label="비밀번호"
        required
      ></v-text-field>

      <v-text-field
        v-model="passwordChk"
        :rules="passwordRules"
        :type="'password'"
        label="비밀번호 확인"
        required
      ></v-text-field>

      <v-text-field
        v-model="name"
        :rules="nameRules"
        label="별명"
        required
      ></v-text-field>
      <v-btn color="success" class="mr-4" @click="validate"> 중복 확인 </v-btn>
    </v-form>

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
      회원가입 완료
    </v-btn>
  </v-app>
</template>
<script>
export default {
  components: {},
  data: () => ({
    valid: true,
    id: "",
    password: "",
    passwordChk: "",
    name: "",

    idRules: [
      (v) => !!v || "아이디를 입력해주세요.",
      (v) => (v && v.length <= 10) || "Name must be less than 10 characters",
    ],

    emailRules: [
      (v) => !!v || "E-mail is required",
      (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
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
    validate() {
      this.$refs.form.validate();
    },
  },
};
</script>

<style></style>
