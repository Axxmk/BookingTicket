<template>
  <v-card>
    <v-card-title class="delete-text">
      Are you sure to delete this showtime?
    </v-card-title>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="blue darken-1" text @click="$emit('closeDelete')">
        Cancel
      </v-btn>
      <v-btn color="blue darken-1" text @click="deleteShowtime">
        Confirm
      </v-btn>
      <v-spacer></v-spacer>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: {
    showtimeId: Number,
    movieId: Number,
  },
  methods: {
    deleteShowtime() {
      let data = {
        showtimeId: this.showtimeId,
        movieId: this.movieId,
      };
      this.$store.dispatch("deleteShowtime", data);
      this.$emit("closeDelete");
    },
  },
};
</script>

<style lang="scss" scoped>
.delete-text {
  display: flex;
  justify-content: center;
  font-weight: 500;
  color: rgba(0, 0, 0, 0.699);
}
</style>