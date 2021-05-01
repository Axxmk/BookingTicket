<template>
  <v-stepper
    v-model="steps"
    vertical
    style="background-color: #f5f5f5; box-shadow: none"
  >
    <v-stepper-step :complete="steps > 1" step="1">
      Select Showtime
      <small class="pt-1">Date & Time</small>
    </v-stepper-step>

    <v-stepper-content step="1">
      <v-card
        color="white"
        class="mb-12 pb-10 pa-4 d-flex flex-column align-center justify-center"
        height="fit-content"
      >
        <h1>Date</h1>

        <v-slide-group v-model="bookingInfo.date" mandatory show-arrows>
          <v-slide-item
            v-for="(date, index) in dates"
            :key="index"
            :value="date"
            v-slot="{ active, toggle }"
          >
            <v-btn
              class="mx-2"
              :input-value="active"
              active-class="yellow darken-1 white--text"
              depressed
              @click="toggle"
            >
              {{ date }}
            </v-btn>
          </v-slide-item>
        </v-slide-group>

        <template v-if="start_times.length > 0">
          <h1>Time</h1>

          <v-slide-group v-model="bookingInfo.start_time" show-arrows>
            <v-slide-item
              v-for="(time, index) in start_times"
              :key="index"
              :value="time"
              v-slot="{ active, toggle }"
            >
              <v-btn
                class="mx-2"
                :input-value="active"
                active-class="yellow darken-1 white--text"
                depressed
                @click="toggle"
              >
                {{ time }}
              </v-btn>
            </v-slide-item>
          </v-slide-group>
        </template>

        <template v-if="theatres.length > 0">
          <h1>Theatre</h1>

          <v-slide-group v-model="bookingInfo.theatre" mandatory show-arrows>
            <v-slide-item
              v-for="(theatre, index) in theatres"
              :key="index"
              :value="theatre"
              v-slot="{ active, toggle }"
            >
              <v-btn
                class="mx-2"
                :input-value="active"
                active-class="yellow darken-1 white--text"
                depressed
                @click="toggle"
              >
                {{ theatre }}
              </v-btn>
            </v-slide-item>
          </v-slide-group>
        </template>
      </v-card>

      <v-btn color="blue lighten-1" class="white--text" @click="nextToStep2">
        Continue
      </v-btn>
      <v-btn text class="mx-3" disabled> Back </v-btn>
    </v-stepper-content>

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
        <v-subheader v-text="'(preview only)'"></v-subheader>
        <v-select
          style="width: 300px"
          v-model="bookingInfo.seat"
          :items="bookingSeats"
          label="Available Seats"
          multiple
          chips
          color="yellow darken-2"
          item-color="yellow darken-1"
          hint="Pick your seats"
          persistent-hint
        ></v-select>
      </v-card>

      <v-btn color="blue lighten-1" class="white--text" @click="nextToStep3">
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
        <h1>Confirmation</h1>
        <v-text-field
          v-model="bookingInfo.confirmEmail"
          :rules="rule"
          clear-icon="mdi-close-circle"
          clearable
          validate-on-blur
          filled
          color="yellow darken-2"
          background-color="#f5f5f5"
          dense
          rounded
          required
          @keydown.enter="createAccount"
        >
          <template v-slot:label>
            <v-icon style="vertical-align: middle" color="yellow darken-1">
              mdi-email-variant
            </v-icon>
            E-mail
          </template>
        </v-text-field>

        <h1>Payment Method</h1>
        <span style="color: grey">* Cash Only</span>
      </v-card>

      <v-btn color="yellow darken-1" class="white--text" @click="buyTicket">
        Buy Ticket
      </v-btn>
      <v-btn text class="mx-3" @click="steps = 2"> Back </v-btn>
    </v-stepper-content>
  </v-stepper>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  mounted() {
    this.$store.dispatch("bookingShowtime", this.$route.params.id);
  },
  computed: {
    ...mapGetters([
      "dates",
      "start_times",
      "theatres",
      "bookingSeats",
      "bookingInfo",
      "showtimeId",
    ]),
  },
  data: () => ({
    steps: 1,
    rule: [
      (v) => !!v || "E-mail is required",
      (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
    ],
  }),
  methods: {
    nextToStep2() {
      if (
        this.bookingInfo.date &&
        this.bookingInfo.start_time &&
        this.bookingInfo.theatre
      ) {
        this.$store.dispatch("bookingSeats", this.showtimeId);
        this.steps = 2;
      } else {
        if (!this.bookingInfo.start_time) alert("Please select time");
        else alert("somting went wrong");
      }
    },
    nextToStep3() {
      let seatCount = this.bookingInfo.seat.length;
      if (seatCount > 0) {
        this.bookingInfo.price = seatCount * 200;
        this.steps = 3;
      } else alert("Please select seat");
    },
    buyTicket() {
      if (this.bookingInfo.confirmEmail) console.log(this.showtimeId);
      else alert("Please fill confirm email");
    },
  },
};
</script>

<style lang="scss" scoped>
.v-stepper--vertical {
  width: 70%;
  padding: 4%;

  & h1 {
    font-size: 30px;
    line-height: 80px;
    letter-spacing: 2px;
    color: #4d92d3;
  }

  @media screen and (max-width: 768px) {
    h1 {
      font-size: 25px;
    }
  }
}
</style>