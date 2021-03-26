<template>
  <v-card>
    <v-card-title class="pl-8 pt-5" style="color: #0361b9">
      {{ formTitle }}
    </v-card-title>

    <v-card-text class="pb-0">
      <v-container>
        <v-row>
          <v-col cols="12" sm="12" md="12">
            <v-text-field
              v-model="editedItem.title"
              label="Movie Title"
            ></v-text-field>
          </v-col>

          <v-col cols="12" sm="5" md="5">
            <v-select
              v-model="editedItem.theatre"
              :items="allTheatre"
              label="Theatre"
            ></v-select>
          </v-col>

          <v-col cols="12" sm="7" md="7">
            <v-combobox
              v-model="editedItem.genre"
              :items="allGenre"
              label="Genre"
              multiple
            ></v-combobox>
          </v-col>

          <v-col cols="12" sm="5" md="5">
            <v-menu
              ref="menu"
              v-model="menu"
              :close-on-content-click="false"
              :return-value.sync="editedItem.releaseDate"
              transition="scale-transition"
              offset-y
              min-width="auto"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="editedItem.releaseDate"
                  label="Release Date"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                v-model="editedItem.releaseDate"
                no-title
                scrollable
              >
                <v-spacer></v-spacer>

                <v-btn
                  text
                  color="primary"
                  @click="$refs.menu.save(editedItem.releaseDate)"
                >
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>

          <v-col cols="12" sm="7" md="7">
            <v-select
              v-model="editedItem.type"
              :items="['Now Showing', 'Coming Soon']"
              label="Type"
            ></v-select>
          </v-col>

          <v-col cols="12">
            <v-textarea
              name="input-7-4"
              label="Synopsis"
              :value="editedItem.synopsis"
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
      <v-btn color="blue darken-1" text @click="$emit('close')"> Cancel </v-btn>
      <v-btn color="blue darken-1" text @click="$emit('save')"> Save </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: {
    formTitle: String,
    editedItem: Object,
  },
  data() {
    return {
      menu: false,
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
      allTheatre: ["1", "2", "3", "4", "5", "6", "7", "8"],
    };
  },
};
</script>

<style lang="scss" scoped>
</style>