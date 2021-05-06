<template>
  <div class="contain">
    <div class="ticket-part">
      <h3>Ticket Booking</h3>
      <Ticket :ticket="ticket"></Ticket>
    </div>

    <div class="checkout-part">
      <h3>Total Checkout</h3>
      <Checkout :price="ticket.price"></Checkout>
      <p>Payment Method: Banking</p>
    </div>
  </div>
</template>

<script>
export default {
  components: {
    Ticket: () => import("./Ticket"),
    Checkout: () => import("./Checkout"),
  },
  computed: {
    ticket() {
      return this.$store.getters.bookingInfo;
    },
  },
};
</script>

<style lang="scss" scoped>
.contain {
  position: fixed;
  right: 0;
  width: 25%;
  height: 100%;

  display: flex;
  flex-direction: column;

  overflow-y: scroll;
  &::-webkit-scrollbar {
    display: none;
  }

  & h3 {
    color: white;
    font-weight: 500;
  }

  .ticket-part {
    display: flex;
    flex-direction: column;
    justify-content: center;
    z-index: 10;
    padding: 7%;
    width: 98%;
    height: 100%;
    margin: 3% auto;

    border-radius: 10px;
    background-color: #4197e7;
    box-shadow: 0 1px 8px 0 #52a1eb;

    & h3 {
      padding-bottom: 6%;
    }
  }

  .checkout-part {
    margin: 0.5% auto;
    margin-bottom: 5%;
    width: 98%;
    height: fit-content;
    padding: 6%;

    border-radius: 20px;
    background-color: white;
    box-shadow: 0 1px 6px 0 rgba(194, 194, 194, 0.582);

    & h3 {
      color: #4197e7;
      padding: 2% 0 4% 0;
    }

    & p {
      margin-bottom: 0;
      padding-top: 4%;
      font-weight: 500;
      font-size: 0.95em;
      color: #eed444;
    }
  }
}
</style>