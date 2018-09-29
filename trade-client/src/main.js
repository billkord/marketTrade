import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import LightBootstrap from './light-bootstrap-main'
import routes from './routes/routes'
import axios from 'axios'

// plugin setup
Vue.use(VueRouter)
Vue.use(LightBootstrap)

Vue.prototype.$apiURL = 'http://127.0.0.1:8080/api/'

Vue.prototype.$http = axios.create({
  baseURL: Vue.prototype.$apiURL,
  headers: {
    post: {'Content-Type': 'application/json'},
    put: {'Content-Type': 'application/json'},
    patch: {'Content-Type': 'application/json'}
  }
})

const router = new VueRouter({
  routes, // short for routes: routes
  linkActiveClass: 'nav-item active'
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: {
    App
  }
})
