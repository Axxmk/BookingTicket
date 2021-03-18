<template>
  <v-card class="mx-auto my-2 pa-6 pb-2 rounded-lg d-flex" width="70%">
    <div class="pr-8 detail-cinema">
      <h5>Cinema {{ cinema.id }}</h5>
      <v-icon large color="#ebc858" class="py-3" style="font-size: 4rem">
        mdi-theater
      </v-icon>
    </div>

    <div class="branch-info">
      <h3>{{ cinema.branch }}</h3>
      <span>Tel: {{ cinema.phoneNumber }}</span>
    </div>
  </v-card>
</template>

<script>
export default {
  props: {
    cinema: Object,
  },
};
</script>

<style lang="scss" scoped>
.v-sheet.v-card {
  transition: all 0.2s ease-in-out;

  &:hover {
    transform: scale(1.05) translateY(-5px);
    box-shadow: 0px 3px 8px -1px rgb(0, 0, 0, 0.05),
      0px 5px 16px 1px rgb(0, 0, 0, 0.05), 0px 2px 20px 1px rgb(0, 0, 0, 0.05);
  }

  .detail-cinema {
    display: flex;
    flex-direction: column;
    align-items: center;

    & h5 {
      color: rgb(182, 180, 180);
      font-size: 0.9rem;
    }

    & span {
      font-size: 4rem;
      color: transparent;
      -webkit-text-stroke: 2.5px #ebc858;
    }
  }

  .branch-info {
    display: flex;
    flex-direction: column;
    justify-content: center;

    & h3 {
      font-size: 1.1rem;
      font-weight: 500;
      padding-bottom: 3px;
      color: rgb(63, 63, 63);
    }

    & span {
      font-size: 0.75rem;
      font-weight: 500;
      color: grey;
    }
  }
}
</style>