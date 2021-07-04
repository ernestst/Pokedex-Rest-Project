<template>
  <div class="container w-50">
    <NavBar></NavBar>
    <div>
      <b-form-input type="text"
                    v-model="login"
                    placeholder="Login"
                    class="md-2 mt-2 center"
                    id="UserName"
                    autocomplete="Name">
      </b-form-input>
      <b-input-group>
        <b-form-input type="password"
                      v-model="password"
                      placeholder="Password"
                      class="center md-2 mt-2"
                      id="Password"
                      autocomplete="new-password">
        </b-form-input>
      </b-input-group>
      <b-form-input type="password"
                    v-model="password2"
                    placeholder="Repeat Password"
                    class="mb-2 mt-2 center"
                    id="Password"
                    autocomplete="off">
      </b-form-input>
    </div>
    <div class="mt-3 mb-3">
      <b-button @click="register">
        Register
      </b-button>
    </div>
    <div class="mt-3 mb-3">
      <b-link href="/login"
              class="center"
              style="font-size: large">
        Back to Login
      </b-link>
    </div>
  </div>
</template>

<script>
import ApiServices from '../api-services';
import {mapActions} from 'vuex';
import NavBar from "./NavBar";

export default {
  components: {NavBar},
  data() {
    return {
      login: '',
      password: '',
      password2: '',
    }
  },

  methods: {
    ...mapActions([
      'createWarningToast',
      'createSuccessToast'
    ]),
    register(){
      if (this.password !== this.password2) {
        this.createWarningToast('Password not match');
      } else {
        ApiServices.register(this.login,this.password).then(() => {
          this.createSuccessToast('Registration completed');
        })
      }
    }
  }
}
</script>

<style scoped>

</style>