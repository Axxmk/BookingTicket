<template>
  <v-carousel
    v-model="model"
    cycle
    hide-delimiter-background
    height="570"
    show-arrows
    class="px-6 mt-2 mb-10"
  >
    <v-carousel-item
      v-for="(item, i) in items"
      :key="i"
      :src="item.src"
    ></v-carousel-item>
  </v-carousel>
</template>

<script>
export default {
  data: () => ({
    model: 0,
    items: [
      {
        src: "https://images3.alphacoders.com/652/thumb-1920-652670.jpg",
      },
      {
        src:
          "https://www.themoviedb.org/t/p/original/cYdHaKx6Ihj1BIvbxpCxIGXfZ02.jpg",
      },
      {
        src: "https://cdn.wallpapersafari.com/82/59/0upGjg.jpg",
      },
      {
        src: "https://wallpaperaccess.com/full/750166.jpg",
      },
    ],
  }),
};
</script>