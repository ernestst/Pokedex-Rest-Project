<template>
  <div>
    <NavBar></NavBar>
    <div class="p-3 my-3 text-50 shadow-sm">
      <h2>Sign in</h2>
    </div>
    <div class="container">
      <div class="d-flex justify-content-center">
        <b-form-input type="email"
                      v-model="login"
                      placeholder="Login"
                      class="w-50 m-2 center"
                      id="Login">

        </b-form-input>
      </div>
      <div class="d-flex justify-content-center">
        <b-form-input type="password"
                      v-model="password"
                      placeholder="Password"
                      class="w-50 m-2 center"
                      id="Password">

        </b-form-input>
      </div>
      <div class="d-flex justify-content-center">
        <b-button @click="loginApi"
                  class="m-2 float-left">
          Login
        </b-button>
      </div>
      <div>
        <b-link href="/registration"
                class="center"
                style="font-size: large">
          Create account
        </b-link>
      </div>
    </div>
  </div>

</template>

<script>
import ApiServices from '../api-services';
import NavBar from "./NavBar";
import {mapActions} from "vuex";

export default {
  components: {NavBar},
  data() {
    return {
      login: '',
      password: ''
    }
  },

  methods: {
    ...mapActions([
      'createWarningToast'
    ]),
    loginApi() {
      ApiServices.login({login: this.login, password: this.password}).then(response => {
        localStorage.setItem('role', response.data)
        localStorage.setItem('login', this.login);
        localStorage.setItem('password', this.password);

        this.$router.push({
          name: 'search',
        });
      }, () => {
        this.createWarningToast('Invalid login or password.');
      });
    },
  },

  mounted() {
  }

}
</script>

<style scoped>

.center {
  margin-right: auto;
  margin-left: auto;
}

</style>
