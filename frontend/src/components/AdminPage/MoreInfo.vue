<template>
  <div class="d-flex flex-column mx-auto" style="width: 800px">
    <MoreDetail :movie="movie"></MoreDetail>
    <v-divider class="my-10 mx-3"></v-divider>
    <ShowtimeTable :movieId="movie.movieId"></ShowtimeTable>
  </div>
</template>

<script>
export default {
  components: {
    MoreDetail: () => import("./MoreDetail"),
    ShowtimeTable: () => import("./ShowtimeTable"),
  },
  props: {
    movie: Object,
  },
};
</script>