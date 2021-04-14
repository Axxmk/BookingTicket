<template>
  <v-card>
    <v-card-title class="pl-8 pb-1 pt-5" style="color: #0361b9">
      Edit Movie
    </v-card-title>

    <v-card-text class="pb-0">
      <v-container>
        <v-row>
          <v-col cols="12">
            <v-text-field
              v-model="movie.title"
              label="Movie Title"
            ></v-text-field>
          </v-col>

          <v-col cols="12" sm="7" md="7">
            <v-combobox
              v-model="movie.genre"
              :items="genres"
              label="Genre"
              item-color="blue lighten-2"
              multiple
            ></v-combobox>
          </v-col>

          <v-col cols="12" sm="5" md="5">
            <v-select
              v-model="movie.status"
              :items="['nowShowing', 'comingSoon']"
              label="Status"
              item-color="blue lighten-2"
            ></v-select>
          </v-col>

          <v-col cols="12" sm="7" md="7">
            <v-text-field
              type="Number"
              v-model="movie.duration"
              label="Duration"
            ></v-text-field>
          </v-col>

          <v-col cols="12" sm="5" md="5">
            <v-text-field
              type="Number"
              v-model="movie.revenue"
              label="Revenue"
            ></v-text-field>
          </v-col>

          <v-col cols="12">
            <v-menu
              ref="menu"
              v-model="menu"
              :close-on-content-click="false"
              :return-value.sync="movie.releaseDate"
              transition="scale-transition"
              offset-y
              min-width="auto"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="movie.releaseDate"
                  label="Release Date"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                v-model="movie.releaseDate"
                color="blue lighten-2"
                no-title
                scrollable
              >
                <v-spacer></v-spacer>

                <v-btn
                  text
                  color="primary"
                  @click="$refs.menu.save(movie.releaseDate)"
                >
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>

          <v-col cols="12">
            <v-textarea
              label="Synopsis"
              v-model="movie.synopsis"
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
      <v-btn color="blue lighten-2" text @click="update"> Save </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: {
    movieId: Number,
  },
  computed: {
    movie() {
      return this.$store.getters.movies.find(
        (movie) => movie.movieId == this.movieId
      );
    },
    genres() {
      return this.$store.getters.genres;
    },
  },
  data() {
    return {
      menu: false,
      title: "",
      genre: "",
      status: "",
      allGenre: [
        "Adventure",
        "Fantasy",
        "Animation",
        "Romance",
        "Comedy",
        "Drama",
        "Action",
        "Family",
        "Sci-Fi",
        "Musical",
        "Horror",
        "Romantic comedy",
        "Sports",
        "Thriller",
      ],
    };
  },
  methods: {
    update() {
      if (
        this.movie.title &&
        this.movie.status &&
        this.movie.synopsis &&
        this.movie.genre &&
        this.movie.releaseDate &&
        this.movie.duration
      ) {
        console.log(this.movie);
        this.$emit("close");
      }
    },
  },
};
</script>