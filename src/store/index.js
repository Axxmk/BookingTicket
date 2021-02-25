import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    auth: false,
  },
  mutations: {
    set_auth(state, value) {
      state.auth = value;
    },
  },
  actions: {
    setAuth({ commit }, value) {
      console.log("work")
      commit("set_auth", value);
    },
  },
  modules: {
  }
})
