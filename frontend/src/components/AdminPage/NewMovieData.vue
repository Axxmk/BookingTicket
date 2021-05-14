<template>
  <v-card>
    <v-card-title class="pl-8 pt-5" style="color: #0361b9">
      New Movie
    </v-card-title>

    <v-card-text class="pb-0">
      <v-container>
        <v-form ref="form">
          <v-row>
            <v-col cols="12">
              <v-text-field v-model="title" label="Movie Title"></v-text-field>
            </v-col>

            <v-col cols="12" sm="7" md="7">
              <v-combobox
                v-model="genre"
                :items="genres"
                label="Genre"
                item-color="blue lighten-2"
                multiple
              ></v-combobox>
            </v-col>

            <v-col cols="12" sm="5" md="5">
              <v-select
                v-model="status"
                :items="statuses"
                item-text="text"
                item-value="value"
                label="Status"
                item-color="blue lighten-2"
              ></v-select>
            </v-col>

            <v-col cols="12">
              <v-text-field
                type="Number"
                v-model="tmdbId"
                label="TMDB movie id (for Release Date, Revenue, Duration and Poster)"
              ></v-text-field>
            </v-col>

            <v-col cols="12">
              <v-textarea
                label="Synopsis"
                v-model="synopsis"
                rows="3"
                filled
                background-color="#f8f8f8"
              ></v-textarea>
            </v-col>
          </v-row>
        </v-form>
      </v-container>
    </v-card-text>

    <v-divider class="mx-6"></v-divider>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="blue lighten-2" text @click="close"> Cancel </v-btn>
      <v-btn color="blue lighten-2" text @click="addNew"> Save </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters(["genres", "isComplete"]),
  },
  data: () => ({
    title: "",
    status: "",
    genre: "",
    synopsis: "",
    tmdbId: 0,
    statuses: [
      {
        text: "Now Showing",
        value: "nowShowing",
      },
      {
        text: "Coming Soon",
        value: "comingSoon",
      },
    ],
  }),
  methods: {
    addNew() {
      if (
        !this.title ||
        !this.status ||
        !this.genre ||
        !this.synopsis ||
        !this.tmdbId
      ) {
        this.$store.dispatch("showError", "Please correct the form");
        return;
      }

      let newMovie = {
        title: this.title,
        status: this.status,
        genre: this.genre.join(),
        synopsis: this.synopsis,
        tmdbId: this.tmdbId.toString(),
      };
      this.$store.dispatch("addMovie", newMovie);
      this.close();
    },
    close() {
      this.$refs.form.reset();
      this.$emit("close");
    },
  },
};
</script>