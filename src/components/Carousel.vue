<template>
  <div id="carousel">
    <i @click="moveToPrevious" class="material-icons arrow arrow-back">
      arrow_back_ios_new
    </i>

    <div id="totalImg-wrapper">
      <div id="totalImg">
        <img
          id="prevImg"
          class="carousel-image"
          :src="images[currentIndex - 1]"
          alt="NO new promotion"
        />
        <img
          id="centerImg"
          class="carousel-image"
          :src="images[currentIndex]"
          alt="NO new promotion"
        />
        <img
          id="nextImg"
          class="carousel-image"
          :src="images[currentIndex + 1]"
          alt="NO new promotion"
        />
      </div>
    </div>

    <i @click="moveToNext" class="material-icons arrow arrow-next">
      arrow_forward_ios
    </i>
  </div>
</template>

<script>
export default {
  data() {
    return {
      images: [
        "bighero.jpg",
        "bighero.jpg",
        "bighero.jpg",
        "bighero.jpg",
        "bighero.jpg",
        "bighero.jpg",
        "bighero.jpg",
      ],
      currentIndex: 1,
    };
  },
  methods: {
    moveToPrevious() {
      const totalImage = document.getElementById("totalImg");

      if (totalImage.classList.contains("translate-next")) {
        totalImage.classList.remove("translate-next");
        totalImage.classList.add("translate-prev");
      } else if (!totalImage.classList.contains("translate-prev")) {
        totalImage.classList.add("translate-prev");
      }

      this.currentIndex -= 1;
    },
    moveToNext() {
      const totalImage = document.getElementById("totalImg");

      if (totalImage.classList.contains("translate-prev")) {
        totalImage.classList.remove("translate-prev");
        totalImage.classList.add("translate-next");
      } else if (!totalImage.classList.contains("translate-next")) {
        totalImage.classList.add("translate-next");
      }

      this.currentIndex += 1;
    },
  },
};
</script>

<style lang="scss" scoped>
$head-linear: #98c6f0;
$tail-linear: #eef5fc;
$secondary-color: #f9e395;
$radius: 25px;

#carousel {
  display: flex;
  align-items: center;
  position: relative;
  margin: 2% 0;

  width: calc(100% - 60px);
  padding-bottom: calc((100% - 60px) * 0.4189);

  background-color: #ffffff;
  border-radius: $radius;
  box-shadow: 0 2px 8px 1px #a5a5a5;

  #totalImg-wrapper {
    border-radius: $radius;
    width: 100%;
    height: 100%;
    top: 0;
    position: absolute;
    overflow: hidden;

    #totalImg {
      width: 300%;
      height: 100%;
      display: flex;
      transition: 1s cubic-bezier(0.075, 0.82, 0.165, 1);
      transform: translateX(-33.33%);

      &.translate-prev {
        transform: translateX(0);
      }
      &.translate-next {
        transform: translateX(-66.66%);
      }
    }
  }

  .arrow {
    position: absolute;
    top: 50%;
    z-index: 100;
    transform: translateY(-50%);
    transition: all 0.1s ease-in-out;

    padding: 1%;

    color: #ffffff;
    border-radius: 50%;
    box-shadow: 0 2px 4px 0 #cccccc;

    cursor: pointer;

    &.arrow-back {
      left: -25px;

      background-image: linear-gradient(90deg, $head-linear, $tail-linear);
    }

    &.arrow-next {
      right: -25px;
      background-image: linear-gradient(270deg, $head-linear, $tail-linear);
    }

    &:hover {
      transform: translateY(-50%) scale(1.05);
    }
  }

  @media screen and (max-width: 768px) {
  }
}
</style>
