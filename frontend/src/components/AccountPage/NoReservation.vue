<template>
  <div class="noreserve-part">
    <h3 class="pb-12">Your Booking History</h3>
    <v-icon size="30" color="yellow darken-1">mdi-ticket</v-icon>
    <span>No Booking Ticket</span>
    <v-btn link :to="{ name: 'Movies' }" color="yellow darken-2" plain>
      See Movies
      <v-icon>mdi-arrow-top-right</v-icon>
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
    font-size: 2.5vw;
    color: #358cdd;
    margin-bottom: 10%;
  }

  & span {
    padding: 1% 0;
    font-size: 1.7vw;
    color: rgba(0, 0, 0, 0.541);
  }
}
</style>