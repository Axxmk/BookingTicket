<template>
  <v-data-table
    :headers="headers"
    :items="showtimes"
    :items-per-page="5"
    :search="search"
    sort-by="date"
    class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar flat>
        <v-toolbar-title style="color: #387dd8">Showtime</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search by Date"
          single-line
          hide-details
        ></v-text-field>
      </v-toolbar>
    </template>

    <template v-slot:item="{ item }">
      <tr>
        <td>{{ item.theatre }}</td>
        <td>{{ item.date }}</td>
        <td>
          <div class="py-1" v-for="(time, index) in item.time" :key="index">
            {{ time }}
          </div>
        </td>
        <td>
          <v-icon small color="blue lighten-2" @click="deleteItem(item.id)">
            mdi-delete
          </v-icon>
        </td>
      </tr>

      <v-dialog
        v-if="item.id == deleteId"
        v-model="dialogDelete"
        max-width="400px"
      >
        <DeleteShowtime
          :showtimeId="item.id"
          @closeDelete="dialogDelete = false"
        ></DeleteShowtime>
      </v-dialog>
    </template>
  </v-data-table>
</template>

<script>
export default {
  components: {
    DeleteShowtime: () => import("./DeleteShowtime"),
  },
  props: {
    movieId: Number,
  },
  data() {
    return {
      dialogDelete: false,
      search: "",
      deleteId: 0,
      headers: [
        {
          text: "Theatre",
          align: "start",
          value: "theatre",
          filterable: false,
        },
        { text: "Date", value: "date" },
        { text: "showtime", value: "time", filterable: false },
        { text: "Actions", value: "actions", sortable: false },
      ],
      showtimes: [],
    };
  },
  methods: {
    deleteItem(id) {
      this.deleteId = id;
      this.dialogDelete = true;
    },
  },
};
</script>