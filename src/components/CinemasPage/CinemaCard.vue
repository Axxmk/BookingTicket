<template>
  <v-card width="95%">
    <p>Theatre {{ theatre.theatreId }}</p>
    <div class="px-3">
      <v-img height="200" class="mx-1" src="../../assets/cinema.jpg"></v-img>
    </div>
    <h5>Now Showing</h5>
    <span>{{ theatre.movieShow }}</span>
    <v-btn
      text
      class="yellow--text text--darken-1"
      link
      :to="{ name: 'Booking', params: { id: theatre.theatreId } }"
    >
      Buy Ticket
    </v-btn>
  </v-card>
</template>

<script>
export default {
  props: {
    theatre: Object,
  },
};
</script>

<style lang="scss" scoped>
.v-sheet.v-card {
  transition: all 0.2s ease-in-out;
  margin: 2% auto;
  padding: 3%;
  border-radius: 10px;

  &:hover {
    transform: scale(1.05) translateY(-5px);
    box-shadow: 0px 3px 8px -1px rgb(0, 0, 0, 0.05),
      0px 5px 16px 1px rgb(0, 0, 0, 0.05), 0px 2px 20px 1px rgb(0, 0, 0, 0.05);
  }

  & p {
    color: rgba(128, 128, 128, 0.603);
    font-size: 0.9em;
    text-align: end;
    padding: 2% 5%;
    margin-bottom: 3%;
  }

  & h5 {
    padding: 6% 5% 2% 7%;
    font-weight: 400;
    color: rgb(168, 168, 168);
  }

  & span {
    display: block;
    font-size: 1.1em;
    font-weight: 500;
    padding: 0 7%;
    margin-bottom: 2%;
  }
}
</style>