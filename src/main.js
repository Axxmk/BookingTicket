import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';
import meta from 'vue-meta'

Vue.config.productionTip = false
Vue.use(meta)

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
