<template>
  <v-card width="95%">
    <p>Theatre {{ theatre }}</p>
    <div class="px-3">
      <v-img height="200" class="mx-1" src="../../assets/cinema.jpg"></v-img>
    </div>
    <h5>Now Showing</h5>
    <!-- <span>{{ movie.title }}</span> -->
    <v-btn text class="yellow--text text--darken-2" @click="dialog = true">
      Movie Detail
    </v-btn>

    <v-dialog v-model="dialog" width="700">
      <DetailSheet @closeDialog="dialog = false"></DetailSheet>
    </v-dialog>
  </v-card>
</template>

<script>
export default {
  components: {
    DetailSheet: () => import("../MoviesPage/DetailSheet"),
  },
  mounted() {},
  props: {
    theatre: Number,
    movieId: Number,
  },
  data: () => ({
    dialog: false,
  }),
};
</script>

<style lang="scss" scoped>
.v-sheet.v-card {
  transition: all 0.2s ease-in-out;
  margin: 2% auto;
  padding: 3%;
  border-radius: 10px;

  &:hover {
    transform: scale(1.05) translateY(-5px);
    box-shadow: 0px 3px 8px -1px rgb(0, 0, 0, 0.05),
      0px 5px 16px 1px rgb(0, 0, 0, 0.05), 0px 2px 20px 1px rgb(0, 0, 0, 0.05);
  }

  & p {
    color: rgba(128, 128, 128, 0.603);
    font-size: 0.9em;
    text-align: end;
    padding: 2% 5%;
    margin-bottom: 3%;
  }

  & h5 {
    padding: 6% 5% 2% 7%;
    font-weight: 400;
    color: rgb(168, 168, 168);
  }

  & span {
    display: block;
    font-size: 1.1em;
    font-weight: 500;
    padding: 0 7%;
    margin-bottom: 2%;
  }
}
</style>