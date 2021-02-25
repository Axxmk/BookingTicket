<template>
  <v-container class="px-8 d-flex align-center">
    <v-btn
      :color="isActive ? 'blue lighten-2t' : 'grey lighten-1'"
      text
      width="100%"
    >
      {{ cinema.branch }}
    </v-btn>
  </v-container>
</template>

<script>
export default {
  props: {
    cinema: Object,
    isActive: Boolean,
  },
  data() {
    return {};
  },
};
</script>

<style lang="scss" scoped>
</style>