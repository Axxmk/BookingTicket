import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import qs from 'qs'
import router from '../router'
import createPersistedState from 'vuex-persistedstate';
import Cookies from 'js-cookie';

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isAuth: false,
    user: {},
    movies: [],
    genres: [
      "Adventure",
      "Fantasy",
      "Animation",
      "Romance",
      "Comedy",
      "Drama",
      "Action",
      "Family",
      "Sci-Fi",
      "Musical",
      "Horror",
      "Romantic comedy",
      "Sports",
      "Thriller",
    ],
  },

  plugins: [createPersistedState({
    paths: ['isAuth', 'user'],
    storage: {
      getItem: key => Cookies.get(key),
      setItem: (key, value) => Cookies.set(key, value, { expires: 3, secure: false }),
      removeItem: key => Cookies.remove(key)
    }
  })],

  getters: {
    isAuth: state => state.isAuth,
    user: state => state.user,
    movies: state => state.movies,
    genres: state => state.genres,

    nowShowingMovies: state => {
      return state.movies.filter(movie => movie.status == "nowShowing");
    },
    comingSoonMovies: state => {
      return state.movies.filter(movie => movie.status == "comingSoon");
    },
    rankingMovie: state => {
      let ranking = state.movies.filter(movie => movie.status == "nowShowing");
      ranking.sort((a, b) => b.revenue - a.revenue);
      return ranking.slice(0, 4);
    },
  },

  mutations: {
    set_auth(state, value) {
      state.isAuth = value;
    },
    set_user(state, user) {
      state.user = user;
    },
    set_movies(state, movies) {
      state.movies = movies;
    },
  },

  actions: {
    login({ commit }, data) {
      axios
        .get(
          `http://localhost:8081/account/login?username=${data.username}&password=${data.password}`
        )
        .then((response) => {
          let isLogin = response.data.isLogin;
          if (isLogin) {
            commit("set_auth", isLogin);
            commit("set_user", response.data.user);

            router.push({ name: "Home" });
          }
          else {
            alert("username or password is incorrect");
          }
        });
    },

    logout({ commit }) {
      commit("set_auth", false);
      commit("set_user", {});
      router.push({ name: "Home" });
    },

    register({ dispatch }, newUser) {
      axios
        .post("http://localhost:8081/account/register", qs.stringify(newUser))
        .then(
          (response) => {
            if (response.data.success) {
              alert("Register was successful")
              dispatch("login", { username: newUser.username, password: newUser.password });
            }
            else {
              console.log(response.data);
              alert(response.data.error_reason);
            }
          },
          (error) => console.log(error)
        );
    },

    getMovies({ commit }) {
      axios.get('http://localhost:8081/movie/movies').then((response) => {
        commit("set_movies", response.data.movies);
      });
    },

    addMovie(_app, newMovie) {
      axios
        .post('http://localhost:8081/movie/addmovie', qs.stringify(newMovie))
        .then(
          (response) => console.log(response.data),
          (error) => console.log(error)
        );
    },
  },
})
