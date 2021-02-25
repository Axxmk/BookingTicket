<template>
  <div>
    <StepOne></StepOne>
  </div>
</template>

<script>
export default {
  components: {
    StepOne: () => import("../components/BookPage/StepOne"),
  },
  metaInfo: {
    title: "Booking",
  },
};
</script>

<style lang="scss" scoped>
</style>