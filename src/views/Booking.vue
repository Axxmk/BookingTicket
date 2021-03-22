<template>
  <div class="part">
    <BookingStep
      :theatreInfo="theatreInfo"
      @dataChange="dataChange"
    ></BookingStep>
    <TicketPart :ticket="ticket"></TicketPart>
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
  mounted() {
    console.log(this.$route.params.id);
    let id = this.$route.params.id;
    let movieList = [
      {
        id: 1,
        title: "Aladdin",
        type: 1,
        theatre: "1",
        profit: 4900,
        releaseDate: "2021 Feb 6",
        synopsis:
          "A kind-hearted street urchin and a power-hungry Grand Vizier vie for a magic lamp that has the power to make their deepest wishes come true.",
        genre: "Adventure, Family, Fantasy",
      },
      {
        id: 2,
        title: "Avenger End Game",
        type: 1,
        theatre: "2",
        profit: 32000,
        releaseDate: "2021 Feb 21",
        synopsis:
          "After the devastating events of Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
        genre: "Action, Adventure, Drama ",
      },
      {
        id: 3,
        title: "Narnia",
        type: 1,
        theatre: "3",
        profit: 246240,
        releaseDate: "2021 Feb 18",
        synopsis:
          "Four kids travel through a wardrobe to the land of Narnia and learn of their destiny to free it with the guidance of a mystical lion.",
        genre: "Adventure, Family, Fantasy",
      },
      {
        id: 4,
        title: "Beauty and The Beast",
        type: 1,
        theatre: "4",
        profit: 4100,
        releaseDate: "2021 Feb 4",
        synopsis:
          "A selfish Prince is cursed to become a monster for the rest of his life, unless he learns to fall in love with a beautiful young woman he keeps prisoner.",
        genre: "Family, Fantasy, Musical",
      },
      {
        id: 5,
        title: "Enchanted",
        type: 1,
        theatre: "5",
        profit: 47400,
        releaseDate: "2021 Feb 20",
        synopsis:
          "A young maiden in a land called Andalasia, who is prepared to be wed, is sent away to New York City by an evil Queen, where she falls in love with a lawyer.",
        genre: "Animation, Comedy, Family",
      },
      {
        id: 6,
        title: "Inside Out",
        type: 1,
        theatre: "6",
        profit: 37560,
        releaseDate: "2021 Feb 10",
        synopsis:
          "After young Riley is uprooted from her Midwest life and moved to San Francisco, her emotions - Joy, Fear, Anger, Disgust and Sadness - conflict on how best to navigate a new city, house, and school.",
        genre: "Animation, Adventure, Comedy ",
      },
    ];

    let selectMovie = movieList.find((movie) => movie.id == id);

    this.ticket.theatre = selectMovie.theatre;
    this.ticket.title = selectMovie.title;
  },
  data() {
    return {
      theatreInfo: {
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
      ticket: {
        theatre: "",
        title: "",
        time: "",
        seat: [],
        date: "",
        price: 0,
        payment: "",
      },
    };
  },
  methods: {
    dataChange(data, type) {
      switch (type) {
        case "date":
          this.ticket.date = data;
          break;
        case "seat":
          this.ticket.seat = data;
          this.ticket.price = data.length * 200;
          break;
        case "time":
          this.ticket.time = data;
          break;
        case "payment":
          this.ticket.payment = data;
          break;
      }
    },
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