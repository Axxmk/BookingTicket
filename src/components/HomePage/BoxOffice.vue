<template>
  <v-container class="py-5 my-5">
    <h1 class="pb-10 text-center header">Box Office</h1>
    <v-row>
      <v-col
        cols="12"
        sm="6"
        md="3"
        lg="3"
        v-for="(movie, index) in movies"
        :key="movie.id"
      >
        <v-container class="ranking">
          <span class="rank-number">{{ index + 1 }}</span>
          <MovieCardIF :movie="movie"></MovieCardIF>
        </v-container>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  components: {
    MovieCardIF: () => import("../MoviesPage/MovieCardIF"),
  },
  props: {
    movies: Array,
  },
  data() {
    return {};
  },
};
</script>

<style lang="scss" scoped>
.header {
  font-size: 2.9vw;
}

.ranking {
  position: relative;

  .rank-number {
    position: absolute;
    z-index: 100;
    font-size: 7.5vw;
    bottom: 20px;
    left: 5px;
    color: #f5f5f5c5;
    -webkit-text-stroke: 2.5px #ebc858;
  }
}
</style>