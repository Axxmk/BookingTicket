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
            v-for="(date, index) in theatreInfo.allDate"
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
            v-for="(time, index) in theatreInfo.allTime"
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
        <v-select
          style="width: 300px"
          v-model="seatSelect"
          :items="theatreInfo.availableSeat"
          label="Pick your seats"
          multiple
          chips
          color="yellow darken-2"
          item-color="yellow darken-1"
          hint="Available Seats"
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
        <v-radio-group v-model="payment" class="mt-0" row mandatory>
          <v-radio
            label="Credit / Debit"
            value="Credit"
            color="yellow darken-1"
            class="mx-5"
          ></v-radio>
          <v-radio
            label="KBank"
            value="KBank"
            color="yellow darken-1"
            class="mx-5"
          ></v-radio>
        </v-radio-group>
      </v-card>

      <v-btn color="yellow darken-1" class="white--text" @click="steps = 4">
        Buy Ticket
      </v-btn>
      <v-btn text class="mx-3" @click="steps = 2"> Back </v-btn>
    </v-stepper-content>
  </v-stepper>
</template>

<script>
export default {
  props: {
    theatreInfo: Object,
  },
  data() {
    return {
      dateSelect: 0,
      timeSelect: 0,
      seatSelect: [],
      confirmEmail: "",
      payment: "",
      steps: 1,
      rule: [
        (v) => !!v || "E-mail is required",
        (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
      ],
    };
  },
  watch: {
    dateSelect(current) {
      this.$emit("dataChange", this.theatreInfo.allDate[current], "date");
    },
    timeSelect(current) {
      this.$emit("dataChange", this.theatreInfo.allTime[current], "time");
    },
    seatSelect(current) {
      this.$emit("dataChange", current, "seat");
    },
    payment(current) {
      this.$emit("dataChange", current, "payment");
    },
  },
  mounted() {
    this.$emit("dataChange", this.theatreInfo.allDate[this.dateSelect], "date");
    this.$emit("dataChange", this.theatreInfo.allTime[this.timeSelect], "time");
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