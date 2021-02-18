<template>
  <div>
    <v-card class="mx-auto" max-width="70%" elevation="3">
      <v-img :src="`assets/poster/${movie.id}.jpg`"></v-img>

      <v-card-subtitle class="pa-0 pt-1 text-center">
        {{ movie.releaseDate }}
      </v-card-subtitle>

      <v-card-title
        class="pa-0 pb-2 justify-center"
        style="color: #2580d3; font-size: 120%"
      >
        {{ movie.title }}
      </v-card-title>
    </v-card>
  </div>
</template>

<script>
export default {
  props: {
    movie: Object,
  },
};
</script>

<style>
</style>