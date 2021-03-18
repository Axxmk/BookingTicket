<template>
  <v-hover>
    <template v-slot:default="{ hover }">
      <v-card class="mx-auto" max-width="60%" elevation="3" shaped>
        <v-img :src="`assets/poster/${movie.id}.jpg`"></v-img>

        <v-card-subtitle class="pa-0 pt-1 release-date">
          {{ movie.releaseDate }}
        </v-card-subtitle>

        <v-card-title class="px-2 pb-2 title-wrap">
          <div class="title-movie">
            {{ movie.title }}
          </div>
        </v-card-title>

        <v-fade-transition>
          <v-overlay v-if="hover" absolute color="#B0BEC5">
            <div class="d-flex flex-column">
              <v-btn
                v-if="movie.type == 1"
                color="#f5f5f5"
                class="yellow--text text--darken-3 mb-3"
              >
                Booking
              </v-btn>
              <v-btn color="#f5f5f5" class="yellow--text text--darken-3">
                See Detail
              </v-btn>
            </div>
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

.title-wrap {
  display: flex;
  justify-content: center;
  font-size: 102%;

  .title-movie {
    color: #186dbd;
    font-weight: 500;
    text-transform: uppercase;

    display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
    overflow: hidden;
    text-overflow: ellipsis;
  }
}
</style>