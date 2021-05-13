<template>
  <div>
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

    <v-btn color="yellow darken-1" class="white--text" @click="seatDone">
      Continue
    </v-btn>
    <v-btn text class="mx-3" @click="$emit('backStep')"> Back </v-btn>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters(["bookingInfo", "bookingSeats"]),
  },
  methods: {
    seatDone() {
      let seatCount = this.bookingInfo.seat.length;
      if (seatCount > 0) {
        this.bookingInfo.price = seatCount * 200;
        this.$emit("nextStep");
      } else this.$store.dispatch("showError", "Please select seat");
    },
  },
};
</script>