<template>
  <div id="part-flex">
    <div class="header">
      <hr />
      <h1>MONTHEARA</h1>
      <hr />
    </div>
    <Carousel></Carousel>
    <ViewMovie :nowShowing="nowShowing" :comingSoon="comingSoon"></ViewMovie>
    <hr class="line" />
    <BoxOffice :movies="ranking"></BoxOffice>
    <SnackBar></SnackBar>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  components: {
    Carousel: () => import("../components/HomePage/Carousel"),
    ViewMovie: () => import("../components/MoviesPage/ViewMovie"),
    BoxOffice: () => import("../components/HomePage/BoxOffice"),
    SnackBar: () => import("../components/SnackBar"),
  },
  metaInfo: {
    title: "Home",
  },
  mounted() {
    this.$store.dispatch("getMovies");
  },
  computed: {
    ...mapGetters({
      nowShowing: "nowShowingMovies",
      comingSoon: "comingSoonMovies",
      ranking: "rankingMovie",
    }),
  },
};
</script>

<style lang="scss" scoped>
$base-color: #f4c430;

#part-flex {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 2rem;

  .header {
    display: flex;
    justify-content: center;
    align-items: center;

    & hr {
      width: 270px;
      border: 1px solid $base-color;
      background-color: $base-color;
    }

    & h1 {
      letter-spacing: 5px;
      padding: 0 2.5%;
      font-size: 3.7vw;
      color: transparent;
      -webkit-text-stroke: 2px $base-color;
    }
  }

  .line {
    margin: 3% 0;
    width: 40vw;
    border: 1.5px solid $base-color;
    background-color: $base-color;
  }
}
</style>