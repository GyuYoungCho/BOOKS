<template>
  <div>
    <v-app>
      <v-container fluid>
        <v-row align="center" justify="end">
          <v-col class="d-flex" cols="2" sm="2">
            <v-select :items="items" placeholder="통합검색" v-model="selectedItem"></v-select>
          </v-col>
          <p>{{ selectedItem }}</p>
          <v-col class="d-flex" cols="8" sm="8">
            <v-text-field placeholder="검색어를 입력하세요."></v-text-field>
          </v-col>
          <v-col class="d-flex" cols="1">
            <i class="fas fa-search"></i>
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
  methods: {},
};
</script>

<style scoped>
::v-deep .v-application--wrap {
  min-height: 0;
}
i{
  font-size: 1.5rem;
  cursor: pointer;
}
</style>
