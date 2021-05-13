<template>
  <div>
    <v-card
      color="white"
      class="mb-12 pa-4 d-flex flex-column align-center justify-center"
      height="fit-content"
    >
      <h1 class="heading">Confirmation</h1>
      <v-form ref="form" v-model="valid">
        <v-text-field
          v-model="confirmPassword"
          :rules="rule"
          type="password"
          clear-icon="mdi-close-circle"
          clearable
          validate-on-blur
          filled
          color="blue lighten-2"
          background-color="#f5f5f5"
          dense
          rounded
          required
        >
          <template v-slot:label>
            <v-icon style="vertical-align: middle" color="blue lighten-2">
              mdi-lock
            </v-icon>
            Password
          </template>
        </v-text-field>
      </v-form>

      <h1 class="heading">Payment Method</h1>
      <v-radio-group v-model="payment" mandatory>
        <v-radio
          label="Banking"
          value="Banking"
          color="blue lighten-2"
        ></v-radio>
      </v-radio-group>
    </v-card>

    <v-btn color="yellow darken-1" class="white--text" @click="checkPassword">
      Buy Ticket
    </v-btn>
    <v-btn text class="mx-3" @click="$emit('backStep')"> Back </v-btn>
  </div>
</template>

<script>
export default {
  data: () => ({
    valid: false,
    rule: [(v) => !!v || "Password is required."],
    confirmPassword: "",
    payment: "",
  }),
  methods: {
    checkPassword() {
      if (this.valid) {
        let data = {
          password: this.confirmPassword,
        };
        this.$store.dispatch("checkPassword", data);
      } else
        this.$store.dispatch(
          "showError",
          "Please fill your password for confirmation"
        );
    },
  },
};
</script>