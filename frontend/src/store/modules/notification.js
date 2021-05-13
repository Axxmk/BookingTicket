const state = () => ({
	snackbar: false,
	message: {
		color: "",
		text: "",
	}
})

const getters = {
	message: state => state.message,
	snackbar: state => state.snackbar,
}

const mutations = {
	set_message(state, payload) {
		let mes = state.message;
		mes.color = payload.color;
		mes.text = payload.text;
	},
	set_snackbar(state, value) {
		state.snackbar = value;
	},
}

const actions = {
	showSuccess({ commit, dispatch }, text) {
		let message = {
			color: "success",
			text: text,
		}
		commit("set_message", message);
		dispatch("toggleSnackbar", true);
	},

	showError({ commit, dispatch }, text) {
		let message = {
			color: "error",
			text: text,
		}
		commit("set_message", message);
		dispatch("toggleSnackbar", true);
	},

	toggleSnackbar({ commit }, value) {
		commit("set_snackbar", value);
	},
}

export default {
	state,
	getters,
	mutations,
	actions
}