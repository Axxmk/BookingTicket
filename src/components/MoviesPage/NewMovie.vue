<template>
  <v-dialog v-model="dialog" max-width="600px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        class="add-button"
        outlined
        plain
        v-bind="attrs"
        v-on="on"
        color="blue darken-3"
      >
        <v-icon class="pr-2" size="22" color="blue darken-3"> mdi-plus </v-icon>
        Add Movie
      </v-btn>
    </template>

    <NewMovieData @closeDialog="dialog = false"></NewMovieData>
  </v-dialog>
</template>

<script>
export default {
  components: {
    NewMovieData: () => import("./NewMovieData"),
  },
  data() {
    return {
      dialog: false,
    };
  },
};
</script>

<style lang="scss" scoped>
</style>