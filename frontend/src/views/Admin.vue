<template>
  <div>
    <h1 class="header">Admin Dashboard</h1>
    <div style="width: 90%" class="mx-auto">
      <v-data-table
        :headers="headers"
        :items="movies"
        item-key="movieId"
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
            <td>
              {{ display[item.status] }}
            </td>
            <td>{{ item.releaseDate }}</td>
            <td>{{ item.duration }}</td>
            <td>{{ item.revenue }}</td>
            <td>
              <v-icon
                small
                color="blue lighten-2"
                class="mr-3"
                @click="movieAction(item.movieId, 'edit')"
              >
                mdi-pencil
              </v-icon>
              <v-icon
                small
                color="blue lighten-2"
                @click="movieAction(item.movieId, 'delete')"
              >
                mdi-delete
              </v-icon>
            </td>
            <td>
              <v-icon
                @click="
                  expand(!isExpanded);
                  getShowtime(!isExpanded, item.movieId);
                "
                size="18"
              >
                mdi-chevron-down
              </v-icon>
            </td>
          </tr>

          <v-dialog
            v-if="item.movieId == selectId"
            v-model="dialogEdit"
            max-width="550px"
            persistent
          >
            <EditMovie :movie="movie" @close="dialogEdit = false"></EditMovie>
          </v-dialog>

          <v-dialog
            v-if="item.movieId == selectId"
            v-model="dialogDelete"
            max-width="400px"
          >
            <DeleteMovie
              :movieId="item.movieId"
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
  mounted() {
    this.$store.dispatch("getMovies");
  },
  computed: {
    movies() {
      return this.$store.getters.movies;
    },
  },
  data: () => ({
    display: { nowShowing: "Now Showing", comingSoon: "Coming Soon" },
    movie: {},
    dialogNew: false,
    dialogDelete: false,
    dialogEdit: false,
    selectId: 0,
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
  }),
  methods: {
    movieAction(id, action) {
      if (action == "edit") {
        this.selectId = id;

        let movieTemp = this.$store.getters.movies.find(
          (movie) => movie.movieId == id
        );
        this.movie = { ...movieTemp };
        this.dialogEdit = true;
      } else if (action == "delete") {
        this.selectId = id;
        this.dialogDelete = true;
      }
    },
    getShowtime(isExpanded, movieId) {
      if (isExpanded) {
        this.$store.dispatch("getShowtimes", movieId);
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