<template>
  <v-card class="pa-2">
    <v-card-title class="header"> Information </v-card-title>

    <v-card-text>
      <v-form ref="form" v-model="valid">
        <NameField
          :fullname="userInfo.fullname"
          :lastnameRule="rules.lastname"
          :firstnameRule="rules.firstname"
        ></NameField>

        <v-text-field
          v-model="userInfo.email"
          :rules="rules.email"
          clear-icon="mdi-close-circle"
          clearable
          validate-on-blur
          filled
          color="blue lighten-1"
          rounded
          dense
          required
        >
          <template v-slot:label>
            <v-icon style="vertical-align: middle" color="blue lighten-2">
              mdi-email-variant
            </v-icon>
            E-mail
          </template>
        </v-text-field>

        <v-text-field
          v-model="userInfo.phone"
          :rules="rules.phone"
          clear-icon="mdi-close-circle"
          clearable
          validate-on-blur
          filled
          color="blue lighten-1"
          rounded
          dense
          required
        >
          <template v-slot:label>
            <v-icon style="vertical-align: middle" color="blue lighten-2">
              mdi-phone
            </v-icon>
            Phone Number
          </template>
        </v-text-field>
      </v-form>
    </v-card-text>

    <v-divider class="mx-3"></v-divider>

    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="blue darken-1" text @click="$emit('closeDialog')">
        Close
      </v-btn>
      <v-btn color="blue darken-1" text @click="$emit('closeDialog')">
        Save
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  components: {
    NameField: () => import("../LoginPage/NameField"),
  },
  props: {
    userInfo: Object,
  },
  data() {
    return {
      valid: true,
      rules: {
        firstname: [(v) => !!v || "FirstName is required."],
        lastname: [(v) => !!v || "LastName is required."],
        phone: [
          (v) => !!v || "Phone Number is required.",
          (v) => (v && v.length == 10) || "Phone Number must be 10 digits",
        ],
        email: [
          (v) => !!v || "E-mail is required",
          (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
        ],
      },
    };
  },
  methods: {},
};
</script>

<style lang="scss" scoped>
.header {
  color: rgb(51, 80, 158);
  margin-left: 2%;
  margin-bottom: 2%;
}
</style>