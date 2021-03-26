<template>
  <div class="d-flex flex-column align-center">
    <h1 class="header">Movies</h1>
    <SearchBar @search="search"></SearchBar>

    <ViewMovie v-if="isSearch" :movies="selectMovies"></ViewMovie>
    <ViewMovie v-else :movies="movies"></ViewMovie>
  </div>
</template>

<script>
export default {
  metaInfo: {
    title: "Movies",
  },
  components: {
    SearchBar: () => import("../components/MoviesPage/SearchBar"),
    ViewMovie: () => import("../components/MoviesPage/ViewMovie"),
  },
  data() {
    return {
      movies: {
        nowShowing: [
          {
            id: 1,
            title: "Aladdin",
            type: "Now Showing",
            theatre: "1",
            profit: 4900,
            releaseDate: "2021-02-15",
            synopsis:
              "A kind-hearted street urchin and a power-hungry Grand Vizier vie for a magic lamp that has the power to make their deepest wishes come true.",
            genre: ["Adventure", "Family", "Fantasy"],
          },
          {
            id: 2,
            title: "Avenger End Game",
            type: "Now Showing",
            theatre: "2",
            profit: 32000,
            releaseDate: "2021-02-21",
            synopsis:
              "After the devastating events of Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
            genre: ["Action", "Adventure", "Drama"],
          },
          {
            id: 3,
            title: "Narnia",
            type: "Now Showing",
            theatre: "3",
            profit: 246240,
            releaseDate: "2021-02-18",
            synopsis:
              "Four kids travel through a wardrobe to the land of Narnia and learn of their destiny to free it with the guidance of a mystical lion.",
            genre: ["Adventure", "Family", "Fantasy"],
          },
          {
            id: 4,
            title: "Beauty and The Beast",
            type: "Now Showing",
            theatre: "4",
            profit: 4100,
            releaseDate: "2021-02-14",
            synopsis:
              "A selfish Prince is cursed to become a monster for the rest of his life, unless he learns to fall in love with a beautiful young woman he keeps prisoner.",
            genre: ["Family", "Fantasy", "Musical"],
          },
          {
            id: 5,
            title: "Enchanted",
            type: "Now Showing",
            theatre: "5",
            profit: 47400,
            releaseDate: "2021-02-20",
            synopsis:
              "A young maiden in a land called Andalasia, who is prepared to be wed, is sent away to New York City by an evil Queen, where she falls in love with a lawyer.",
            genre: ["Animation", "Comedy", "Family"],
          },
          {
            id: 6,
            title: "Inside Out",
            type: "Now Showing",
            theatre: "6",
            profit: 37560,
            releaseDate: "2021-02-10",
            synopsis:
              "After young Riley is uprooted from her Midwest life and moved to San Francisco, her emotions - Joy, Fear, Anger, Disgust and Sadness - conflict on how best to navigate a new city, house, and school.",
            genre: ["Animation", "Adventure", "Comedy "],
          },
        ],
        comingSoon: [
          {
            id: 7,
            title: "Onward",
            type: "Coming Soon",
            profit: 0,
            releaseDate: "2021-03-08",
            synopsis:
              "Two elven brothers embark on a quest to bring their father back for one day.",
            genre: ["Animation", "Adventure", "Comedy "],
          },
          {
            id: 8,
            title: "How to Train Your Dragon",
            type: "Coming Soon",
            profit: 0,
            releaseDate: "2021-03-12",
            synopsis:
              "A hapless young Viking who aspires to hunt dragons becomes the unlikely friend of a young dragon himself, and learns there may be more to the creatures than he assumed.",
            genre: ["Animation", "Adventure", "Adventure "],
          },
          {
            id: 9,
            title: "Ready Player One",
            type: "Coming Soon",
            profit: 0,
            releaseDate: "2021-04-10",
            synopsis:
              "When the creator of a virtual reality called the OASIS dies, he makes a posthumous challenge to all OASIS users to find his Easter Egg, which will give the finder his fortune and control of his world.",
            genre: ["Action", "Adventure", "Sci-Fi"],
          },
          {
            id: 10,
            title: "Artemis Fowl",
            type: "Coming Soon",
            profit: 0,
            releaseDate: "2021-04-16",
            synopsis:
              "Artemis Fowl, a young criminal prodigy, hunts down a secret society of fairies to find his missing father.",
            genre: ["Adventure", "Family", "Fantasy"],
          },
          {
            id: 11,
            title: "Howl's Moving Castle",
            type: "Coming Soon",
            profit: 0,
            releaseDate: "2021-05-3",
            synopsis:
              "When an unconfident young woman is cursed with an old body by a spiteful witch, her only chance of breaking the spell lies with a self-indulgent yet insecure young wizard and his companions in his legged, walking castle.",
            genre: ["Animation", "Adventure", "Family"],
          },
        ],
      },
      selectMovies: {
        nowShowing: [],
        comingSoon: [],
      },
      isSearch: false,
    };
  },
  methods: {
    search(key) {
      if (!key) {
        this.isSearch = false;
        return;
      }

      key = key.toLowerCase();
      this.selectMovies.nowShowing = this.movies.nowShowing.filter((movie) =>
        movie.title.toLowerCase().startsWith(key)
      );
      this.selectMovies.comingSoon = this.movies.comingSoon.filter((movie) =>
        movie.title.toLowerCase().startsWith(key)
      );
      this.isSearch = true;
    },
  },
};
</script>

<style lang="scss" scoped>
.header {
  font-size: 5vw;

  margin-top: 2rem;
  color: transparent;
  -webkit-text-stroke: 2.5px #ebc858;
}
</style>