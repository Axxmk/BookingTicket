<template>
  <div id="nav-bar">
    <div id="search-bar">
      <i
        @click="searchMovie"
        class="material-icons icon-search"
        style="font-size: 1.1rem; color: #f3d465; cursor: pointer"
      >
        search
      </i>
      <input
        @keydown.enter="searchMovie"
        v-model="search"
        type="text"
        placeholder="Search"
      />
    </div>

    <div id="selected-box">
      <div id="showtime-bar">
        <div
          v-for="(option, index) in selection"
          :key="index"
          class="selected-type"
          @click="typeSelected(option)"
        >
          <span>Select {{ option }}</span>
          <i class="material-icons icon-dropdown"> arrow_drop_down </i>
        </div>

        <button @click="selectShowtime" class="showtime-btn">Showtime</button>
      </div>

      <div id="selected-data" v-if="showSheet">
        <template v-if="showSheet == 'Movie'">Movie</template>
        <template v-else-if="showSheet == 'Cinema'">Cinema</template>
      </div>
    </div>

    <div v-if="showSheet" class="overlay" @click="showSheet = null"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      search: "",
      selection: ["Movie", "Cinema"],
      selected: {
        movie: "",
        cinema: "",
      },
      showSheet: null,
    };
  },
  methods: {
    searchMovie() {
      if (!this.search) {
        console.log("ts");
        return;
      }

      this.search = "";
    },
    typeSelected(type) {
      if (type == "Movie") {
        this.selectMovie();
      } else if (type == "Cinema") {
        this.selectCinema();
      }
    },
    selectMovie() {
      if (this.showSheet == "Movie") {
        this.showSheet = null;
      } else {
        this.showSheet = "Movie";
      }
    },
    selectCinema() {
      if (this.showSheet == "Cinema") {
        this.showSheet = null;
      } else {
        this.showSheet = "Cinema";
      }
    },
    selectShowtime() {
      if (!this.selected.movie || !this.selected.cinema) {
        alert("Please Select First");
        return;
      }
    },
  },
};
</script>

<style lang="scss" scoped>
#nav-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;

  padding: 0 6rem;
  width: 100%;
  box-sizing: border-box;

  @media screen and (max-width: 768px) {
    flex-direction: column;
    align-items: center;
    padding: 0;

    #search-bar {
      margin-bottom: 1rem;
      margin-right: 0;
      width: 80%;
    }

    #selected-box {
      width: 80%;
    }
  }
}
#search-bar {
  box-sizing: border-box;
  display: flex;
  align-items: center;
  padding: 0.7rem;
  margin-right: 20%;
  border-radius: 17px;
  background-color: #ffffff;
  box-shadow: 0 2px 2px 0 #e5e5e5;
  width: 19.5rem;
  height: 2.2rem;
  transition: all 0.1s ease-in-out;
  input {
    padding-left: 0.5rem;
    width: 90%;
    font-size: 0.85rem;
    color: #424242;
    &:focus {
      outline: none;
    }
  }
  &:focus-within {
    transform: scale(1.05);
    box-shadow: 1px 1px 4px 0 lighten($color: #f9e395, $amount: 9);
  }
}
#selected-box {
  display: flex;
  flex: 1;
  flex-flow: column;
  position: relative;
  z-index: 1000;
}

#showtime-bar {
  display: flex;
  align-items: center;
  justify-content: space-evenly;

  padding: 0.7rem;
  width: 100%;
  height: 2.4rem;

  border-radius: 17px;
  background-color: #ffffff;
  box-shadow: 0 2px 2px 0 #e5e5e5;
  transition: all 0.1s ease-in-out;

  &:hover {
    transform: scale(1.02);
  }
}
.selected-type {
  display: flex;
  justify-content: space-between;
  width: 8rem;
  padding-left: 0.7rem;
  border-bottom: 1.5px solid #f3d465;
  cursor: pointer;
  span {
    color: #424242;
    font-size: 0.8rem;
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
  font-size: 0.75rem;
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
#selected-data {
  position: absolute;
  width: 100%;
  height: 400px;
  margin-top: 3rem;
  padding: 1.4rem;
  background: #fff;
  border-radius: 20px;
  box-shadow: 0 2px 6px 0 #d3d3d3;
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