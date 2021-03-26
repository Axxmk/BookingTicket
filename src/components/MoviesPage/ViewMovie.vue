<template>
  <div id="part-movie">
    <v-tabs v-model="currentTab" background-color="#F5F5F5" centered>
      <v-tab v-for="(tab, index) in tabs" :key="index">
        {{ tab }}
      </v-tab>
    </v-tabs>

    <v-tabs-items v-model="currentTab" class="my-10">
      <v-tab-item>
        <v-row>
          <v-col
            class="pa-4"
            cols="12"
            sm="6"
            md="3"
            lg="3"
            v-for="movie in movies.nowShowing"
            :key="movie.id"
          >
            <MovieCardIF :movie="movie"></MovieCardIF>
          </v-col>
        </v-row>
      </v-tab-item>

      <v-tab-item>
        <v-row>
          <v-col
            class="pa-4"
            cols="12"
            sm="6"
            md="3"
            lg="3"
            v-for="movie in movies.comingSoon"
            :key="movie.id"
          >
            <MovieCardIF :movie="movie"></MovieCardIF>
          </v-col>
        </v-row>
      </v-tab-item>
    </v-tabs-items>
  </div>
</template>

<script>
export default {
  components: {
    MovieCardIF: () => import("./MovieCardIF"),
  },
  props: {
    movies: {
      nowShowing: Array,
      comingSoon: Array,
    },
  },
  data() {
    return {
      currentTab: null,
      tabs: ["Now Showing", "Coming Soon"],
    };
  },
};
</script>

<style lang="scss" scoped>
#part-movie {
  margin: 1% 0;
  width: 80%;

  .v-tabs-items {
    background-color: transparent;
  }
}
</style>