<template>
  <div class="information">
    <div>
      <v-icon color="blue darken-1"> mdi-email </v-icon>
      <span>{{ email }}</span>
    </div>

    <div>
      <v-icon color="blue darken-1"> mdi-phone </v-icon>
      <span>{{ phone }}</span>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    phone: String,
    email: String,
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
}
</style>