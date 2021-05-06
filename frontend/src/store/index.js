import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate';
import Cookies from 'js-cookie';

import authen from './modules/authen';
import movie from './modules/movie';
import showtime from './modules/showtime';
import booking from './modules/booking';
import ticket from './modules/ticket';

Vue.use(Vuex)

export default new Vuex.Store({

  plugins: [createPersistedState({
    paths: ['authen'],
    storage: {
      getItem: key => Cookies.get(key),
      setItem: (key, value) => Cookies.set(key, value, { expires: 3, secure: false }),
      removeItem: key => Cookies.remove(key)
    }
  })],

  modules: {
    authen,
    movie,
    showtime,
    booking,
    ticket,
  },

})
