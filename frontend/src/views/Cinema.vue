<template>
  <div>
    <h1 class="header">Theatre</h1>

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
      <v-icon size="30" color="orange">mdi-close-circle-outline</v-icon>
      <span>No Movie Showtime Right Now</span>
      <v-btn link :to="{ name: 'Movies' }" color="white" class="yellow--text">
        See Movies
      </v-btn>
    </div>
  </div>
</template>

<script>
export default {
  metaInfo: {
    title: "Cinema",
  },
  components: {
    CinemaCard: () => import("../components/CinemasPage/CinemaCard"),
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
.header {
  text-align: center;
  font-size: 4.7vw;

  margin: 2rem 0;
  color: transparent;
  -webkit-text-stroke: 2.5px #f4c430;
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