<template>
  <div class="contain">
    <h2>Your Information</h2>

    <FullName :user="user"></FullName>

    <Information
      :phone="user.contacts.phone"
      :email="user.contacts.email"
    ></Information>

    <DialogEdit :user="user"></DialogEdit>
  </div>
</template>

<script>
export default {
  components: {
    FullName: () => import("./FullName"),
    Information: () => import("../AccountPage/Information"),
    DialogEdit: () => import("./DialogEdit"),
  },
  props: {
    user: Object,
  },
};
</script>

<style lang="scss" scoped>
.contain {
  position: fixed;
  right: 0;
  width: 30%;
  height: 100%;
  padding: 1%;

  display: flex;
  flex-direction: column;
  justify-content: center;

  background-color: white;
  box-shadow: 0 0 5px 0 rgb(160, 160, 160);

  & h2 {
    text-align: center;
    padding-bottom: 2%;
    color: rgba(0, 0, 0, 0.493);
  }
}
</style>