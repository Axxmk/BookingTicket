<template>
  <div>
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
        <h1 class="heading">Date</h1>

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
              active-class="blue lighten-2 white--text"
              depressed
              @click="toggle"
            >
              {{ date }}
            </v-btn>
          </v-slide-item>
        </v-slide-group>

        <template v-if="start_times.length > 0">
          <h1 class="heading">Time</h1>

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
                active-class="blue lighten-2 white--text"
                depressed
                @click="toggle"
              >
                {{ time }}
              </v-btn>
            </v-slide-item>
          </v-slide-group>
        </template>

        <template v-if="theatres.length > 0">
          <h1 class="heading">Theatre</h1>

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
                active-class="blue lighten-2 white--text"
                depressed
                @click="toggle"
              >
                {{ theatre }}
              </v-btn>
            </v-slide-item>
          </v-slide-group>
        </template>
      </v-card>

      <v-btn color="yellow darken-1" class="white--text" @click="nextToStep2">
        Continue
      </v-btn>
      <v-btn text class="mx-3" disabled> Back </v-btn>
    </v-stepper-content>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  mounted() {
    this.$store.dispatch("bookingShowtime", this.$route.params.id);
  },
  props: {
    steps: Number,
  },
  computed: {
    ...mapGetters([
      "dates",
      "start_times",
      "theatres",
      "bookingInfo",
      "showtimeId",
    ]),
  },
  methods: {
    nextToStep2() {
      if (this.bookingInfo.start_time) {
        this.$store.dispatch("bookingSeats", this.showtimeId);
        this.$emit("nextStep");
      } else {
        alert("Please select Time");
      }
    },
  },
};
</script>
