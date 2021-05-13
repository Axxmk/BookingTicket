<template>
  <v-snackbar
    v-model="snackbar"
    timeout="-1"
    :color="message.color"
    rounded="lg"
  >
    {{ message.text }}

    <template v-slot:action="{ attrs }">
      <v-btn text v-bind="attrs" @click="closeSnackBar"> Close </v-btn>
    </template>
  </v-snackbar>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters(["message", "snackbar"]),
  },
  methods: {
    closeSnackBar() {
      this.$store.dispatch("toggleSnackbar", false);
    },
  },
};
</script>

<style>
</style>