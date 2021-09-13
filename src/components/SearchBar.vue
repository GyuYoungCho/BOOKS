<template>
  <v-app>
    <v-autocomplete
      :search-input.sync="model"
      filled
      rounded
      solo
      clearable
      :items="items"
    ></v-autocomplete>
  </v-app>
</template>

<script>
import axios from "axios";

export default {
  name: "",
  components: {},
  data() {
    return {
      model: null,
      items: [],
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

<style></style>
