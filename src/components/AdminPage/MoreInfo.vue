<template>
  <div class="d-flex mx-auto" style="width: 700px">
    <v-img
      class="rounded-lg"
      width="200px"
      height="auto"
      :src="`assets/poster/${id}.jpg`"
    ></v-img>
    <div class="pa-5 d-flex flex-column justify-center">
      <div>
        <h4>Synopsis</h4>
        {{ synopsis }}
      </div>
      <div>
        <h4 class="mt-2">Genre</h4>
        <div
          class="mr-1"
          style="display: inline-block"
          v-for="(genre, index) in genre"
          :key="index"
        >
          {{ genre }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    id: Number,
    synopsis: String,
    genre: Array,
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