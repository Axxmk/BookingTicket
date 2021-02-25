<template>
  <div id="search-bar">
    <i @click="searchMovie" class="material-icons icon-search"> search </i>
    <input
      @keydown.enter="searchMovie"
      v-model="search"
      type="text"
      placeholder="Search"
    />
  </div>
</template>

<script>
export default {
  data() {
    return {
      search: "",
    };
  },
  methods: {
    searchMovie() {
      if (!this.search) {
        alert("Please Type Something");
        return;
      } else {
        this.$router.push({ name: "Movies" });
        this.search = "";
      }
    },
  },
};
</script>

<style lang="scss" scoped>
#search-bar {
  box-sizing: border-box;
  display: flex;
  align-items: center;
  padding: 0.7rem;
  margin-right: 18%;
  border-radius: 17px;
  background-color: #ffffff;
  box-shadow: 0 2px 2px 0 #e5e5e5;
  width: 22rem;
  height: 2.5rem;
  transition: all 0.1s ease-in-out;

  .icon-search {
    font-size: 1.3rem;
    color: #f3d465;
    cursor: pointer;
  }

  input {
    padding-left: 0.5rem;
    width: 90%;
    font-size: 0.95rem;
    color: #424242;

    &:focus {
      outline: none;
    }
  }

  &:focus-within {
    box-shadow: 2px 2px 4px 0 #cecece;
  }

  &:focus-within .icon-search {
    color: #63b2fc;
  }
  @media screen and (max-width: 768px) {
    margin-bottom: 1rem;
    margin-right: 0;
    width: 80%;
  }
}
</style>