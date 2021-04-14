<template>
  <div class="wrapper">
    <TicketShow v-if="tickets.length > 0" :tickets="tickets"></TicketShow>
    <NoReservation v-else></NoReservation>
    <ProfileShow v-if="user" :user="user"></ProfileShow>
  </div>
</template>

<script>
export default {
  metaInfo: {
    title: "Account",
  },
  components: {
    TicketShow: () => import("../components/AccountPage/TicketShow"),
    NoReservation: () => import("../components/AccountPage/NoReservation"),
    ProfileShow: () => import("../components/AccountPage/ProfileShow"),
  },
  computed: {
    user() {
      return this.$store.getters.user;
    },
  },
  data() {
    return {
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
          seat: ["A12", "C12"],
          date: "03 Apr 2021",
          price: 400,
          payment: "KBank",
        },
      ],
    };
  },
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