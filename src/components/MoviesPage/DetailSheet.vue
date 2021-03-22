<template>
  <v-card>
    <div class="d-flex flex-no-wrap justify-space-between">
      <div style="width: 220px">
        <v-img
          width="100%"
          height="100%"
          :src="`assets/poster/${movie.type}${movie.id}.jpg`"
        ></v-img>
      </div>

      <div class="mx-2 d-flex flex-column justify-space-between">
        <DetailContent :movie="movie"></DetailContent>

        <div class="ma-2">
          <v-divider></v-divider>
          <v-card-actions>
            <v-spacer></v-spacer>
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
  props: {
    dialog: Boolean,
    movie: Object,
  },
  components: {
    DetailContent: () => import("./DetailContent"),
  },
};
</script>

<style>
</style>