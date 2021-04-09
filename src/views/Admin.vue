<template>
  <div>
    <h1 class="header">Admin Dashboard</h1>
    <div style="width: 90%" class="mx-auto">
      <v-data-table
        :headers="headers"
        :items="movies"
        item-key="id"
        :single-expand="true"
        show-expand
        sort-by="status"
        :sort-desc="true"
        class="elevation-1 mb-8 px-4"
      >
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title style="color: #387dd8">Movies</v-toolbar-title>
            <v-divider class="mx-5" inset vertical></v-divider>
            <v-spacer></v-spacer>

            <v-btn
              outlined
              plain
              color="blue darken-2"
              @click="dialogNew = true"
            >
              <v-icon class="pr-2" size="17" color="blue darken-2">
                mdi-plus
              </v-icon>
              Add Movie
            </v-btn>

            <v-dialog v-model="dialogNew" max-width="550px" persistent>
              <NewMovieData @close="dialogNew = false"></NewMovieData>
            </v-dialog>
          </v-toolbar>
        </template>

        <template v-slot:item="{ item, expand, isExpanded }">
          <tr>
            <td>{{ item.title }}</td>
            <td>{{ item.status }}</td>
            <td>{{ item.releaseDate }}</td>
            <td>{{ item.duration }}</td>
            <td>{{ item.revenue }}</td>
            <td>
              <v-icon
                small
                color="blue lighten-2"
                class="mr-3"
                @click="movieAction(item.id, 'edit')"
              >
                mdi-pencil
              </v-icon>
              <v-icon
                small
                color="blue lighten-2"
                @click="movieAction(item.id, 'delete')"
              >
                mdi-delete
              </v-icon>
            </td>
            <td>
              <v-icon @click="expand(!isExpanded)" size="18">
                mdi-chevron-down
              </v-icon>
            </td>
          </tr>

          <v-dialog
            v-if="item.id == editId"
            v-model="dialogEdit"
            max-width="550px"
          >
            <EditMovie :movie="item" @close="dialogEdit = false"></EditMovie>
          </v-dialog>

          <v-dialog
            v-if="item.id == deleteId"
            v-model="dialogDelete"
            max-width="400px"
          >
            <DeleteMovie
              :movieId="item.id"
              @closeDelete="dialogDelete = false"
            ></DeleteMovie>
          </v-dialog>
        </template>

        <template v-slot:expanded-item="{ headers, item }">
          <td class="pa-10" :colspan="headers.length">
            <MoreInfo :movie="item"></MoreInfo>
          </td>
        </template>
      </v-data-table>
    </div>
  </div>
</template>

<script>
export default {
  metaInfo: {
    title: "Admin",
  },
  components: {
    NewMovieData: () => import("../components/AdminPage/NewMovieData"),
    EditMovie: () => import("../components/AdminPage/EditMovie"),
    DeleteMovie: () => import("../components/AdminPage/DeleteMovie"),
    MoreInfo: () => import("../components/AdminPage/MoreInfo"),
  },
  data() {
    return {
      dialogNew: false,
      dialogDelete: false,
      dialogEdit: false,
      deleteId: 0,
      editId: 0,
      headers: [
        {
          text: "Movie Title",
          align: "start",
          value: "title",
        },
        { text: "Status", value: "status" },
        { text: "Release Date", value: "releaseDate" },
        { text: "Duration", value: "duration" },
        { text: "Revenue", value: "revenue" },
        { text: "Actions", value: "actions", sortable: false },
        { text: "", value: "data-table-expand" },
      ],
      editedIndex: -1,
      movies: [
        {
          id: 1,
          title: "Aladdin",
          status: "Now Showing",
          revenue: 4900,
          releaseDate: "2021-02-15",
          duration: 95,
          synopsis:
            "A kind-hearted street urchin and a power-hungry Grand Vizier vie for a magic lamp that has the power to make their deepest wishes come true.",
          genre: ["Adventure", "Family", "Fantasy"],
        },
        {
          id: 2,
          title: "Avenger End Game",
          status: "Now Showing",
          revenue: 32000,
          releaseDate: "2021-02-21",
          duration: 105,
          synopsis:
            "After the devastating events of Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
          genre: ["Action", "Adventure", "Drama"],
        },
        {
          id: 3,
          title: "Narnia",
          status: "Now Showing",
          revenue: 246240,
          releaseDate: "2021-02-18",
          duration: 92,
          synopsis:
            "Four kids travel through a wardrobe to the land of Narnia and learn of their destiny to free it with the guidance of a mystical lion.",
          genre: ["Adventure", "Family", "Fantasy"],
        },
        {
          id: 4,
          title: "Beauty and The Beast",
          status: "Now Showing",
          revenue: 4100,
          releaseDate: "2021-02-14",
          duration: 120,
          synopsis:
            "A selfish Prince is cursed to become a monster for the rest of his life, unless he learns to fall in love with a beautiful young woman he keeps prisoner.",
          genre: ["Family", "Fantasy", "Musical"],
        },
        {
          id: 5,
          title: "Enchanted",
          status: "Now Showing",
          revenue: 47400,
          releaseDate: "2021-02-20",
          duration: 114,
          synopsis:
            "A young maiden in a land called Andalasia, who is prepared to be wed, is sent away to New York City by an evil Queen, where she falls in love with a lawyer.",
          genre: ["Animation", "Comedy", "Family"],
        },
        {
          id: 6,
          title: "Inside Out",
          status: "Now Showing",
          revenue: 37560,
          releaseDate: "2021-02-10",
          duration: 98,
          synopsis:
            "After young Riley is uprooted from her Midwest life and moved to San Francisco, her emotions - Joy, Fear, Anger, Disgust and Sadness - conflict on how best to navigate a new city, house, and school.",
          genre: ["Animation", "Adventure", "Comedy "],
        },
        {
          id: 7,
          title: "Onward",
          status: "Coming Soon",
          revenue: 0,
          releaseDate: "2021-03-08",
          duration: 102,
          synopsis:
            "Two elven brothers embark on a quest to bring their father back for one day.",
          genre: ["Animation", "Adventure", "Comedy "],
        },
        {
          id: 8,
          title: "How to Train Your Dragon",
          status: "Coming Soon",
          revenue: 0,
          releaseDate: "2021-03-12",
          duration: 125,
          synopsis:
            "A hapless young Viking who aspires to hunt dragons becomes the unlikely friend of a young dragon himself, and learns there may be more to the creatures than he assumed.",
          genre: ["Animation", "Adventure"],
        },
        {
          id: 9,
          title: "Ready Player One",
          status: "Coming Soon",
          revenue: 0,
          releaseDate: "2021-04-10",
          duration: 117,
          synopsis:
            "When the creator of a virtual reality called the OASIS dies, he makes a posthumous challenge to all OASIS users to find his Easter Egg, which will give the finder his fortune and control of his world.",
          genre: ["Action", "Adventure", "Sci-Fi"],
        },
        {
          id: 10,
          title: "Artemis Fowl",
          status: "Coming Soon",
          revenue: 0,
          releaseDate: "2021-04-16",
          duration: 90,
          synopsis:
            "Artemis Fowl, a young criminal prodigy, hunts down a secret society of fairies to find his missing father.",
          genre: ["Adventure", "Family", "Fantasy"],
        },
        {
          id: 11,
          title: "Howl's Moving Castle",
          status: "Coming Soon",
          revenue: 0,
          releaseDate: "2021-05-3",
          duration: 106,
          synopsis:
            "When an unconfident young woman is cursed with an old body by a spiteful witch, her only chance of breaking the spell lies with a self-indulgent yet insecure young wizard and his companions in his legged, walking castle.",
          genre: ["Animation", "Adventure", "Family"],
        },
      ],
    };
  },
  methods: {
    movieAction(id, action) {
      if (action == "edit") {
        this.editId = id;
        this.dialogEdit = true;
      } else if (action == "delete") {
        this.deleteId = id;
        this.dialogDelete = true;
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.header {
  text-align: center;
  font-size: 4.7vw;

  margin: 2rem 0;
  color: rgb(238, 247, 252);
  -webkit-text-stroke: 2.5px #ebc858;
}
</style>