<template>
  <v-dialog v-model="dialog" width="500">
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        class="mx-8 mt-1"
        color="yellow darken-1"
        dark
        v-bind="attrs"
        v-on="on"
      >
        Edit
        <v-icon class="pl-1" size="17" color="white"> mdi-pencil </v-icon>
      </v-btn>
    </template>

    <EditCard :userInfo="userInfo" @closeDialog="dialog = false"></EditCard>
  </v-dialog>
</template>

<script>
export default {
  components: {
    EditCard: () => import("./EditCard"),
  },
  props: {
    userInfo: Object,
  },
  data() {
    return {
      dialog: false,
    };
  },
  methods: {},
};
</script>

<style>
</style>