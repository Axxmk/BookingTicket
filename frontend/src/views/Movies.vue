<template>
  <div class="d-flex flex-column align-center">
    <div class="header">
      <hr />
      <h1>Movies</h1>
      <hr />
    </div>
    <SearchBar @search="search"></SearchBar>

    <ViewMovie
      v-if="isSearch"
      :nowShowing="selectNowShowing"
      :comingSoon="selectComingSoon"
    ></ViewMovie>
    <ViewMovie
      v-else
      :nowShowing="nowShowing"
      :comingSoon="comingSoon"
    ></ViewMovie>

    <SnackBar></SnackBar>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  metaInfo: {
    title: "Movies",
  },
  components: {
    SearchBar: () => import("../components/MoviesPage/SearchBar"),
    ViewMovie: () => import("../components/MoviesPage/ViewMovie"),
    SnackBar: () => import("../components/SnackBar"),
  },
  mounted() {
    this.$store.dispatch("getMovies");
  },
  computed: {
    ...mapGetters({
      nowShowing: "nowShowingMovies",
      comingSoon: "comingSoonMovies",
    }),
  },
  data: () => ({
    selectNowShowing: [],
    selectComingSoon: [],
    isSearch: false,
  }),
  methods: {
    search(key) {
      if (!key) {
        this.isSearch = false;
        return;
      }

      key = key.toLowerCase();
      this.selectNowShowing = this.nowShowing.filter((movie) =>
        movie.title.toLowerCase().startsWith(key)
      );
      this.selectComingSoon = this.comingSoon.filter((movie) =>
        movie.title.toLowerCase().startsWith(key)
      );
      this.isSearch = true;
    },
  },
};
</script>

<style lang="scss" scoped>
$base-color: #f4c430;

.header {
  display: flex;
  justify-content: center;
  align-items: center;

  & hr {
    width: 250px;
    border: 1px solid $base-color;
    background-color: $base-color;
  }

  & h1 {
    margin: 3%;
    font-size: 5vw;
    color: transparent;
    -webkit-text-stroke: 2.2px $base-color;
  }
}
</style>