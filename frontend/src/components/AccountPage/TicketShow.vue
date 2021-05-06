<template>
  <div class="ticket-part">
    <h3 class="pb-12">Your Ticket Booking</h3>

    <v-row class="d-flex justify-center">
      <v-col
        class="d-flex justify-center pb-4"
        v-for="(ticket, index) in tickets"
        :key="index"
        cols="12"
        sm="6"
        md="6"
        lg="6"
      >
        <Ticket :ticket="ticket"></Ticket>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  components: {
    Ticket: () => import("../BookPage/Ticket"),
  },
  props: {
    tickets: Array,
  },
};
</script>

<style lang="scss" scoped>
.row {
  width: 85%;
}
.ticket-part {
  width: 70%;
  padding: 5% 0;
  display: flex;
  flex-direction: column;
  align-items: center;

  h3 {
    font-size: 2.5vw;
    color: #358cdd;
  }
}
</style>