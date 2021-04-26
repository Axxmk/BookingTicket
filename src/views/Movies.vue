<template>
  <div class="d-flex flex-column align-center">
    <h1 class="header">Movies</h1>
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
.header {
  font-size: 5vw;

  margin-top: 2rem;
  color: transparent;
  -webkit-text-stroke: 2.5px #ebc858;
}
</style>