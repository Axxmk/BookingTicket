<template>
  <div class="ticket-info">
    <div>
      <span>Theatre</span>
      <p>{{ ticket.theatre }}</p>
    </div>

    <div>
      <span>Movie Title</span>
      <p>{{ ticket.title }}</p>
    </div>

    <div class="part-row">
      <div>
        <span>ShowTime</span>
        <div>{{ ticket.time }}</div>
      </div>

      <div>
        <span>Seat No.</span>
        <div
          class="showSeat"
          style="display: inline"
          v-for="(seat, index) in ticket.seat"
          :key="index"
        >
          {{ seat }}
        </div>
      </div>
    </div>

    <div>
      <span>Date</span>
      <p>{{ ticket.date }}</p>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    ticket: Object,
  },
};
</script>

<style lang="scss" scoped>
.ticket-info {
  & > div {
    padding: 2%;
  }

  & div span {
    font-size: 0.8rem;
    color: #ddc32e;
    display: block;
  }

  & div p {
    font-size: 1.1em;
    font-weight: 500;
    margin-bottom: 0;
  }

  .part-row {
    display: flex;

    & div {
      margin-right: 2.5rem;
    }

    & div div {
      font-size: 1.1em;
      font-weight: 500;
    }

    @media screen and (max-width: 1042px) {
      flex-direction: column;

      & div {
        margin-right: 0;
      }
    }
  }
}
</style>