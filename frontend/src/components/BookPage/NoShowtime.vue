<template>
  <div class="no-showtime">
    <v-icon size="30" color="orange">mdi-ticket</v-icon>
    <span>Sorry, no showtime aviable now</span>
    <v-btn
      link
      :to="{ name: 'Movies' }"
      color="yellow darken-1"
      class="white--text my-2"
    >
      See other Movies
    </v-btn>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.no-showtime {
  width: 65vw;
  height: 100vh;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  & span {
    padding: 1% 0;
    font-size: 1.7vw;
    color: rgba(0, 0, 0, 0.541);
  }
}
</style>