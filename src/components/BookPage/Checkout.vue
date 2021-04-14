<template>
  <v-card class="price-box">
    <h4>Checkout</h4>
    <div>฿ {{ price }}</div>
  </v-card>
</template>

<script>
export default {
  props: {
    price: Number,
  },
};
</script>

<style lang="scss" scoped>
.price-box {
  width: 100%;
  height: 60px;
  padding: 4%;

  display: flex;
  justify-content: space-between;
  align-items: center;

  border-radius: 10px;
  background-color: #52a1eb;

  & h4 {
    color: white;
  }

  & div {
    width: 100px;
    height: 40px;
    font-size: 1.25em;

    display: flex;
    justify-content: center;
    align-items: center;

    color: #f0d438;
    border-radius: 10px;
    background-color: white;
  }
}
</style>