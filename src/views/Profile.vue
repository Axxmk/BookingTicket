<template>
  <div class="wrapper">
    <div class="editPart">
      <h3>Your Information</h3>
      <v-icon color="blue darken-3" class="edit-button">
        mdi-pencil-circle-outline
      </v-icon>
    </div>

    <v-avatar width="16vw" height="auto" class="py-8">
      <img :src="`assets/${user.avatar}`" alt="Avatar" />
    </v-avatar>

    <div class="fullname">Monthita Kiatruangchai</div>

    <div>
      <Information header="Username" :content="user.username"></Information>
      <Information header="E-mail" :content="user.email"></Information>
      <Information header="Phone Number" :content="user.phone"></Information>
    </div>

    <hr class="line" />

    <h3 class="py-12">Your Ticket Booking</h3>

    <Ticket></Ticket>
  </div>
</template>

<script>
export default {
  metaInfo: {
    title: "Profile",
  },
  components: {
    Information: () => import("../components/ProfilePage/Information"),
    Ticket: () => import("../components/BookPage/Ticket"),
  },
  data() {
    return {
      user: {
        avatar: "profile.jpg",
        email: "monthita.joy@mail.kmutt.ac.th",
        phone: "098-857-5473",
        username: "jxymk",
      },
    };
  },
};
</script>

<style lang="scss" scoped>
.wrapper {
  margin: 4% 0;
  display: flex;
  flex-direction: column;
  align-items: center;

  & h3 {
    font-size: 2.5vw;
    color: #358cdd;
  }

  .editPart {
    width: 24%;
    display: flex;
    justify-content: space-between;
    align-items: center;

    .edit-button {
      font-size: 30px;
      cursor: pointer;
      transition: all 0.15s ease-in-out;

      &:hover {
        transform: scale(1.05) translateY(-5px);
      }
    }
  }

  .line {
    margin-top: 2%;
    width: 40vw;
    border: 0.9px solid #c9e1f8;
    background-color: #c9e1f8;
  }

  .fullname {
    font-size: 2vw;
    font-weight: 500;
    padding-bottom: 20px;
  }

  @media screen and (max-width: 1024px) {
    & h3 {
      font-size: 2rem;
    }

    .editPart {
      flex-direction: column;
      width: 400px;
    }

    .fullname {
      font-size: 1.5rem;
    }
  }
}
</style>