<template>
  <div class="whole-page">
    <div class="part-flex">
      <v-container>
        <v-tabs
          v-model="model"
          centered
          background-color="#F5F5F5"
          slider-size="2.5"
          slider-color="blue lighten-3"
        >
          <v-tab v-for="(type, index) in types" :key="index">
            {{ type }}
          </v-tab>
        </v-tabs>

        <v-tabs-items v-model="model" class="my-6">
          <v-tab-item v-for="type in types" :key="type">
            <template v-if="type == 'Login'"><SignIn></SignIn></template>
            <template v-else> <Register></Register> </template>
          </v-tab-item>
        </v-tabs-items>
      </v-container>
    </div>
  </div>
</template>

<script>
export default {
  components: {
    SignIn: () => import("../components/LoginPage/SignIn"),
    Register: () => import("../components/LoginPage/Register"),
  },
  metaInfo: {
    title: "Log in",
  },
  data: () => ({
    model: "login",
    types: ["Login", "Register"],
  }),
};
</script>

<style lang="scss" scoped>
.whole-page {
  background-image: url("../assets/loginBg.png");
  background-size: 100% 110%;

  .part-flex {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 50%;
    height: 100vh;

    .container {
      width: 60%;

      .v-tabs-items {
        background-color: transparent;
      }
    }
  }
}
</style>