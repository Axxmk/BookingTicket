<template>
  <div>
    <v-card-title style="font-size: 1.6vw">{{ movie.title }}</v-card-title>

    <v-card-subtitle> Release Date: {{ movie.releaseDate }} </v-card-subtitle>
    <v-card-text>
      <h4>Synopsis</h4>
      {{ movie.synopsis }}

      <h4 class="mt-2">Genre</h4>
      {{ genre }}
    </v-card-text>
  </div>
</template>

<script>
export default {
  props: {
    movie: Object,
  },
  computed: {
    genre() {
      let genres = this.movie.genre;
      let result = "";

      for (let i = 0; i < genres.length; i++) {
        if (i == genres.length - 1) {
          result += genres[i];
        } else {
          result += genres[i] + ", ";
        }
      }

      return result;
    },
  },
};
</script>

<style lang="scss" scoped>
h4 {
  padding: 1% 0;
  color: grey;
  font-weight: 400;
}
</style>