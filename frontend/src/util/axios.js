import axios from "axios";

let isDev = false;
if (!process.env.NODE_ENV || process.env.NODE_ENV === "development") {
	isDev = true;
}

const instance = axios.create({
	baseURL: isDev
		? "http://localhost:8081"
		: "https://montheara-api.axxmk.ga",
	withCredentials: true,
});

// instance.defaults.headers.common["Authorization"] = CookieHelper.read(
//     "auth-token"
// );

export default instance;