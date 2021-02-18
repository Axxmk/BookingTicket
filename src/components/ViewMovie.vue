<template>
  <div id="part-movie">
    <v-tabs v-model="currentTab" background-color="#F5F5F5" centered>
      <v-tab v-for="item in items" :key="item.tab">
        {{ item.tab }}
      </v-tab>
    </v-tabs>

    <v-tabs-items v-model="currentTab" class="my-10 tab-show">
      <v-tab-item v-for="item in items" :key="item.tab">
        <v-row>
          <v-col
            cols="12"
            sm="6"
            md="3"
            v-for="content in item.contents"
            :key="content.id"
          >
            <Card :movie="content"></Card>
          </v-col>
        </v-row>
      </v-tab-item>
    </v-tabs-items>
  </div>
</template>

<script>
export default {
  components: {
    Card: () => import("../components/Card"),
  },
  data() {
    return {
      currentTab: null,
      items: [
        {
          tab: "Now Showing",
          contents: [
            {
              id: 1,
              title: "Aladdin",
              type: "",
              profit: 0,
              releaseDate: "2021 Feb 18",
            },
            {
              id: 2,
              title: "Avenger End Game",
              type: "",
              profit: 0,
              releaseDate: "2021 Feb 18",
            },
            {
              id: 3,
              title: "Narnia",
              type: "",
              profit: 0,
              releaseDate: "2021 Feb 18",
            },
            {
              id: 4,
              title: "Beauty and the beast",
              type: "",
              profit: 0,
              releaseDate: "2021 Feb 18",
            },
            {
              id: 5,
              title: "Enchanted",
              type: "",
              profit: 0,
              releaseDate: "2021 Feb 18",
            },
            {
              id: 6,
              title: "Star Wars Episode VII",
              type: "",
              profit: 0,
              releaseDate: "2021 Feb 18",
            },
          ],
        },
        {
          tab: "Coming Soon",
          contents: [
            {
              id: 1,
              title: "Aladdin",
              type: "",
              profit: 0,
              releaseDate: "2021 Feb 18",
            },
            {
              id: 2,
              title: "Avenger End Game",
              type: "",
              profit: 0,
              releaseDate: "2021 Feb 18",
            },
            {
              id: 3,
              title: "Narnia",
              type: "",
              profit: 0,
              releaseDate: "2021 Feb 18",
            },
            {
              id: 4,
              title: "Beauty and the beast",
              type: "",
              profit: 0,
              releaseDate: "2021 Feb 18",
            },
            {
              id: 5,
              title: "Enchanted",
              type: "",
              profit: 0,
              releaseDate: "2021 Feb 18",
            },
            {
              id: 6,
              title: "Star Wars Episode VII",
              type: "",
              profit: 0,
              releaseDate: "2021 Feb 18",
            },
          ],
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped>
#part-movie {
  margin: 3% 0;
  width: 95%;

  .tab-show {
    background-color: transparent;
  }
}
</style>