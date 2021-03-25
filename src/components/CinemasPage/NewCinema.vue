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
        Add Theatre
      </v-btn>
    </template>

    <NewCinemaData @closeDialog="dialog = false"></NewCinemaData>
  </v-dialog>
</template>

<script>
export default {
  components: {
    NewCinemaData: () => import("./NewCinemaData"),
  },
  data() {
    return {
      dialog: false,
    };
  },
};
</script>

<style lang="scss">
.add-button {
  font-size: 1vw;
  transition: all 0.2s ease-in-out;

  &:hover {
    transform: scale(1.05) translateY(-5px);
  }
}
</style>