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
				});
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

	register({ dispatch }, newUser) {
		axios
			.post("/account/register", newUser)
			.then(
				(response) => {
					if (response.data.success) {
						console.log(response.data);
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

	updateUser({ commit }, newData) {
		axios
			.put(`/account/${newData.userId}`, newData.detail)
			.then(
				(response) => {
					location.reload();

					commit("set_user", response.data.user);
					Cookies.set("token", response.data.token, { expires: 3, secure: false });
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