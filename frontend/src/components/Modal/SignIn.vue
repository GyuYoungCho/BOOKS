<template>
  <div class="SignInDiv" id="signInDiv">
    <!-- v-if="$route.path.length == 8 || $route.path.length == 7" -->
    <div class="SignInDivBody" @keyup.enter="signIn(signInData)" id="signInDivBody">
      <!-- <h1>Sign In</h1> -->
      <!-- <img src="https://cdn.discordapp.com/attachments/869113013892964412/870324360420929586/logo_png.png" alt=""> -->
      <img src="@/assets/BOOKS-logo.png" alt="" id="loginLogo" />
      <div>
        <!-- <label for="id">ID </label> -->
        <input
          v-model="signInData.id"
          id="id"
          pattern="^([a-z0-9_]){6,50}$"
          placeholder="ID"
          type="text"
        />
      </div>

      <div>
        <!-- <label for="password">Password : </label> -->
        <form>
          <input
            v-model="signInData.password"
            id="password"
            pattern=""
            placeholder="Password"
            type="password"
            autocomplete="off"
          />
        </form>
      </div>

      <button class="login" @click="signIn(signInData)">Login</button>
    </div>
    <div class="SignInDivFooter">
      <span>
        <a @click="signUp()">Create Account</a>
      </span>
      <span>
        <a @click="close()">Close</a>
      </span>
    </div>
  </div>
</template>

<script>
export default {
  name: "SignIn",
  components: {},
  data() {
    return {
      sampleData: "",
      dialog: false,
      signInData: {
        id: "",
        password: "",
        user_id: ""
      },
    };
  },
  beforeCreate() {},
  created() {

  },
  beforeMount() {},
  async mounted() {

  },
  beforeUpdate() {},
  updated() {},
  beforeUnmount() {},
  unmounted() {},
  methods: {
    close() {
      this.$store.state.tryingLogin = !this.$store.state.tryingLogin;
    },
    signIn(signInData) {
      console.log(signInData)
      this.$store.dispatch('signIn', signInData)
    },
    signUp() {
      this.$router.push('/signup')
    }
  },
};
</script>

<style scoped>
.SignInDiv {
  /* display: table; */
  /* inline-block 사용시 margin과 함께 사용. inline-block은 가로 중앙정렬만 가능 */
  /* display: inline-block; */

  /* 다른 중앙정렬 방식 position ~ transform */
  /* body에 overflow:auto 통해 뷰포트가 작아져도 웹사이트 구조는 유지하게 만들 수 있다.(여기선 index.html) */
  position: absolute;
  top: calc(218px);
  left: calc(90%);
  transform: translate(-50%, -50%);
  background-color: rgba(0, 0, 0, 0.4);

  width: 380px;
  height: 300px;
  /* 상우하좌 padding으로 내부컨텐츠 정렬 구현 */
  padding: 10px 45px 40px 45px;
  /* margin: 50px auto; */
  /* margin-block: 10%; */

  text-align: center;

  border-radius: 50px;
  /* background-color: rgba(0, 0, 0, .75); */
  background-color: rgba(183, 220, 204, 0.5);
  /* opacity: 0.5; */
  box-shadow: rgba(0, 0, 0, 0.15) 2.4px 2.4px 3.2px;
}

.SignInDivBody {
  /* display: table-cell; */
  margin: auto;

  /* vertical-align: middle; */
}

/* 좌우 배치 */
.SignInDivFooter {
  display: flex;
  justify-content: space-around;
}

h1 {
  color: white;
  text-align: start;
}

img {
  margin-top: 10px;
  width: 100px;
}

input {
  width: 250px;
  height: 30px;
  /* 상 좌우 하 */
  padding: 12px 20px 5px;
  margin-bottom: 15px;

  border: none;
  border-radius: 20px;
  background-color: rgba(255, 255, 255, 0.6);

  color: black;
  font-size: 16px;
}

/* input#id:invalid {
  border: 3px solid red;
} */
/* input:valid {
    background-color: greenyellow;
  } */

/* 글자 입력시 테두리 삭제 */
input:focus {
  outline: none;
}

input::placeholder {
  color: rgba(237, 119, 102, 1);
  font-weight: bold;
  font-family: monospace;
}

.login {
  width: 150px;
  height: 40px;
  padding: 12px 20px 5x;
  margin-bottom: 25px;

  border: none;
  border-radius: 15px;
  background-color: rgba(255, 255, 255, 0.6);

  color: rgba(237, 119, 102, 1);
  font-size: 20px;
  font-weight: bold;
  font-family: monospace;
  text-decoration: none;

  cursor: pointer;
}

span {
  color: rgba(237, 119, 102, 1);
  font-size: 15px;
  font-weight: bold;
}

span > a {
  font-weight: bold;
  cursor: pointer;
}
</style>
