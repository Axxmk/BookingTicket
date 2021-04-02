<template>
  <div>
    <h1 class="header">Admin Dashboard</h1>
    <div style="width: 90%" class="mx-auto">
      <v-data-table
        :headers="headers"
        :items="movies"
        item-key="id"
        :expanded.sync="expanded"
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

            <v-dialog v-model="dialog" max-width="550px" persistent>
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  outlined
                  plain
                  v-bind="attrs"
                  v-on="on"
                  color="blue darken-2"
                >
                  <v-icon class="pr-2" size="17" color="blue darken-2">
                    mdi-plus
                  </v-icon>
                  Add Movie
                </v-btn>
              </template>

              <NewMovieData
                :formTitle="formTitle"
                :editedItem="editedItem"
                @close="close"
                @save="save"
              ></NewMovieData>
            </v-dialog>

            <v-dialog v-model="dialogDelete" max-width="400px">
              <DeleteSheet
                @closeDelete="closeDelete"
                @deleteItemConfirm="deleteItemConfirm"
              ></DeleteSheet>
            </v-dialog>
          </v-toolbar>
        </template>

        <template v-slot:item.actions="{ item }">
          <v-icon
            small
            color="blue lighten-2"
            class="mr-3"
            @click="editItem(item)"
          >
            mdi-pencil
          </v-icon>
          <v-icon small color="blue lighten-2" @click="deleteItem(item)">
            mdi-delete
          </v-icon>
        </template>

        <template v-slot:expanded-item="{ headers, item }">
          <td class="pa-8" :colspan="headers.length">
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
    DeleteSheet: () => import("../components/AdminPage/DeleteSheet"),
    MoreInfo: () => import("../components/AdminPage/MoreInfo"),
  },
  data() {
    return {
      dialog: false,
      dialogDelete: false,
      expanded: [],
      headers: [
        {
          text: "Movie Title",
          align: "start",
          value: "title",
        },
        { text: "Status", value: "status" },
        { text: "Release Date", value: "releaseDate" },
        { text: "Revenue", value: "revenue" },
        { text: "Actions", value: "actions", sortable: false },
        { text: "", value: "data-table-expand" },
      ],
      editedIndex: -1,
      editedItem: {
        title: "",
        status: "",
        revenue: 0,
        releaseDate: "",
        synopsis: "",
        genre: "",
      },
      defaultItem: {
        title: "",
        status: "",
        revenue: 0,
        releaseDate: "",
        synopsis: "",
        genre: "",
      },
      movies: [
        {
          id: 1,
          title: "Aladdin",
          status: "Now Showing",
          revenue: 4900,
          releaseDate: "2021-02-15",
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
          synopsis:
            "When an unconfident young woman is cursed with an old body by a spiteful witch, her only chance of breaking the spell lies with a self-indulgent yet insecure young wizard and his companions in his legged, walking castle.",
          genre: ["Animation", "Adventure", "Family"],
        },
      ],
    };
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "New Movie" : "Edit Movie";
    },
  },
  methods: {
    editItem(item) {
      this.editedIndex = this.movies.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    deleteItem(item) {
      this.editedIndex = this.movies.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialogDelete = true;
    },

    deleteItemConfirm() {
      this.movies.splice(this.editedIndex, 1);
      this.closeDelete();
    },

    close() {
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    closeDelete() {
      this.dialogDelete = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.movies[this.editedIndex], this.editedItem);
      } else {
        this.movies.push(this.editedItem);
      }
      this.close();
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