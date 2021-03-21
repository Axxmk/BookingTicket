<template>
  <div id="part-flex">
    <div class="header">
      <hr />
      <h1>MONTHEARA</h1>
      <hr />
    </div>
    <Carousel></Carousel>
    <ViewMovie :movies="movies"></ViewMovie>
    <hr class="line" />
    <BoxOffice :movies="ranking()"></BoxOffice>
  </div>
</template>

<script>
export default {
  components: {
    Carousel: () => import("../components/HomePage/Carousel"),
    ViewMovie: () => import("../components/MoviesPage/ViewMovie"),
    BoxOffice: () => import("../components/HomePage/BoxOffice"),
  },
  metaInfo: {
    title: "Home",
  },
  data() {
    return {
      movies: {
        nowShowing: [
          {
            id: 1,
            title: "Aladdin",
            type: 1,
            profit: 4900,
            releaseDate: "2021 Feb 18",
          },
          {
            id: 2,
            title: "Avenger End Game",
            type: 1,
            profit: 32000,
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
            profit: 4100,
            releaseDate: "2021 Feb 18",
          },
          {
            id: 5,
            title: "Enchanted",
            type: 1,
            profit: 47400,
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
  methods: {
    ranking() {
      const sortedList = [...this.movies.nowShowing];
      sortedList.sort((a, b) => b.profit - a.profit);
      return sortedList.slice(0, 4);
    },
  },
};
</script>

<style lang="scss" scoped>
#part-flex {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 2rem;

  .header {
    display: flex;
    justify-content: center;
    align-items: center;

    & hr {
      width: 270px;
      border: 1px solid #f7e78d;
      background-color: #f7e78d;
    }

    & h1 {
      letter-spacing: 5px;
      padding: 0 2.5%;
      font-size: 3.5vw;
      color: transparent;
      -webkit-text-stroke: 2px #ebc858;
    }
  }

  .line {
    margin: 3% 0;
    width: 40vw;
    border: 1.5px solid #f7e78d;
    background-color: #f7e78d;
  }
}
</style>
