<template>
  <div class="pa-1">
    <v-form ref="form" v-model="valid">
      <v-text-field
        v-model="username"
        :rules="rules.name"
        :counter="15"
        clear-icon="mdi-close-circle"
        clearable
        filled
        color="blue lighten-1"
        background-color="white"
        rounded
        dense
        required
      >
        <template v-slot:label>
          <v-icon style="vertical-align: middle" color="blue lighten-2">
            mdi-account-edit
          </v-icon>
          Username
        </template>
      </v-text-field>

      <v-text-field
        v-model="password"
        type="Password"
        :rules="rules.password"
        validate-on-blur
        clear-icon="mdi-close-circle"
        clearable
        filled
        color="blue lighten-1"
        background-color="white"
        rounded
        dense
        required
      >
        <template v-slot:label>
          <v-icon style="vertical-align: middle" color="blue lighten-2">
            mdi-lock
          </v-icon>
          Password
        </template>
      </v-text-field>

      <NameField
        :fullname="fullname"
        :lastnameRule="rules.lastname"
        :firstnameRule="rules.firstname"
        color="white"
      ></NameField>

      <v-text-field
        v-model="email"
        :rules="rules.email"
        clear-icon="mdi-close-circle"
        clearable
        validate-on-blur
        filled
        color="blue lighten-1"
        background-color="white"
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
        v-model="phone"
        :rules="rules.phone"
        clear-icon="mdi-close-circle"
        clearable
        validate-on-blur
        filled
        color="blue lighten-1"
        background-color="white"
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

    <div class="pt-2 d-flex justify-center align-center">
      <v-btn
        class="px-9 white--text"
        color="blue lighten-2"
        rounded
        height="35"
        type="submit"
        @click="createAccount"
      >
        Sign Up
      </v-btn>
    </div>
  </div>
</template>

<script>
export default {
  components: {
    NameField: () => import("./NameField"),
  },
  data: () => ({
    valid: true,
    username: "",
    password: "",
    fullname: {
      firstname: "",
      lastname: "",
    },
    email: "",
    phone: "",
    rules: {
      name: [
        (v) => !!v || "Username is required",
        (v) =>
          (v && v.length <= 15) || "Username must be less than 15 characters",
      ],
      password: [
        (v) => !!v || "Password is required.",
        (v) =>
          (v && v.length >= 6) || "Password must be more than 6 characters",
      ],
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
  }),
  methods: {
    createAccount() {
      if (!this.valid) {
        alert("Please correct your inform");
        return;
      }

      let newUser = {
        username: this.username,
        password: this.password,
        firstname: this.fullname.firstname,
        lastname: this.fullname.lastname,
        phone: this.phone,
        email: this.email,
      };

      this.$store.dispatch("register", newUser);
      this.$refs.form.reset();
    },
  },
};
</script>