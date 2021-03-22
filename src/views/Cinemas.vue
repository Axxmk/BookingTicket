<template>
  <div>
    <h1 class="header">Cinemas</h1>
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
          id: 1,
          movieShow: "Aladdin",
        },
        {
          id: 2,
          movieShow: "Avenger End Game",
        },
        {
          id: 3,
          movieShow: "Narnia",
        },
        {
          id: 4,
          movieShow: "Beauty and The Beast",
        },
        {
          id: 5,
          movieShow: "Enchanted",
        },
        {
          id: 6,
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
  font-size: 4.5vw;

  margin: 2rem 0;
  color: transparent;
  -webkit-text-stroke: 2.5px #67aff3;
}

.cinema-wrap {
  margin: 0 6rem;
}
</style>