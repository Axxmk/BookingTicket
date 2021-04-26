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
              v-model="title"
              label="Movie Title"
              :rules="rule.name"
            ></v-text-field>
          </v-col>

          <v-col cols="12" sm="7" md="7">
            <v-combobox
              v-model="genre"
              :items="genres"
              label="Genre"
              :rules="rule.name"
              item-color="blue lighten-2"
              multiple
            ></v-combobox>
          </v-col>

          <v-col cols="12" sm="5" md="5">
            <v-select
              v-model="status"
              :items="['nowShowing', 'comingSoon']"
              label="Status"
              :rules="rule.name"
              item-color="blue lighten-2"
            ></v-select>
          </v-col>

          <v-col cols="12" sm="7" md="7">
            <v-text-field
              type="Number"
              v-model="duration"
              label="Duration"
              :rules="rule.name"
            ></v-text-field>
          </v-col>

          <v-col cols="12" sm="5" md="5">
            <v-text-field
              type="Number"
              v-model="revenue"
              label="Revenue"
              :rules="rule.name"
            ></v-text-field>
          </v-col>

          <v-col cols="12">
            <v-menu
              ref="menu"
              v-model="menu"
              :close-on-content-click="false"
              :return-value.sync="releaseDate"
              transition="scale-transition"
              offset-y
              min-width="auto"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="releaseDate"
                  label="Release Date"
                  prepend-icon="mdi-calendar"
                  readonly
                  :rules="rule.name"
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                v-model="releaseDate"
                color="blue lighten-2"
                no-title
                scrollable
              >
                <v-spacer></v-spacer>

                <v-btn
                  text
                  color="primary"
                  @click="$refs.menu.save(releaseDate)"
                >
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>

          <v-col cols="12">
            <v-textarea
              label="Synopsis"
              v-model="synopsis"
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
      <v-btn color="blue lighten-2" text @click="cancle"> Cancel </v-btn>
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
      title: this.movie.title,
      genre: this.movie.genre,
      status: this.movie.status,
      duration: this.movie.duration,
      revenue: this.movie.revenue,
      releaseDate: this.movie.releaseDate,
      synopsis: this.movie.synopsis,
    };
  },
  methods: {
    update() {
      if (this.valid) {
        let data = {
          movieId: this.movie.movieId,
          detail: {
            title: this.title,
            status: this.status,
            releaseDate: this.releaseDate,
            revenue: Number(this.revenue),
            duration: Number(this.duration),
            synopsis: this.synopsis,
            genre: this.genre.join(),
          },
        };

        this.$store.dispatch("updateMovie", data);
        this.$emit("close");
      }
    },
    cancle() {
      this.$emit("close");

      this.title = this.movie.title;
      this.genre = this.movie.genre;
      this.status = this.movie.status;
      this.duration = this.movie.duration;
      this.revenue = this.movie.revenue;
      this.releaseDate = this.movie.releaseDate;
      this.synopsis = this.movie.synopsis;
    },
  },
};
</script>