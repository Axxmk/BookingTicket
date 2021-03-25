<template>
  <div class="wrapper">
    <TicketShow v-if="tickets.length > 0" :tickets="tickets"></TicketShow>
    <NoReservation v-else></NoReservation>
    <ProfileShow :userInfo="userInfo"></ProfileShow>
  </div>
</template>

<script>
export default {
  metaInfo: {
    title: "Profile",
  },
  components: {
    TicketShow: () => import("../components/ProfilePage/TicketShow"),
    NoReservation: () => import("../components/ProfilePage/NoReservation"),
    ProfileShow: () => import("../components/ProfilePage/ProfileShow"),
  },
  data() {
    return {
      userInfo: {
        avatar: "profile.jpg",
        fullname: {
          firstname: "Monthita",
          lastname: "Kiatruangchai",
        },
        username: "jxymttk_",
        email: "monthita.joy@mail.kmutt.ac.th",
        phone: "0988575473",
      },
      tickets: [
        {
          theatre: "1",
          title: "Aladdin",
          time: "20:20",
          seat: ["A10", "B19"],
          date: "30 Mar 2021",
          price: 400,
          payment: "KBank",
        },
        {
          theatre: "3",
          title: "Narnia",
          time: "14:50",
          seat: ["A09", "C09"],
          date: "03 Apr 2021",
          price: 400,
          payment: "KBank",
        },
      ],
    };
  },
  methods: {},
};
</script>

<style lang="scss" scoped>
.wrapper {
  display: flex;
  justify-content: space-between;
  width: 100%;
  color: #f5f5f5;
}
</style>