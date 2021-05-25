import axios from '../../util/axios'
import router from '../../router'
import Cookies from 'js-cookie';

const state = () => ({
	isAuth: false,
	user: {},
})

const getters = {
	isAuth: state => state.isAuth,
	user: state => state.user,
}

const mutations = {
	set_auth(state, value) {
		state.isAuth = value;
	},
	set_user(state, user) {
		state.user = user;
	},
}

const actions = {
	login({ commit }, data) {
		axios
			.post('/account/login', data)
			.then(
				(response) => {
					let isLogin = response.data.isLogin;
					if (isLogin) {
						commit("set_auth", isLogin);
						commit("set_user", response.data.user);

						Cookies.set("token", response.data.token, { expires: 3, secure: false });

						router.push({ name: "Home" });
					}
					else {
						alert("username or password is incorrect");
					}
				},
				(error) => console.log(error)
			);
	},

	logout({ commit }) {
		Cookies.remove("token");

		if (!Cookies.get("token")) {
			commit("set_auth", false);
			commit("set_user", {});
		}

		if (router.currentRoute.name !== 'Home') {
			router.push({ name: "Home" });
		}
	},

	register({ dispatch }, user) {
		axios
			.post("/account/register", user)
			.then(
				async (response) => {
					const data = response.data;
					if (data.success) {
						await dispatch("login", { username: user.username, password: user.password });
						dispatch("showSuccess", "Register was successful", { root: true });
						dispatch("mailRegis", user.email);
					}
					else {
						dispatch("showError", data.error_reason, { root: true });
					}
				},
				(error) => console.log(error)
			);
	},

	mailRegis(_app, email) {
		let data = { email: email };
		axios
			.post("/account/register/mail", data)
			.then(
				(response) => console.log(response.data),
				(error) => console.log(error)
			);
	},

	updateUser({ commit, dispatch }, newData) {
		axios
			.put(`/account/${newData.userId}`, newData.detail)
			.then(
				(response) => {
					const data = response.data;
					if (data.success) {
						commit("set_user", data.user);
						Cookies.set("token", data.token, { expires: 3, secure: false });
						dispatch("showSuccess", "Information has been updated", { root: true });
					}
					else {
						dispatch("showError", data.error_reason, { root: true });
					}
				},
				(error) => console.log(error)
			);
	}
}

export default {
	state,
	getters,
	mutations,
	actions
}