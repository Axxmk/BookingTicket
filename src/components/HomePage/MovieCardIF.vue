<template>
  <v-hover>
    <template v-slot:default="{ hover }">
      <v-card class="mx-auto" max-width="70%" elevation="3" shaped>
        <v-img :src="`assets/poster/${movie.id}.jpg`"></v-img>

        <v-card-subtitle class="pa-0 pt-1 release-date">
          {{ movie.releaseDate }}
        </v-card-subtitle>

        <v-card-title class="px-2 pb-2 title-movie">
          {{ movie.title }}
        </v-card-title>

        <v-fade-transition>
          <v-overlay v-if="hover" absolute color="#B0BEC5">
            <v-btn color="#f5f5f5" class="indigo--text">See Detail</v-btn>
          </v-overlay>
        </v-fade-transition>
      </v-card>
    </template>
  </v-hover>
</template>

<script>
export default {
  props: {
    movie: Object,
  },
};
</script>

<style lang="scss" scoped>
.release-date {
  text-align: center;
  font-size: 80%;
}

.title-movie {
  display: flex;
  justify-content: center;
  color: black;
  font-size: 102%;
  font-weight: 500;
  text-transform: uppercase;
}
</style>