<template>
  <v-stepper
    v-model="steps"
    vertical
    style="background-color: #f5f5f5; box-shadow: none"
  >
    <v-stepper-step :complete="steps > 2" step="2">
      Select Seat
    </v-stepper-step>

    <v-stepper-content step="2">
      <v-card
        color="white"
        class="mb-12 pa-4 d-flex flex-column justify-center align-center"
        height="fit-content"
      >
        <v-img
          width="510"
          height="auto"
          src="../../assets/cinemaPlan.png"
        ></v-img>
        <v-subheader v-text="'*(preview only)*'"></v-subheader>
        <v-select
          style="width: 300px"
          v-model="bookingInfo.seat"
          :items="bookingSeats"
          label="Available Seats"
          multiple
          chips
          item-color="blue lighten-2"
          hint="Pick your seats"
          persistent-hint
        ></v-select>
      </v-card>

      <v-btn color="yellow darken-1" class="white--text" @click="nextToStep3">
        Continue
      </v-btn>
      <v-btn text class="mx-3" @click="steps = 1"> Back </v-btn>
    </v-stepper-content>

    <v-stepper-step :complete="steps > 3" step="3"> Payment </v-stepper-step>

    <v-stepper-content step="3">
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

      <v-btn color="yellow darken-1" class="white--text" @click="buyTicket">
        Buy Ticket
      </v-btn>
      <v-btn text class="mx-3" @click="steps = 2"> Back </v-btn>
    </v-stepper-content>
  </v-stepper>
</template>

<script>
import { mapActions } from "vuex";

export default {
  // mounted() {
  //   this.$store.dispatch("bookingShowtime", this.$route.params.id);
  // },
  computed: {
    bookingInfo() {
      return this.$store.getters.bookingInfo;
    },
  },
  data: () => ({
    steps: 1,
    rule: [(v) => !!v || "Password is required."],
    valid: false,
    confirmPassword: "",
    payment: "",
  }),
  methods: {
    ...mapActions(["bookingSeats", "checkPassword"]),
    // nextToStep2() {
    //   if (this.bookingInfo.start_time) {
    //     this.$store.dispatch("bookingSeats", this.showtimeId);
    //     this.steps = 2;
    //   } else {
    //     alert("Please select time");
    //   }
    // },
    nextToStep3() {
      let seatCount = this.bookingInfo.seat.length;
      if (seatCount > 0) {
        this.bookingInfo.price = seatCount * 200;
        this.steps = 3;
      } else alert("Please select seat");
    },
    buyTicket() {
      if (this.valid) {
        let data = {
          password: this.confirmPassword,
        };
        this.$store.dispatch("checkPassword", data);
      } else alert("Please fill your password for confirmation");
    },
  },
};
</script>

<style lang="scss" >
.v-stepper--vertical {
  width: 70%;
  padding: 4%;

  & .heading {
    font-size: 30px;
    line-height: 80px;
    letter-spacing: 2px;
    color: #4d92d3;
  }

  @media screen and (max-width: 768px) {
    .heading {
      font-size: 25px;
    }
  }
}
</style>