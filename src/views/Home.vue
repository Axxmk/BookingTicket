<template>
  <div id="part-flex">
    <NavBar></NavBar>
    <Carousel></Carousel>
  </div>
</template>

<script>
export default {
  components: {
    NavBar: () => import("../components/NavBar"),
    Carousel: () => import("../components/Carousel"),
  },
};
</script>

<style scoped>
#part-flex {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>