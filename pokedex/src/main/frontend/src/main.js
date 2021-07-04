import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router';
import router from "./router"
import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'
import '@/assets/pokeball.gif'
import x2js from 'x2js'
import store from './store'
import BootstrapVue from 'bootstrap-vue/dist/bootstrap-vue.esm';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import 'bootstrap/dist/css/bootstrap.css';

Vue.use(BootstrapVue);

Vue.prototype.$x2js = new x2js()
Vue.use(BootstrapVue);
Vue.config.productionTip = false
Vue.use(VueRouter);

new Vue({
  render: h => h(App),
  router,
  store
}).$mount('#app')
