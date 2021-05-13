<template>
  <div class="contain">
    <h2>Your Information</h2>

    <FullName :user="user"></FullName>

    <Information
      :phone="user.contacts.phone"
      :email="user.contacts.email"
    ></Information>

    <v-btn class="mx-8 mt-1" color="yellow darken-1" dark @click="open">
      Edit
      <v-icon class="pl-1" size="17" color="white"> mdi-pencil </v-icon>
    </v-btn>
    <v-dialog v-model="dialog" width="500">
      <DialogEdit
        :userEdit="userEdit"
        @closeDialog="dialog = false"
      ></DialogEdit>
    </v-dialog>
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
  data: () => ({
    dialog: false,
    userEdit: {},
  }),
  methods: {
    open() {
      this.userEdit = {
        ...this.user,
        contacts: { ...this.user.contacts },
        fullname: { ...this.user.fullname },
      };
      this.dialog = true;
    },
  },
};
</script>

<style lang="scss" >
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
.v-dialog {
  border-radius: 20px;
}
</style>