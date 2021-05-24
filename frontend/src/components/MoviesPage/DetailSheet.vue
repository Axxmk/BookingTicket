<template>
  <v-card>
    <div class="d-flex justify-space-between">
      <div style="width: 220px">
        <v-img width="100%" height="100%" :src="movie.poster_path"></v-img>
      </div>

      <div class="mx-2 d-flex flex flex-column justify-space-between">
        <DetailContent :movie="movie"></DetailContent>

        <div class="ma-2">
          <v-divider></v-divider>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="blue lighten-2"
              v-if="movie.status == 'nowShowing'"
              text
              @click="buyTicket"
            >
              Buy Ticket
            </v-btn>
            <v-btn color="blue lighten-2" text @click="$emit('closeDialog')">
              Close
            </v-btn>
          </v-card-actions>
        </div>
      </div>
    </div>
  </v-card>
</template>

<script>
export default {
  components: {
    DetailContent: () => import("./DetailContent"),
  },
  props: {
    movie: Object,
  },
  methods: {
    buyTicket() {
      if (this.$store.getters.isAuth) {
        this.$router.push({
          name: "Booking",
          params: { id: this.movie.movieId },
        });
      } else {
        this.$router.push({ name: "Login" });
        this.$store.dispatch("showError", "Please login before booking");
      }
    },
  },
};
</script>

<style lang="scss" >
.v-dialog {
  border-radius: 20px;
}
</style>