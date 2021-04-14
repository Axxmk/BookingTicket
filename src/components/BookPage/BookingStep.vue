<template>
  <v-stepper
    v-model="steps"
    vertical
    style="background-color: #f5f5f5; box-shadow: none"
  >
    <v-stepper-step :complete="steps > 1" step="1" editable>
      Select Showtime
      <small class="pt-1">Date & Time</small>
    </v-stepper-step>

    <v-stepper-content step="1">
      <v-card
        color="white"
        class="mb-12 pa-4 d-flex flex-column align-center justify-center"
        height="fit-content"
      >
        <h1>Date</h1>

        <v-slide-group v-model="dateSelect" mandatory show-arrows>
          <v-slide-item
            v-for="(date, index) in theatreInfo.dates"
            :key="index"
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

        <h1>Time</h1>

        <v-slide-group v-model="timeSelect" mandatory show-arrows>
          <v-slide-item
            v-for="(time, index) in theatreInfo.times"
            :key="index"
            v-slot="{ active, toggle }"
          >
            <v-btn
              class="mx-2"
              :input-value="active"
              active-class="yellow darken-1 white--text"
              depressed
              rounded
              @click="toggle"
            >
              {{ time }}
            </v-btn>
          </v-slide-item>
        </v-slide-group>
      </v-card>

      <v-btn color="blue lighten-1" class="white--text" @click="steps = 2">
        Continue
      </v-btn>
      <v-btn text class="mx-3" disabled> Back </v-btn>
    </v-stepper-content>

    <v-stepper-step :complete="steps > 2" step="2" editable>
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
          v-model="seatSelect"
          :items="theatreInfo.availableSeat"
          label="Available Seats"
          multiple
          chips
          color="yellow darken-2"
          item-color="yellow darken-1"
          hint="Pick your seats"
          persistent-hint
        ></v-select>
      </v-card>

      <v-btn color="blue lighten-1" class="white--text" @click="steps = 3">
        Continue
      </v-btn>
      <v-btn text class="mx-3" @click="steps = 1"> Back </v-btn>
    </v-stepper-content>

    <v-stepper-step :complete="steps > 3" step="3" editable>
      Payment
    </v-stepper-step>

    <v-stepper-content step="3">
      <v-card
        color="white"
        class="mb-12 pa-4 d-flex flex-column align-center justify-center"
        height="fit-content"
      >
        <h1>Confirmation</h1>
        <v-text-field
          v-model="confirmEmail"
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

      <v-btn
        color="yellow darken-1"
        class="white--text"
        link
        :to="{ name: 'Home' }"
      >
        Buy Ticket
      </v-btn>
      <v-btn text class="mx-3" @click="steps = 2"> Back </v-btn>
    </v-stepper-content>
  </v-stepper>
</template>

<script>
export default {
  data() {
    return {
      theatreInfo: {
        dates: [
          "2021-03-01",
          "2021-03-02",
          "2021-03-03",
          "2021-03-04",
          "2021-03-05",
          "2021-03-06",
          "2021-03-07",
          "2021-03-08",
          "2021-03-09",
          "2021-03-10",
        ],
        times: ["11:50", "14:30", "15:50", "17:10", "19:50"],
        availableSeat: [
          "A12",
          "A14",
          "A20",
          "B02",
          "B08",
          "B19",
          "C10",
          "C14",
          "D20",
        ],
      },
      dateSelect: 0,
      timeSelect: 0,
      seatSelect: [],
      confirmEmail: "",
      steps: 1,
      rule: [
        (v) => !!v || "E-mail is required",
        (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
      ],
    };
  },
  watch: {
    dateSelect(current) {
      this.$emit("dataChange", this.theatreInfo.dates[current], "date");
    },
    timeSelect(current) {
      this.$emit("dataChange", this.theatreInfo.times[current], "time");
    },
    seatSelect(current) {
      this.$emit("dataChange", current, "seat");
    },
  },
  mounted() {
    this.$emit("dataChange", this.theatreInfo.dates[this.dateSelect], "date");
    this.$emit("dataChange", this.theatreInfo.times[this.timeSelect], "time");
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