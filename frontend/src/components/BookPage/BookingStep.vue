<template>
  <v-stepper
    v-model="steps"
    vertical
    style="background-color: #f5f5f5; box-shadow: none"
  >
    <v-stepper-step :complete="steps > 1" step="1">
      Select Showtime
      <small class="pt-1">Date & Time & Theatre</small>
    </v-stepper-step>
    <v-stepper-content step="1">
      <StepOne @nextStep="steps = 2"></StepOne>
    </v-stepper-content>

    <v-stepper-step :complete="steps > 2" step="2">
      Select Seat
    </v-stepper-step>
    <v-stepper-content step="2">
      <StepTwo @nextStep="steps = 3" @backStep="steps = 1"></StepTwo>
    </v-stepper-content>

    <v-stepper-step :complete="steps > 3" step="3"> Payment </v-stepper-step>
    <v-stepper-content step="3">
      <StepThree @backStep="steps = 2"></StepThree>
    </v-stepper-content>

    <v-dialog v-model="dialog" width="550" persistent>
      <DialogInfo></DialogInfo>
    </v-dialog>
  </v-stepper>
</template>

<script>
export default {
  components: {
    StepOne: () => import("./StepOne"),
    StepTwo: () => import("./StepTwo"),
    StepThree: () => import("./StepThree"),
    DialogInfo: () => import("./DialogInfo"),
  },
  data: () => ({
    steps: 1,
  }),
  computed: {
    dialog() {
      return this.$store.getters.dialog;
    },
  },
};
</script>

<style lang="scss">
.v-stepper--vertical {
  width: 70%;
  padding: 4%;

  & .heading {
    font-size: 30px;
    line-height: 80px;
    letter-spacing: 2px;
    color: #4d92d3;
  }

  @media screen and (max-width: 768px) {
    .heading {
      font-size: 25px;
    }
  }
}
</style>