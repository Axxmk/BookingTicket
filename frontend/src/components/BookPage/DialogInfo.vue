<template>
  <v-card color="#f8f8f8">
    <h2 class="head">Your Booking Detail</h2>
    <v-card-text class="content py-0">
      <Ticket :ticket="ticket"></Ticket>

      <v-checkbox v-model="checkbox" class="pt-4 px-8" color="blue lighten-1">
        <template v-slot:label>
          <div>
            Understand that I will be received <span>an email </span> about the
            bank number for payment.
          </div>
        </template>
      </v-checkbox>
    </v-card-text>

    <v-card-actions class="d-flex justify-end">
      <v-btn color="grey" plain text @click="close"> Cancle </v-btn>
      <v-btn color="primary" plain text @click="buyTicket">Confirm</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  components: {
    Ticket: () => import("./Ticket"),
  },
  computed: {
    ...mapGetters({
      ticket: "bookingInfo",
      showtimeId: "showtimeId",
    }),
  },
  data: () => ({
    checkbox: false,
  }),
  methods: {
    buyTicket() {
      if (this.checkbox) {
        let data = {
          showtimeId: this.showtimeId,
          seatNumbers: this.ticket.seat.join(),
        };
        this.$store.dispatch("buyTicket", data);
      } else alert("Please fill the checkbox");
    },
    close() {
      this.$store.commit("set_dialog", false);
    },
  },
};
</script>

<style lang="scss" scoped>
.head {
  display: flex;
  justify-content: center;
  padding-top: 1.5rem;
  font-weight: 400;
  color: grey;
}

.content {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 1rem;

  & span {
    font-weight: 500;
    color: #4192df;
  }
}
</style>