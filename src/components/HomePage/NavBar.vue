<template>
  <div id="nav-bar">
    <SearchBar></SearchBar>
    <div id="selection-wrap">
      <SelectionBar :movies="movies" :cinemas="cinemas"></SelectionBar>
    </div>
  </div>
</template>

<script>
export default {
  components: {
    SearchBar: () => import("./SearchBar"),
    SelectionBar: () => import("./SelectionBar"),
  },
  props: {
    movies: Array,
    cinemas: Array,
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

  #selection-wrap {
    display: flex;
    flex: 1;
    flex-flow: column;
    position: relative;
    z-index: 1000;
  }

  @media screen and (max-width: 768px) {
    flex-direction: column;
    align-items: center;
    padding: 0;

    #selection-wrap {
      width: 80%;
    }
  }
}
</style>