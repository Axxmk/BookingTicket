<template>
  <div>
    <h1 class="header">
      Cinemas
      <hr />
    </h1>
    <v-row class="cinema-wrap">
      <v-col
        v-for="cinema in cinemas"
        :key="cinema.id"
        cols="12"
        sm="12"
        md="6"
        lg="6"
      >
        <CinemaCard :cinema="cinema"></CinemaCard>
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
      cinemas: [
        {
          id: 1,
          branch: "Central Rama 9",
          phoneNumber: "112-313-2424",
        },
        {
          id: 2,
          branch: "Central Plaza Ladprao",
          phoneNumber: "142-535-1462",
        },
        {
          id: 3,
          branch: "The Mall Thapra",
          phoneNumber: "964-446-3852",
        },
        {
          id: 4,
          branch: "BK Center",
          phoneNumber: "974-852-7375",
        },
        {
          id: 5,
          branch: "The Mall Bangkae",
          phoneNumber: "089-979-8255",
        },
        {
          id: 6,
          branch: "The Crystal Ratchapruek",
          phoneNumber: "864-826-2864",
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped>
.header {
  text-align: center;
  font-size: 5vw;

  margin: 1.2rem 0 3rem 0;
  color: #5babf5;

  & hr {
    margin: 2% auto;
    width: 70vw;
    border: 0.8px solid #f7e78d;
    background-color: #f7e78d;
  }
}

.cinema-wrap {
  margin: 0 10rem;
}
</style>