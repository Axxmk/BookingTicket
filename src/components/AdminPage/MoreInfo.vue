<template>
  <div class="d-flex flex-column mx-auto" style="width: 800px">
    <div class="d-flex">
      <v-img
        class="rounded-lg"
        max-width="200px"
        height="auto"
        :src="`assets/poster/${movie.id}.jpg`"
      ></v-img>

      <div class="pa-5 d-flex flex-column justify-space-around">
        <div>
          <h4>Synopsis</h4>
          {{ movie.synopsis }}

          <h4 class="mt-2">Genre</h4>
          <div
            class="mr-1"
            style="display: inline-block"
            v-for="(genre, index) in movie.genre"
            :key="index"
          >
            {{ genre }}
          </div>
        </div>

        <div>
          <v-dialog v-model="dialog" max-width="550px">
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                max-width="140px"
                outlined
                plain
                v-bind="attrs"
                v-on="on"
                color="blue darken-2"
                style="font-size: 12px"
              >
                <v-icon class="pr-1" size="14" color="blue darken-2">
                  mdi-plus
                </v-icon>
                Add Showtime
              </v-btn>
            </template>

            <EditShowtime
              :movie_id="movie.id"
              @close="dialog = false"
              @addShowtime="addShowtime"
            ></EditShowtime>
          </v-dialog>
        </div>
      </div>
    </div>

    <v-divider class="my-10 mx-3"></v-divider>

    <v-data-table
      :headers="headers"
      :items="showtimes"
      :items-per-page="5"
      sort-by="date"
      class="elevation-1"
    >
      <template v-slot:top>
        <v-toolbar flat>
          <v-dialog v-model="dialogDelete" max-width="400px">
            <DeleteSheet
              @closeDelete="dialogDelete = false"
              @deleteItemConfirm="deleteItemConfirm"
            ></DeleteSheet>
          </v-dialog>
        </v-toolbar>
      </template>

      <template v-slot:item.time="{ item }">
        <div class="py-1" v-for="(time, index) in item.time" :key="index">
          {{ time }}
        </div>
      </template>

      <template v-slot:item.actions="{ item }">
        <v-icon small color="blue lighten-2" @click="deleteItem(item)">
          mdi-delete
        </v-icon>
      </template>
    </v-data-table>
  </div>
</template>

<script>
export default {
  components: {
    EditShowtime: () => import("./EditShowtime"),
    DeleteSheet: () => import("./DeleteSheet"),
  },
  props: {
    movie: Object,
  },
  data() {
    return {
      dialog: false,
      dialogDelete: false,
      editedIndex: -1,
      headers: [
        {
          text: "Theatre",
          align: "start",
          value: "theatre",
        },
        { text: "Date", value: "date" },
        { text: "showtime", value: "time" },
        { text: "Actions", value: "actions", sortable: false },
      ],
      showtimes: [],
    };
  },
  methods: {
    addShowtime(data) {
      this.showtimes.push(data);
      this.dialog = false;
    },
    deleteItem(item) {
      this.editedIndex = this.showtimes.indexOf(item);
      this.dialogDelete = true;
    },
    deleteItemConfirm() {
      console.log(this.showtimes[this.editedIndex]);
      this.dialogDelete = false;
    },
  },
};
</script>

<style lang="scss" scoped>
h4 {
  padding: 1% 0;
  color: grey;
  font-weight: 400;
}
</style>