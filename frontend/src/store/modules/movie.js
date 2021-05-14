import axios from '../../util/axios'

const state = () => ({
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
})

const getters = {
	movies: state => state.movies,
	genres: state => state.genres,
	isComplete: state => state.isComplete,

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
}

const mutations = {
	set_movies(state, movies) {
		state.movies = movies;
	},
}

const actions = {
	getMovies({ commit }) {
		axios
			.get('/movies')
			.then(
				(response) => {
					commit("set_movies", response.data.movies);
				},
				(error) => console.log(error)
			);
	},

	addMovie({ dispatch }, newMovie) {
		axios
			.post('/movies', newMovie)
			.then(
				(response) => {
					const data = response.data;
					console.log(data)
					if (data.success) {
						dispatch("getMovies");
						dispatch("showSuccess", "Add movie was successful", { root: true });
					}
					else dispatch("showError", data.error_reason, { root: true });
				},
				(error) => console.log(error)
			);
	},

	updateMovie({ dispatch }, data) {
		axios
			.put(`/movies/${data.movieId}`, data.detail)
			.then(
				(response) => {
					const data = response.data;
					console.log(data)
					if (data.success) {
						dispatch("getMovies");
						dispatch("showSuccess", "Update movie was successful", { root: true });
					}
					else dispatch("showError", data.error_reason, { root: true });
				},
				(error) => console.log(error)
			);
	},

	deleteMovie({ dispatch }, movieId) {
		axios
			.delete(`/movies/${movieId}`)
			.then(
				(response) => {
					const data = response.data;
					console.log(data)
					if (data.success) {
						dispatch("getMovies");
						dispatch("showSuccess", "Delete movie was successful", { root: true });
					}
					else dispatch("showError", data.error_reason, { root: true });
				},
				(error) => console.log(error)
			);
	},
}

export default {
	state,
	getters,
	mutations,
	actions
}