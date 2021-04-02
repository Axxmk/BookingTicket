<template>
  <div>
    <h1 class="header">Cinema</h1>

    <v-row class="cinema-wrap">
      <v-col
        v-for="theatre in cinema"
        :key="theatre.id"
        cols="12"
        sm="6"
        md="4"
        lg="3"
      >
        <CinemaCard :theatre="theatre"></CinemaCard>
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
    title: "Cinemas",
  },
  components: {
    CinemaCard: () => import("../components/CinemasPage/CinemaCard"),
  },
  data() {
    return {
      cinema: [
        {
          theatreId: 1,
          movieShow: "Aladdin",
        },
        {
          theatreId: 2,
          movieShow: "Avenger End Game",
        },
        {
          theatreId: 3,
          movieShow: "Narnia",
        },
        {
          theatreId: 4,
          movieShow: "Beauty and The Beast",
        },
        {
          theatreId: 5,
          movieShow: "Enchanted",
        },
        {
          theatreId: 6,
          movieShow: "Inside Out",
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