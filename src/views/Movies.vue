<template>
  <div>
    <ViewMovie :movies="movies"></ViewMovie>
  </div>
</template>

<script>
export default {
  metaInfo: {
    title: "Movies",
  },
  components: {
    ViewMovie: () => import("../components/HomePage/ViewMovie"),
  },
  data() {
    return {
      movies: {
        nowShowing: [
          {
            id: 1,
            title: "Aladdin",
            type: 1,
            profit: 1000,
            releaseDate: "2021 Feb 18",
          },
          {
            id: 2,
            title: "Avenger End Game",
            type: 1,
            profit: 320,
            releaseDate: "2021 Feb 18",
          },
          {
            id: 3,
            title: "Narnia",
            type: 1,
            profit: 246240,
            releaseDate: "2021 Feb 18",
          },
          {
            id: 4,
            title: "Beauty and The Beast",
            type: 1,
            profit: 410,
            releaseDate: "2021 Feb 18",
          },
          {
            id: 5,
            title: "Enchanted",
            type: 1,
            profit: 4740,
            releaseDate: "2021 Feb 18",
          },
          {
            id: 6,
            title: "Inside Out",
            type: 1,
            profit: 37560,
            releaseDate: "2021 Feb 18",
          },
        ],
        comingSoon: [
          {
            id: 1,
            title: "Aladdin",
            type: 0,
            profit: 0,
            releaseDate: "2021 Feb 18",
          },
          {
            id: 2,
            title: "Avenger End Game",
            type: 0,
            profit: 0,
            releaseDate: "2021 Feb 18",
          },
          {
            id: 3,
            title: "Narnia",
            type: 0,
            profit: 0,
            releaseDate: "2021 Feb 18",
          },
          {
            id: 4,
            title: "Beauty and the beast",
            type: 0,
            profit: 0,
            releaseDate: "2021 Feb 18",
          },
          {
            id: 5,
            title: "Enchanted",
            type: 0,
            profit: 0,
            releaseDate: "2021 Feb 18",
          },
          {
            id: 6,
            title: "Inside Out",
            type: 0,
            profit: 0,
            releaseDate: "2021 Feb 18",
          },
        ],
      },
    };
  },
};
</script>

<style>
</style>