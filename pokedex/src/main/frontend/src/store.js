import Vue from 'vue'
import Vuex from 'vuex';

Vue.use(Vuex)

export default new Vuex.Store({
    actions: {
        createWarningToast(ms, message) {
            const vm = new Vue();
            vm.$bvToast.toast(message, {
                title: 'Warning',
                toaster: 'b-toaster-bottom-left',
                variant: 'danger',
                solid: true
            })
        },
        createSuccessToast(ms, message) {
            const vm = new Vue();
            vm.$bvToast.toast(message, {
                title: 'Success',
                toaster: 'b-toaster-bottom-left',
                headerClass: "header-success-class",
                toastClass: "toast-class",
                variant: 'success',
                solid: true
            })
        }
    }
})