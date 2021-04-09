<template>
  <div class="pa-3">
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
        @keydown.enter="submitForm"
      >
        <template v-slot:label>
          <v-icon style="vertical-align: middle" color="blue lighten-2">
            mdi-account-settings
          </v-icon>
          Username
        </template>
      </v-text-field>

      <v-text-field
        v-model="password"
        :type="showPass ? 'text' : 'password'"
        :rules="rules.password"
        clear-icon="mdi-close-circle"
        clearable
        :append-icon="showPass ? 'mdi-eye' : 'mdi-eye-off'"
        @click:append="showPass = !showPass"
        filled
        color="blue lighten-1"
        background-color="white"
        rounded
        dense
        required
        @keydown.enter="submitForm"
      >
        <template v-slot:label>
          <v-icon style="vertical-align: middle" color="blue lighten-2">
            mdi-key
          </v-icon>
          Password
        </template>
      </v-text-field>
    </v-form>

    <div class="pt-2 d-flex justify-space-between align-center">
      <v-btn color="grey" text> Forget your password? </v-btn>

      <v-btn
        class="px-9 white--text"
        color="blue lighten-2"
        rounded
        height="35"
        type="submit"
        @click="submitForm"
        :disabled="!valid"
      >
        Login
      </v-btn>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      valid: false,
      username: "",
      password: "",
      showPass: false,
      rules: {
        name: [
          (v) => !!v || "Username is required",
          (v) =>
            (v && v.length <= 15) || "Username must be less than 15 characters",
        ],
        password: [(v) => !!v || "Password is required."],
      },
    };
  },
  methods: {
    submitForm() {
      if (!this.valid || !this.username || !this.password) {
        alert("Please corect your information :(");
        this.$refs.form.reset();
        return;
      } else {
        axios
          .get(
            `http://localhost:8081/account/login?username=${this.username}&password=${this.password}`
          )
          .then((response) => {
            this.$store.dispatch("setAuth", response.data.isLogin);
            this.$store.dispatch(
              "setAdmin",
              response.data.isLogin && response.data.user.isAdmin
            );

            if (response.data.isLogin) {
              this.$router.push({ name: "Home" });
            } else {
              alert("Something wrong, Please try Again :(");
            }
          });
      }
    },
  },
};
</script>

<style lang="scss" scoped>
</style>