import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isAuth: true,
  },
  mutations: {
    set_auth(state, value) {
      state.isAuth = value;
    },
  },
  actions: {
    setAuth({ commit }, value) {
      console.log("work");
      commit("set_auth", value);
    },
  },
  modules: {
  }
})
