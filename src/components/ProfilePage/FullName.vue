<template>
  <div class="name-part">
    <v-avatar width="15vw" height="auto" class="py-3">
      <img :src="`assets/${userInfo.avatar}`" alt="Avatar" />
    </v-avatar>
    <h2>{{ firstname }} {{ lastname }}</h2>
    <span>{{ userInfo.username }}</span>
  </div>
</template>

<script>
export default {
  props: {
    userInfo: Object,
  },
  data() {
    return {
      firstname: this.userInfo.fullname.firstname,
      lastname: this.userInfo.fullname.lastname,
    };
  },
};
</script>

<style lang="scss" scoped>
.name-part {
  display: flex;
  flex-direction: column;
  align-items: center;

  padding: 2% 0;

  & h2 {
    padding-top: 3%;
    font-size: 2vw;
    color: rgb(36, 36, 78);
  }

  & span {
    color: rgba(0, 0, 0, 0.486);
  }

  @media screen and (max-width: 900px) {
    & span {
      font-size: 13px;
    }
  }
}
</style>