<template>
  <div>
    <h1 class="header">Cinema</h1>

    <v-row class="cinema-wrap">
      <v-col
        v-for="showtime in showtimes"
        :key="showtime.theatre"
        cols="12"
        sm="6"
        md="4"
        lg="3"
      >
        <CinemaCard
          :theatre="showtime.theatre"
          :movieId="showtime.movieId"
        ></CinemaCard>
      </v-col>

      <v-col
        class="d-flex justify-center align-center"
        cols="12"
        sm="6"
        md="4"
        lg="3"
      >
      </v-col>
    </v-row>
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
  data() {
    return {
      showtimes: [
        {
          theatre: 1,
          movieId: 1,
        },
        {
          theatre: 2,
          movieId: 2,
        },
        {
          theatre: 3,
          movieId: 3,
        },
        {
          theatre: 4,
          movieId: 4,
        },
        {
          theatre: 5,
          movieId: 5,
        },
        {
          theatre: 6,
          movieId: 6,
        },
        {
          theatre: 7,
          movieId: 13,
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped>
.header {
  text-align: center;
  font-size: 4.7vw;

  margin: 2rem 0;
  color: transparent;
  -webkit-text-stroke: 2.5px #ebc858;
}

.cinema-wrap {
  margin: 0 6rem;
}
</style>