<template>
  <div class="part">
    <BookingStep></BookingStep>
    <BookingResult></BookingResult>
  </div>
</template>

<script>
export default {
  metaInfo: {
    title: "Booking",
  },
  components: {
    BookingStep: () => import("../components/BookPage/BookingStep"),
    BookingResult: () => import("../components/BookPage/BookingResult"),
  },
  mounted() {
    this.$store.dispatch("clearInfo");
  },
};
</script>

<style lang="scss" scoped>
.part {
  display: flex;
  justify-content: space-between;
  width: 100%;
}
</style>