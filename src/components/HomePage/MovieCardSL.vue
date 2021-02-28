<template>
  <v-card class="mx-auto card-selected" max-width="78%" elevation="3">
    <v-img :src="`assets/poster/${movie.id}.jpg`"></v-img>

    <v-card-subtitle class="pa-0 text-center release-date">
      {{ movie.releaseDate }}
    </v-card-subtitle>

    <v-card-title
      :class="isActive ? 'primary--text' : ''"
      class="px-2 py-0 title-movie"
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

  .release-date {
    color: #2580d3;
    font-size: 55%;
    font-weight: 500;
  }

  .title-movie {
    display: flex;
    justify-content: center;
    font-size: 64%;
    font-weight: 500;
    text-transform: uppercase;
  }
}
</style>