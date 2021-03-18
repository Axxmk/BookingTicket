<template>
  <div class="information">
    <span>{{ header }} :</span>
    {{ content }}
  </div>
</template>

<script>
export default {
  props: {
    header: String,
    content: String,
  },
};
</script>

<style lang="scss" scoped>
.information {
  padding-bottom: 15px;
  font-size: 1vw;

  & span {
    font-weight: 500;
  }

  @media screen and (max-width: 1042px) {
    font-size: 0.8rem;
  }
}
</style>