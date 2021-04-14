<template>
  <div class="noreserve-part">
    <h3 class="pb-12">Your Ticket Booking</h3>
    <span>No Booking Ticket</span>
    <v-btn
      link
      :to="{ name: 'Movies' }"
      color="yellow darken-1"
      class="white--text"
    >
      See Movies
    </v-btn>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.noreserve-part {
  width: 70%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 5% 0;

  & h3 {
    font-size: 3vw;
    color: transparent;
    -webkit-text-stroke: 2px #358cdd;
  }

  & span {
    padding: 10% 0 2% 0;
    font-size: 1.7vw;
    color: rgba(0, 0, 0, 0.541);
  }
}
</style>