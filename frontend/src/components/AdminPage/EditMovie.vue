<template>
  <v-card>
    <v-card-title class="pl-8 pb-1 pt-5" style="color: #0361b9">
      Edit Movie
    </v-card-title>

    <v-card-text class="pb-0">
      <v-form ref="form" v-model="valid">
        <v-row>
          <v-col cols="12">
            <v-text-field
              v-model="movie.title"
              label="Movie Title"
              :rules="rule.name"
            ></v-text-field>
          </v-col>

          <v-col cols="12" sm="7" md="7">
            <v-combobox
              v-model="movie.genre"
              :items="genres"
              label="Genre"
              :rules="rule.name"
              item-color="blue lighten-2"
              multiple
            ></v-combobox>
          </v-col>

          <v-col cols="12" sm="5" md="5">
            <v-select
              v-model="movie.status"
              :items="statuses"
              item-text="text"
              item-value="value"
              label="Status"
              :rules="rule.name"
              item-color="blue lighten-2"
            ></v-select>
          </v-col>

          <v-col cols="12" sm="7" md="7">
            <v-text-field
              type="Number"
              v-model="movie.duration"
              label="Duration"
              :rules="rule.name"
            ></v-text-field>
          </v-col>

          <v-col cols="12" sm="5" md="5">
            <v-text-field
              type="Number"
              v-model="movie.revenue"
              label="Revenue"
              :rules="rule.name"
            ></v-text-field>
          </v-col>

          <v-col cols="12">
            <v-menu
              ref="menu"
              v-model="menu"
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
                  :rules="rule.name"
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                v-model="movie.releaseDate"
                color="blue lighten-2"
                scrollable
                @change="$refs.menu.save(movie.releaseDate)"
              >
              </v-date-picker>
            </v-menu>
          </v-col>

          <v-col cols="12">
            <v-textarea
              label="Synopsis"
              v-model="movie.synopsis"
              :rules="rule.name"
              rows="3"
              filled
              background-color="#f8f8f8"
            ></v-textarea>
          </v-col>
        </v-row>
      </v-form>
    </v-card-text>

    <v-divider class="mx-6"></v-divider>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="blue lighten-2" text @click="$emit('close')">
        Cancel
      </v-btn>
      <v-btn color="blue lighten-2" text type="submit" @click="update">
        Save
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: {
    movie: Object,
  },
  computed: {
    genres() {
      return this.$store.getters.genres;
    },
  },
  data() {
    return {
      menu: false,
      valid: false,
      rule: {
        name: [(v) => !!v || "required"],
      },
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
    };
  },
  methods: {
    update() {
      if (this.valid) {
        let data = {
          movieId: this.movie.movieId,
          detail: {
            title: this.movie.title,
            status: this.movie.status,
            releaseDate: this.movie.releaseDate,
            revenue: Number(this.movie.revenue),
            duration: Number(this.movie.duration),
            synopsis: this.movie.synopsis,
            genre: this.movie.genre.join(),
          },
        };

        this.$store.dispatch("updateMovie", data);
        this.$emit("close");
      }
    },
  },
};
</script>