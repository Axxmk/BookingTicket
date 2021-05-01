<template>
  <v-card class="pa-6 pb-4 rounded-lg" width="20rem">
    <TicketInfo :ticket="ticket"></TicketInfo>
    <hr class="divider" />
    <p class="code">{{ code }}</p>
    <footer>MonTheara Cinema</footer>
  </v-card>
</template>

<script>
export default {
  components: {
    TicketInfo: () => import("../BookPage/TicketInfo"),
  },
  props: {
    ticket: Object,
  },
  computed: {
    code() {
      if (this.ticket.seat.length > 0) {
        return (
          this.ticket.title.substring(0, 2) +
          this.ticket.start_time.substring(0, 2) +
          this.ticket.theatre +
          this.ticket.seat[0]
        );
      } else {
        return "";
      }
    },
  },
};
</script>

<style lang="scss" scoped>
hr.divider {
  margin: 12% 0 6% 0;
  border-top: 2px dashed rgb(206, 206, 206);
  border-bottom: none;
}

.code {
  display: flex;
  justify-content: center;
  font-size: 1.6rem;
  font-weight: 500;
}

footer {
  text-align: center;
  font-style: italic;
  color: #ddc32e75;
}
</style>