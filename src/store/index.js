import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isAuth: true,
    isAdmin: true,
  },
  mutations: {
    set_auth(state, value) {
      state.isAuth = value;
    },
    set_admin(state, value) {
      state.isAdmin = value;
    },
  },
  actions: {
    setAuth({ commit }, value) {
      console.log("Auth work");
      commit("set_auth", value);
    },
    setAdmin({ commit }, value) {
      console.log("Admin work");
      commit("set_admin", value);
    },
  },
  modules: {
  }
})
