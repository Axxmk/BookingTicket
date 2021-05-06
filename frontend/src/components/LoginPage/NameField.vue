<template>
  <v-row>
    <v-col cols="12" sm="6" md="6" lg="6">
      <v-text-field
        v-model="fullname.firstname"
        :rules="firstnameRule"
        filled
        color="blue lighten-1"
        :background-color="color"
        rounded
        dense
        required
      >
        <template v-slot:label>
          <div style="width: 100%">
            <v-icon style="vertical-align: middle" color="blue lighten-2">
              mdi-account
            </v-icon>
            Firstname
          </div>
        </template>
      </v-text-field>
    </v-col>

    <v-col cols="12" sm="6" md="6" lg="6">
      <v-text-field
        v-model="fullname.lastname"
        :rules="lastnameRule"
        filled
        color="blue lighten-1"
        :background-color="color"
        rounded
        dense
        required
      >
        <template v-slot:label>
          <div style="width: 100%">
            <v-icon style="vertical-align: middle" color="blue lighten-2">
              mdi-account
            </v-icon>
            Lastname
          </div>
        </template>
      </v-text-field>
    </v-col>
  </v-row>
</template>

<script>
export default {
  props: {
    color: String,
    fullname: {
      firstname: String,
      lastname: String,
    },
    lastnameRule: Array,
    firstnameRule: Array,
  },
};
</script>

<style>
</style>