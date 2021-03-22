<template>
  <div class="wrapper">
    <div class="information">
      <div class="editPart">
        <h3>Your Information</h3>
        <v-icon color="blue darken-3" class="edit-button">
          mdi-pencil-circle-outline
        </v-icon>
      </div>

      <div class="d-flex justify-center">
        <v-avatar width="16vw" height="auto" class="py-8">
          <img :src="`assets/${user.avatar}`" alt="Avatar" />
        </v-avatar>
      </div>

      <div class="fullname">Monthita Kiatruangchai</div>

      <Information header="Username" :content="user.username"></Information>
      <Information header="E-mail" :content="user.email"></Information>
      <Information header="Phone Number" :content="user.phone"></Information>
    </div>

    <div class="line" />

    <div class="d-flex flex-column align-center">
      <h3 class="pb-12">Your Ticket Booking</h3>
      <Ticket :ticket="ticket"></Ticket>
    </div>
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
      ticket: {
        theatre: "1",
        title: "Aladdin",
        time: "20:20",
        seat: ["A10", "B19"],
        date: "2021 Mar 18",
        price: 400,
        payment: "KBank",
      },
    };
  },
};
</script>

<style lang="scss" scoped>
.wrapper {
  margin: 4% 0;
  display: flex;
  // align-items: center;
  justify-content: space-evenly;

  & h3 {
    font-size: 2.5vw;
    color: #358cdd;
  }

  .information {
    display: flex;
    flex-direction: column;

    & > div {
      align-items: center;
    }

    .editPart {
      width: 105%;
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

    .fullname {
      font-size: 2vw;
      font-weight: 500;
      padding-bottom: 20px;
    }
  }

  .line {
    margin: 0 4%;
    border-left: 3.5px solid #c9e1f8;
    height: 600px;
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