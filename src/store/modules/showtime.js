import axios from '../../util/axios'

const state = () => ({
	showtimes: [],
	nowShowing: [],
})

const getters = {
	showtimes: (state) => state.showtimes,
	nowShowingShowtime: (state) => state.nowShowing,
}

const mutations = {
	set_showtimes(state, value) {
		state.showtimes = value;
	},
	set_nowShowing(state, value) {
		state.nowShowing = value;
	},
}

const actions = {
	getShowtimes({ commit }, movieId) {
		axios
			.get(`/showtimes/${movieId}`)
			.then(
				(response) => {
					console.log(response.data);
					commit('set_showtimes', response.data.showtimes);
				},
				(error) => console.log(error)
			);
	},

	getNowShowing({ commit }) {
		axios
			.get(`/showtimes/now`)
			.then(
				(response) => {
					console.log(response.data);
					commit('set_nowShowing', response.data.showtimes);
				},
				(error) => console.log(error)
			);
	},

	addShowtime(_app, newShowtime) {
		axios
			.post('/showtimes', newShowtime)
			.then(
				(response) => {
					location.reload();
					console.log(response.data)
				},
				(error) => console.log(error)
			);
	},

	deleteShowtime(_app, showtimeId) {
		axios
			.delete(`/showtimes/${showtimeId}`)
			.then(
				(response) => {
					location.reload();
					console.log(response.data)
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