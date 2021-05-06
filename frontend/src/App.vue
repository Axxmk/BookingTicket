<template>
  <v-app>
    <SideBar></SideBar>
    <v-main class="grey lighten-4">
      <div id="wrapper">
        <div id="card-show">
          <router-view></router-view>
        </div>
      </div>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "App",
  components: {
    SideBar: () => import("./components/SideBar"),
  },
};
</script>

<style lang="scss" scoped>
#wrapper {
  display: flex;
  justify-content: center;

  #card-show {
    position: relative;
    max-width: 1440px;
    height: 100%;
    width: 100%;
  }
}
</style>
