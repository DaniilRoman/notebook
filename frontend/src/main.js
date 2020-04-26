import Vue from 'vue'
import router from './router.js'
import store from './store'
import App from './App.vue'
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false

Vue.use(BootstrapVue) 
Vue.use(BootstrapVueIcons)

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
