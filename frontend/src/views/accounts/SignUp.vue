<template>
  <v-app class="signUpPage">
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-text-field
        v-model="signUpData.id"
        :counter="10"
        :rules="idRules"
        label="아이디"
        required
      ></v-text-field>
      <v-btn color="success" class="mr-4" @click="idCheck(signUpData.id)"> 중복 확인 </v-btn>

      <v-text-field
        v-model="signUpData.password"
        :type="'password'"
        label="비밀번호"
        required
      ></v-text-field>

      <v-text-field
        v-model="passwordChk"
        :type="'password'"
        label="비밀번호 확인"
        required
      ></v-text-field>

      <v-text-field
        v-model="signUpData.nickname"
        label="별명"
        required
      ></v-text-field>
      <v-btn color="success" class="mr-4" @click="nickNameCheck(signUpData.nickname)"> 중복 확인 </v-btn>
    </v-form>

    <v-card class="mx-auto">
      <v-card-text>
        <h2 class="text-h6 mb-2">관심있는 해시태그를 선택해 주세요.</h2>

        <v-chip-group v-model="signUpData.hashtag" column multiple>
          <v-chip v-for="hashtag in hashtags" :key="hashtag">
            {{ hashtag }}
          </v-chip>
        </v-chip-group>
      </v-card-text>
      {{ signUpData.hashtag }}
    </v-card>

    <v-btn :disabled="!valid" color="success" class="mr-4" @click="signUp(signUpData)">
      회원가입 완료
    </v-btn>
  </v-app>
</template>
<script>
import axios from 'axios'

export default {
  components: {},
  data: () => ({
    valid: true,
    passwordChk: "",
    error: {
      id: "",
      nickname: "",
    },
    signUpData: {
      id: "",
      password: "",
      nickname: "",
      hashtag: [

      ],
    },

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
      console.log(this.$refs.form.validate()); // true false
    },

    // 아이디 중복체크
    async idCheck(id) {
      // 둘중에 어느방식인지 확인 필요함
      const res = await axios.get(`http://localhost:8500/api/user/checkId/${id}`, { params: { userId: id }});
      console.log(res);
      if (res.data) {
        alert("아이디가 중복됩니다.")
        this.error.id = "아이디가 중복됩니다."
      }
      else {
        alert("아이디를 사용가능합니다.")
      }
    },
    
    // 닉네임 중복 체크
    async nickNameCheck(nickname) {
      const res = await axios.get(`http://localhost:8500/api/user/checkNickname/${nickname}`)
      console.log(res);
      
      if (res.data) {
        alert("닉네임이 중복됩니다.")
        this.error.nickname = "닉네임이 중복됩니다."
      }
      else {
        alert("닉네임을 사용가능합니다.")
      }
    },
    signUp(signUpData) {
      
      // console.log(this.signUpData.hashtag)
      for (var i = 0; i<this.signUpData.hashtag.length; i++) {
        //this.signUpData.hashtag[i]=this.hashtag[i];
        console.log(this.signUpData.hashtag)
      }

      this.$store.dispatch('signUp',[signUpData,this.selected])  
      this.$router.push('/')
    }
  },
};
</script>

<style scoped>
.signUpPage {
  max-width: 1080px;
  margin: 0 auto;
  margin-top: 48px;
  text-align: center;
}
</style>
