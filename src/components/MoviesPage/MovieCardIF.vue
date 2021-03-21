<template>
  <v-hover>
    <template v-slot:default="{ hover }">
      <v-card class="mx-auto" max-width="75%" elevation="3" shaped>
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
                link
                :to="{ name: 'Booking' }"
                v-if="movie.type == 1"
                color="#f5f5f5"
                class="yellow--text text--darken-3 mb-3"
              >
                Buy Ticket
              </v-btn>

              <v-btn
                @click="dialog = true"
                color="#f5f5f5"
                class="yellow--text text--darken-3 mb-3"
              >
                See Detail
              </v-btn>
            </div>
          </v-overlay>
        </v-fade-transition>

        <v-dialog v-model="dialog" width="500">
          <v-card>
            <v-card-title class="headline grey lighten-2">
              Privacy Policy
            </v-card-title>

            <v-card-text>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
              eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
              enim ad minim veniam, quis nostrud exercitation ullamco laboris
              nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
              reprehenderit in voluptate velit esse cillum dolore eu fugiat
              nulla pariatur. Excepteur sint occaecat cupidatat non proident,
              sunt in culpa qui officia deserunt mollit anim id est laborum.
            </v-card-text>

            <v-divider></v-divider>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="primary" text @click="dialog = false">
                I accept
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-card>
    </template>
  </v-hover>
</template>

<script>
export default {
  props: {
    movie: Object,
  },
  data() {
    return {
      dialog: false,
    };
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