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
					const data = response.data;
					commit('set_showtimes', data.showtimes);
				},
				(error) => console.log(error)
			);
	},

	getNowShowing({ commit }) {
		axios
			.get(`/showtimes/now`)
			.then(
				(response) => {
					commit('set_nowShowing', response.data.showtimes);
				},
				(error) => console.log(error)
			);
	},

	addShowtime({ dispatch }, newShowtime) {
		axios
			.post('/showtimes', newShowtime)
			.then(
				(response) => {
					const data = response.data;
					if (data.success) {
						dispatch("getShowtimes", newShowtime.movieId);
						dispatch("showSuccess", "Add showtime was successful", { root: true });
					}
					else dispatch("showError", data.error_reason, { root: true });
				},
				(error) => console.log(error)
			);
	},

	deleteShowtime({ dispatch }, payload) {
		axios
			.delete(`/showtimes/${payload.showtimeId}`)
			.then(
				(response) => {
					const data = response.data;
					if (data.success) {
						dispatch("getShowtimes", payload.movieId);
						dispatch("showSuccess", "Delete showtime was successful", { root: true });
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