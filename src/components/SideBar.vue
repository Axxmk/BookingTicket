<template>
  <v-navigation-drawer
    app
    floating
    permanent
    expand-on-hover
    width="195px"
    class="py-9 rounded-r-xl elevation-1"
  >
    <div id="part">
      <v-list class="d-flex justify-center">
        <v-list-item>
          <v-list-item-icon>
            <v-img
              max-width="35"
              max-height="35"
              src="../assets/logo.png"
            ></v-img>
          </v-list-item-icon>
          <v-list-item-title>
            <h3 class="logo-name">MONTHEARA</h3>
          </v-list-item-title>
        </v-list-item>
      </v-list>

      <v-list nav>
        <v-list-item
          v-if="$store.state.isAuth"
          link
          :to="{ name: 'Profile' }"
          color="#56A3EB"
        >
          <v-list-item-icon>
            <div
              class="avatar"
              :style="`background-image: url('assets/${userImage}')`"
            ></div>
          </v-list-item-icon>
          <v-list-item-title>Profile</v-list-item-title>
        </v-list-item>

        <v-list-item
          v-for="item in list"
          :key="item.name"
          link
          :to="{ name: item.name }"
          color="#56A3EB"
        >
          <v-list-item-icon>
            <v-icon color="#56A3EB">{{ item.icon }}</v-icon>
          </v-list-item-icon>
          <v-list-item-title>{{ item.name }}</v-list-item-title>
        </v-list-item>
      </v-list>

      <v-list nav>
        <template v-if="$store.state.isAuth">
          <v-list-item link color="#56A3EB" @click="logOut">
            <v-list-item-icon>
              <v-icon color="#56A3EB" medium>mdi-logout</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Logout</v-list-item-title>
          </v-list-item>
        </template>

        <template v-else>
          <v-list-item link color="#56A3EB" :to="{ name: 'Login' }">
            <v-list-item-icon>
              <v-icon color="#56A3EB" medium>mdi-login</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Login</v-list-item-title>
          </v-list-item>
        </template>
      </v-list>
    </div>
  </v-navigation-drawer>
</template>

<script>
export default {
  data() {
    return {
      list: [
        { name: "Home", icon: "mdi-home" },
        { name: "Movies", icon: "mdi-movie-open" },
        { name: "Cinema", icon: "mdi-theater" },
        { name: "Admin", icon: "mdi-account-star" },
      ],
      userImage: "profile.jpg",
    };
  },
  methods: {
    logOut() {
      this.$store.dispatch("setAuth", false);
    },
  },
};
</script>

<style lang="scss" scoped>
.v-navigation-drawer {
  z-index: 2000;
  opacity: 0.97;

  #part {
    display: flex;
    flex-direction: column;
    justify-content: space-between;

    height: 100%;

    .logo-name {
      font-size: 18px;
      font-weight: 500;
      line-height: 75px;
      color: #69c2dd;
    }

    .avatar {
      width: 1.5rem;
      height: 1.5rem;
      background-position: center;
      background-size: cover;
      background-repeat: no-repeat;
      border-radius: 12px;
    }
  }
}
</style>