<template>
  <v-card>
    <v-card-title class="pl-8 pt-5" style="color: #0361b9">
      New Movie
    </v-card-title>

    <v-card-text class="pb-0">
      <v-container>
        <v-row>
          <v-col cols="12">
            <v-text-field
              v-model="newMovie.title"
              label="Movie Title"
            ></v-text-field>
          </v-col>

          <v-col cols="12" sm="7" md="7">
            <v-combobox
              v-model="newMovie.genre"
              :items="allGenre"
              label="Genre"
              item-color="blue lighten-2"
              multiple
            ></v-combobox>
          </v-col>

          <v-col cols="12" sm="5" md="5">
            <v-select
              v-model="newMovie.status"
              :items="['Now Showing', 'Coming Soon']"
              label="Status"
              item-color="blue lighten-2"
            ></v-select>
          </v-col>

          <v-col cols="12">
            <v-text-field
              type="Number"
              v-model="newMovie.tmdbMovieId"
              label="TMDB movie id (for Release Date, Revenue, Duration and Poster)"
            ></v-text-field>
          </v-col>

          <v-col cols="12">
            <v-textarea
              label="Synopsis"
              v-model="newMovie.synopsis"
              rows="3"
              filled
              background-color="#f8f8f8"
            ></v-textarea>
          </v-col>
        </v-row>
      </v-container>
    </v-card-text>

    <v-divider class="mx-6"></v-divider>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="blue lighten-2" text @click="$emit('close')">
        Cancel
      </v-btn>
      <v-btn color="blue lighten-2" text @click="addNew"> Save </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      newMovie: {
        title: "",
        status: "",
        synopsis: "",
        genre: "",
        tmdbMovieId: 0,
      },
      allGenre: [
        "Adventure",
        "Family",
        "Fantasy",
        "Drama",
        "Action",
        "Musical",
        "Comedy",
        "Animation",
        "Sci-Fi",
      ],
    };
  },
  methods: {
    addNew() {
      if (
        this.newMovie.title &&
        this.newMovie.status &&
        this.newMovie.synopsis &&
        this.newMovie.genre &&
        this.newMovie.tmdbMovieId
      ) {
        console.log(this.newMovie);
        this.$emit("close");
      }
    },
  },
};
</script>