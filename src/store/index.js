import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isAuth: false,
    user: {},
  },

  getters: {
    isAuth: state => state.isAuth,
    user: state => state.user,
  },

  mutations: {
    set_auth(state, value) {
      state.isAuth = value;
    },
    set_user(state, user) {
      state.user = user;
    },

  },

  actions: {
    login({ commit }, data) {
      axios
        .get(
          `http://localhost:8081/account/login?username=${data.username}&password=${data.password}`
        )
        .then((response) => {
          if (response.data.isLogin) {
            commit("set_auth", response.data.isLogin);
            commit("set_user", response.data.user);
          }
          else {
            alert("Something wrong, Please try Again :(");
          }
        });
    },
  },

  modules: {
  }
})
