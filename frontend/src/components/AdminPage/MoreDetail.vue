<template>
  <div class="d-flex">
    <v-img
      class="rounded-lg"
      max-width="200px"
      height="auto"
      :src="movie.poster_path"
    ></v-img>

    <div class="pa-5 d-flex flex-column justify-space-around">
      <div>
        <h4>Synopsis</h4>
        <span style="font-size: 15px">{{ movie.synopsis }}</span>

        <h4 class="mt-2">Genre</h4>
        <div
          class="mr-1"
          style="display: inline-block"
          v-for="(genre, index) in movie.genre"
          :key="index"
        >
          <span style="font-size: 15px">{{ genre }}</span>
        </div>
      </div>

      <div>
        <v-dialog v-model="dialog" max-width="550px" persistent>
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              max-width="140px"
              outlined
              plain
              v-bind="attrs"
              v-on="on"
              color="blue darken-2"
              style="font-size: 12px"
            >
              <v-icon class="pr-1" size="14" color="blue darken-2">
                mdi-plus
              </v-icon>
              Add Showtime
            </v-btn>
          </template>

          <EditShowtime
            :movieId="movie.movieId"
            @close="dialog = false"
          ></EditShowtime>
        </v-dialog>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {
    EditShowtime: () => import("./EditShowtime"),
  },
  props: {
    movie: Object,
  },
  data() {
    return {
      dialog: false,
    };
  },
};
</script>

<style lang="scss" scoped>
h4 {
  padding: 1% 0;
  color: grey;
  font-size: 13px;
  font-weight: 400;
}
</style>