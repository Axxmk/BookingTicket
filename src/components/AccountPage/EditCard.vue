<template>
  <v-card class="pa-2">
    <v-card-title class="header"> Information </v-card-title>

    <v-card-text>
      <v-form ref="form" v-model="valid">
        <NameField
          :fullname="fullname"
          :lastnameRule="rules.lastname"
          :firstnameRule="rules.firstname"
          color="#f6f6f6"
        ></NameField>

        <v-text-field
          v-model="email"
          :rules="rules.email"
          clear-icon="mdi-close-circle"
          filled
          background-color="#f7f7f7"
          color="blue lighten-1"
          dense
          rounded
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
          v-model="phone"
          :rules="rules.phone"
          clear-icon="mdi-close-circle"
          filled
          background-color="#f7f7f7"
          color="blue lighten-1"
          dense
          rounded
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
      <v-btn color="blue darken-1" text @click="close"> Close </v-btn>
      <v-btn color="blue darken-1" text @click="update"> Save </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  components: {
    NameField: () => import("../LoginPage/NameField"),
  },
  props: {
    user: Object,
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
      fullname: {
        firstname: this.user.fullname.firstname,
        lastname: this.user.fullname.lastname,
      },
      email: this.user.contacts.email,
      phone: this.user.contacts.phone,
    };
  },
  methods: {
    update() {
      console.log(this.email);
      this.$emit("closeDialog");
    },
    close() {
      this.fullname = {
        firstname: this.user.fullname.firstname,
        lastname: this.user.fullname.lastname,
      };
      this.email = this.user.contacts.email;
      this.phone = this.user.contacts.phone;

      this.$emit("closeDialog");
    },
  },
};
</script>

<style lang="scss" scoped>
.header {
  font-size: 1.5rem;
  color: rgb(37, 62, 129);
  margin-left: 2%;
  margin-bottom: 2%;
}
</style>