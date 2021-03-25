<template>
  <div class="information">
    <div>
      <v-icon color="blue darken-1"> mdi-email </v-icon>
      <span>{{ emailShow }}</span>
    </div>

    <div>
      <v-icon color="blue darken-1"> mdi-phone </v-icon>
      <span>{{ phoneShow }}</span>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    phone: String,
    email: String,
  },
  data() {
    return {
      phoneShow: this.phone,
      emailShow: this.email,
    };
  },
};
</script>

<style lang="scss" scoped>
.information {
  margin: 4% 6%;

  & i {
    font-size: 1.8vw;
    margin: 3% 6%;
  }

  & span {
    font-size: 1.05em;
    color: black;
  }

  @media screen and (max-width: 1042px) {
  }
}
</style>