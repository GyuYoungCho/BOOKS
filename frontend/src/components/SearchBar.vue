<template>
  <div>
    <v-app>
      <v-container fluid>
        <v-row align="center" justify="center">
          
          <v-col class="d-flex" cols="8" sm="8" autocomplete>
            <v-text-field v-model="inputText" placeholder="검색어를 입력하세요." @keyup.enter="search()"></v-text-field>
          </v-col>
          <v-col class="d-flex" cols="1" sm="1">
            <i class="fas fa-search" @click="search()"></i>
          </v-col>
        </v-row>
      </v-container>
    </v-app>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "",
  components: {},
  data() {
    return {
      model: null,
      inputText: null,
      items: ["저자", "제목"],
      selectedItem: "통합검색",
    };
  },
  watch: {
    model: function () {
      console.log("hi");
      if (this.items.length > 0) return;
      // const key = 'ttbmodernseoul1532001'
      axios
        .get(
          "http://data4library.kr/api/srchDtlList?authKey=96f1954ac46690b179fea20e2e37a24101532a730dc0f1a56432925846826f2b&isbn13=9788983921987&loaninfoYN=Y",
          {
            crossdomain: true,
            headers: {
              "Access-Control-Allow-Origin": "*",
              "content-type": "application/x-www-form-urlencoded",
            },
          }
        )
        .then((res) => {
          console.log(res.data);
        });
    },
  },
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeUnmount() {},
  unmounted() {},
  methods: {
    search() {
      console.log("dkdk")
      this.$store.dispatch('searchBook', this.inputText)
      this.$router.push(`/search/${this.inputText}`)
      // this.$router.go()
    }
  },
};
</script>

<style scoped>
::v-deep .v-application--wrap {
  min-height: 0;
}

i {
  font-size: 1.5rem;
  cursor: pointer;
}
</style>
