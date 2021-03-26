<template>
  <v-card>
    <v-card-title class="delete-text">
      Are you sure to delete this Movie?
    </v-card-title>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="blue darken-1" text @click="$emit('closeDelete')">
        Cancel
      </v-btn>
      <v-btn color="blue darken-1" text @click="$emit('deleteItemConfirm')">
        Confirm
      </v-btn>
      <v-spacer></v-spacer>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.delete-text {
  display: flex;
  justify-content: center;
  font-weight: 500;
  color: rgba(0, 0, 0, 0.699);
}
</style>