<template>
  <div class="wrapper">
    <NoReservation v-if="tickets.length == 0"></NoReservation>
    <TicketShow v-else :tickets="tickets"></TicketShow>
    <ProfileShow v-if="user" :user="user"></ProfileShow>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  metaInfo: {
    title: "Account",
  },
  components: {
    TicketShow: () => import("../components/AccountPage/TicketShow"),
    NoReservation: () => import("../components/AccountPage/NoReservation"),
    ProfileShow: () => import("../components/AccountPage/ProfileShow"),
  },
  mounted() {
    this.$store.dispatch("getTicket");
  },
  computed: {
    ...mapGetters(["user", "tickets"]),
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