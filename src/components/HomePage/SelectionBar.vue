<template>
  <div>
    <div id="selected-box">
      <div id="showtime-bar">
        <div
          v-for="(option, index) in selection"
          :key="index"
          class="selected-type"
          @click="typeSelected(option)"
        >
          <span :id="option">Select {{ option }}</span>
          <i class="material-icons icon-dropdown"> arrow_drop_down </i>
        </div>

        <button @click="selectShowtime" class="showtime-btn">Showtime</button>
      </div>

      <div id="selected-data" v-if="showSheet">
        <template v-if="showSheet == 'Movie'">
          <v-row>
            <v-col
              cols="12"
              sm="6"
              md="6"
              lg="4"
              v-for="movie in movies"
              :key="movie.id"
              @click="selectMovie(movie.id)"
            >
              <MovieCardSL
                :isActive="movie.id == selected.movie ? true : false"
                :movie="movie"
              ></MovieCardSL>
            </v-col>
          </v-row>
        </template>

        <template v-else-if="showSheet == 'Cinema'">
          <v-row>
            <v-col
              cols="12"
              v-for="cinema in cinemas"
              :key="cinema.id"
              @click="selectCinema(cinema.id)"
            >
              <CinemaSL
                :isActive="cinema.id == selected.cinema ? true : false"
                :cinema="cinema"
              ></CinemaSL>
            </v-col>
          </v-row>
        </template>
      </div>
    </div>

    <div v-if="showSheet" class="overlay" @click="showSheet = null"></div>
  </div>
</template>

<script>
export default {
  components: {
    MovieCardSL: () => import("./MovieCardSL"),
    CinemaSL: () => import("./CinemaSL"),
  },
  props: {
    movies: Array,
    cinemas: Array,
  },
  data() {
    return {
      selection: ["Movie", "Cinema"],
      selected: {
        movie: -1,
        cinema: -1,
      },
      showSheet: null,
    };
  },
  methods: {
    typeSelected(type) {
      if (type == "Movie") {
        if (this.showSheet == "Movie") {
          this.showSheet = null;
        } else {
          this.showSheet = "Movie";
        }
      } else if (type == "Cinema") {
        if (this.showSheet == "Cinema") {
          this.showSheet = null;
        } else {
          this.showSheet = "Cinema";
        }
      }
    },
    selectMovie(id) {
      this.selected.movie = id;

      const selectMovie = this.movies.find((elem) => elem.id == id);
      const spanElem = document.getElementById("Movie");
      spanElem.innerText = selectMovie.title;
    },
    selectCinema(id) {
      this.selected.cinema = id;

      const selectCinema = this.cinemas.find((elem) => elem.id == id);
      const spanElem = document.getElementById("Cinema");
      spanElem.innerText = selectCinema.branch;
    },
    selectShowtime() {
      if (this.selected.movie < 0 || this.selected.cinema < 0) {
        alert("Please Select First");
        return;
      }
    },
  },
};
</script>

<style lang="scss" scoped>
#selected-box {
  display: flex;
  flex: 1;
  flex-flow: column;
  position: relative;
  z-index: 1000;

  #showtime-bar {
    display: flex;
    align-items: center;
    justify-content: space-evenly;

    padding: 0.7rem;
    width: 100%;
    height: 2.6rem;

    border-radius: 17px;
    background-color: #ffffff;
    box-shadow: 0 2px 2px 0 #e5e5e5;
    transition: all 0.1s ease-in-out;

    .selected-type {
      display: flex;
      justify-content: space-between;
      width: 10vw;
      padding-left: 0.7rem;
      border-bottom: 1.5px solid #f3d465;
      cursor: pointer;

      span {
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 1;
        -webkit-box-orient: vertical;

        color: #424242;
        font-size: 0.88em;
      }
      .icon-dropdown {
        font-size: 1.3rem;
        color: #f3d465;
      }
      &:hover {
        border-bottom: 1.5px solid #63b2fc;

        .icon-dropdown {
          color: #63b2fc;
        }
      }
    }

    .showtime-btn {
      padding: 0.7% 3%;
      font-size: 0.85rem;
      font-weight: bold;
      color: #ffffff;
      text-shadow: 0px 0px 3px #3c8cd6;
      background-color: #63b2fc;
      box-shadow: 0 0 5px 0 #cde3f6;
      border-radius: 7px;
      transition: all 0.003s;

      &:focus {
        outline: none;
      }

      &:active {
        transform: scale(0.9);
        opacity: 0.9;
      }
    }
  }

  #selected-data {
    position: absolute;
    overflow-y: scroll;

    width: 100%;
    max-height: 400px;
    margin-top: 3rem;
    padding: 1.4rem;

    background: #fff;
    border-radius: 10px;
    box-shadow: 0 2px 6px 0 #d3d3d3;

    &::-webkit-scrollbar {
      width: 10px;
    }

    /* Handle */
    &::-webkit-scrollbar-thumb {
      background: #aad6ff;
      border-radius: 5px;
    }

    /* Handle on hover */
    &::-webkit-scrollbar-thumb:hover {
      background: #7bbbf7;
    }
  }

  @media screen and (max-width: 768px) {
    flex-direction: column;
    align-items: center;
    padding: 0;

    #selected-box {
      width: 80%;
    }
  }
}

.overlay {
  position: absolute;
  z-index: 500;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background: transparent;
}
</style>