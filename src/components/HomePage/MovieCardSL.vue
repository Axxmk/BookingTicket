<template>
  <v-card class="mx-auto card-selected" max-width="78%" elevation="3">
    <v-img :src="`assets/poster/${movie.id}.jpg`"></v-img>

    <v-card-subtitle
      class="pa-0 text-center release-date"
      style="color: #2580d3; font-size: 55%"
    >
      {{ movie.releaseDate }}
    </v-card-subtitle>

    <v-card-title
      :class="isActive ? 'primary--text' : ''"
      class="px-2 py-0 justify-center text-uppercase"
      style="color: #444444; font-size: 70%; font-weight: 500"
    >
      {{ movie.title }}
    </v-card-title>
  </v-card>
</template>

<script>
export default {
  props: {
    movie: Object,
    isActive: Boolean,
  },
};
</script>

<style lang="scss" scoped>
.card-selected {
  cursor: pointer;
  transition: all 0.1s ease-in-out;

  &:hover {
    transform: translateY(-5px) scale(1.02);
  }
}
</style>