<template>
  <div class="pa-5">
    <v-form ref="form" v-model="valid">
      <v-text-field
        v-model="username"
        :rules="rules.name"
        :counter="15"
        clear-icon="mdi-close-circle"
        clearable
        filled
        color="blue darken-3"
        background-color="white"
        rounded
        dense
        required
        @keydown.enter="createAccount"
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
        color="blue darken-3"
        background-color="white"
        rounded
        dense
        required
        @keydown.enter="createAccount"
      >
        <template v-slot:label>
          <v-icon style="vertical-align: middle" color="blue lighten-2">
            mdi-lock
          </v-icon>
          Password
        </template>
      </v-text-field>

      <v-text-field
        v-model="confirmPass"
        type="Password"
        :rules="rules.confirmPass"
        validate-on-blur
        filled
        color="blue darken-3"
        background-color="white"
        rounded
        dense
        required
        @keydown.enter="createAccount"
      >
        <template v-slot:label>
          <v-icon style="vertical-align: middle" color="blue lighten-2">
            mdi-lock
          </v-icon>
          Comfirm Password
        </template>
      </v-text-field>

      <v-row>
        <v-col cols="12" sm="6" md="6" lg="6">
          <v-text-field
            v-model="fullname.firstname"
            :rules="rules.firstname"
            clear-icon="mdi-close-circle"
            clearable
            filled
            color="blue darken-3"
            background-color="white"
            rounded
            dense
            required
            @keydown.enter="createAccount"
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
            :rules="rules.lastname"
            clear-icon="mdi-close-circle"
            clearable
            filled
            color="blue darken-3"
            background-color="white"
            rounded
            dense
            required
            @keydown.enter="createAccount"
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

      <v-text-field
        v-model="phoneNumber"
        :rules="rules.phone"
        clear-icon="mdi-close-circle"
        clearable
        validate-on-blur
        filled
        color="blue darken-3"
        background-color="white"
        rounded
        dense
        required
        @keydown.enter="createAccount"
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
        :disabled="!valid"
      >
        Sign Up
      </v-btn>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      valid: true,
      username: "",
      password: "",
      confirmPass: "",
      fullname: {
        firstname: "",
        lastname: "",
      },
      phoneNumber: "",
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
        confirmPass: [
          (v) => (v && v == this.password) || "Password you enterd don't match",
        ],
        firstname: [(v) => !!v || "FirstName is required."],
        lastname: [(v) => !!v || "LastName is required."],
        phone: [
          (v) => !!v || "Phone Number is required.",
          (v) => (v && v.length == 10) || "Phone Number must be 10 digits",
        ],
      },
    };
  },
  methods: {
    createAccount() {
      if (
        !this.valid ||
        !this.username ||
        !this.password ||
        !this.confirmPass ||
        !this.fullname.firstname ||
        !this.fullname.lastname ||
        !this.phoneNumber
      ) {
        alert("Please inform all of these :(");
        this.$refs.form.reset();
        return;
      } else {
        alert("Sign Up Success");
        this.$refs.form.reset();
      }
    },
  },
};
</script>

<style>
</style>