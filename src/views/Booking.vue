<template>
  <div class="part">
    <BookingStep :movieInfo="movieInfo"></BookingStep>
    <TicketPart></TicketPart>
  </div>
</template>

<script>
export default {
  metaInfo: {
    title: "Booking",
  },
  components: {
    BookingStep: () => import("../components/BookPage/BookingStep"),
    TicketPart: () => import("../components/BookPage/TicketPart"),
  },
  data() {
    return {
      movieInfo: {
        allDate: [
          "1 Mar 2021",
          "2 Mar 2021",
          "3 Mar 2021",
          "4 Mar 2021",
          "5 Mar 2021",
          "6 Mar 2021",
          "7 Mar 2021",
          "8 Mar 2021",
          "9 Mar 2021",
          "10 Mar 2021",
        ],
        allTime: ["11:50", "14:30", "15:50", "17:10", "19:50"],
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
    };
  },
};
</script>

<style lang="scss" scoped>
.part {
  display: flex;
  justify-content: space-between;
  width: 100vw;
}
</style>