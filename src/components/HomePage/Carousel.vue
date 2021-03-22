<template>
  <div id="carousel">
    <i @click="moveToPrevious" class="material-icons arrow arrow-back">
      arrow_back_ios_new
    </i>

    <div id="totalImg-wrapper">
      <div id="totalImg">
        <img
          v-for="(url, index) in images"
          :key="index"
          class="carousel-image"
          :src="`assets/carousel/${url}`"
          alt="New movie is coming soon!"
          width="100%"
          loading="lazy"
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
      images: ["bighero.jpg", "insideout.jpg", "lake.jpg", "spiderman.jpg"],
      currentIndex: 0,
    };
  },
  methods: {
    moveToPrevious() {
      const totalImage = document.getElementById("totalImg");

      this.currentIndex -= 1;

      if (this.currentIndex < 0) {
        this.currentIndex = this.images.length - 1;

        totalImage.classList.add("special");
        setTimeout(() => {
          totalImage.classList.remove("special");
        }, 3000);
      }

      totalImage.firstElementChild.style.marginLeft =
        -this.currentIndex * 100 + "%";
    },
    moveToNext() {
      const totalImage = document.getElementById("totalImg");

      this.currentIndex += 1;

      if (this.currentIndex >= this.images.length) {
        this.currentIndex = 0;

        totalImage.classList.add("special");
        setTimeout(() => {
          totalImage.classList.remove("special");
        }, 3000);
      }

      totalImage.firstElementChild.style.marginLeft =
        -this.currentIndex * 100 + "%";
    },
  },
};
</script>

<style lang="scss" scoped>
$head-linear: #98c6f0;
$tail-linear: #eef5fc;
$secondary-color: #f9e395;
$radius: 20px;

#carousel {
  display: flex;
  align-items: center;
  position: relative;
  margin: 1% 0 3% 0;
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
      height: 100%;
      display: flex;
      position: relative;

      img.carousel-image {
        transition: 2s cubic-bezier(0.075, 0.82, 0.165, 1);
      }
    }

    #totalImg.special {
      img.carousel-image {
        transition: 5s cubic-bezier(0.075, 0.82, 0.165, 1);
      }
    }
  }

  .arrow {
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    z-index: 100;

    padding: 1%;
    color: #ffffff;
    background-color: rgba(0, 0, 0, 0.192);
    border-radius: 50%;

    transition: all 0.1s ease-in-out;
    cursor: pointer;

    &.arrow-back {
      left: -25px;
    }

    &.arrow-next {
      right: -25px;
    }

    &:hover {
      transform: translateY(-50%) scale(1.05);
    }
  }
}
</style>
