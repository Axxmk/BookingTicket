import axios from '../../util/axios'

const state = () => ({
	tickets: [],
})

const getters = {
	tickets: (state) => state.tickets,
}

const mutations = {
	set_tickets(state, value) {
		state.tickets = value;
	},
}

const actions = {
	getTicket({ commit }) {
		axios
			.get('/tickets')
			.then(
				(response) => {
					console.log(response.data);
					commit("set_tickets", response.data.tickets);
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