<template>
  <div>
    <div class="header">
      <hr />
      <h1>Theatre</h1>
      <hr />
    </div>

    <div v-if="showtimes.length > 0">
      <v-row class="cinema-wrap">
        <v-col
          v-for="showtime in showtimes"
          :key="showtime.theatre"
          cols="12"
          sm="6"
          md="4"
          lg="3"
        >
          <CinemaCard :showtime="showtime"></CinemaCard>
        </v-col>
      </v-row>
    </div>

    <div v-else class="no-movie">
      <v-icon size="35" color="blue lighten-2">mdi-ticket</v-icon>
      <span>No Movie Showtime Right Now</span>
      <v-btn link :to="{ name: 'Movies' }" color="blue lighten-1" plain>
        See Movies
        <v-icon>mdi-arrow-top-right</v-icon>
      </v-btn>
    </div>

    <SnackBar></SnackBar>
  </div>
</template>

<script>
export default {
  metaInfo: {
    title: "Cinema",
  },
  components: {
    CinemaCard: () => import("../components/CinemasPage/CinemaCard"),
    SnackBar: () => import("../components/SnackBar"),
  },
  mounted() {
    this.$store.dispatch("getNowShowing");
  },
  computed: {
    showtimes() {
      return this.$store.getters.nowShowingShowtime;
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
    margin: 2rem 2.5%;
    font-size: 4.9vw;
    color: transparent;
    -webkit-text-stroke: 2.2px $base-color;
  }
}

.cinema-wrap {
  margin: 0 6rem;
}

.no-movie {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 10%;

  & span {
    padding: 1% 0;
    font-size: 1.7vw;
    color: rgba(0, 0, 0, 0.541);
  }
}
</style>