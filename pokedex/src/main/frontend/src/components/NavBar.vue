<template>
  <div class="fixed-top">
    <b-navbar type="dark" variant="dark">
      <b-navbar-nav>
        <b-nav-item href="/search" class="ml-4">Home</b-nav-item>

        <!-- Navbar dropdowns -->
        <div v-show="ifLoggedUser">
          <b-nav-item-dropdown text="User" class="ml-2" right>
            <b-dropdown-item href="/login">Login</b-dropdown-item>
            <b-dropdown-item href="/registration">Create account</b-dropdown-item>
          </b-nav-item-dropdown>
        </div>
        <div v-show="!ifLoggedUser">
          <b-nav-item-dropdown text="User" class="ml-2" right>
            <b-dropdown-item href="/team">Team</b-dropdown-item>
            <b-dropdown-item @click="logout">Logout</b-dropdown-item>
          </b-nav-item-dropdown>
        </div>
        <div v-show="ifAdmin">
          <b-nav-item href="/admin" class="ml-2">Admin</b-nav-item>
        </div>
      </b-navbar-nav>
    </b-navbar>
  </div>
</template>

<script>
export default {
  methods: {
    logout() {
      localStorage.clear();
      this.$router.go();
    }
  },

  computed: {
    ifLoggedUser() {
      return !localStorage.getItem('login');
    },
    ifAdmin() {
      return localStorage.getItem('role') === "administrator"
    }
  }
}
</script>

<style scoped>

</style>
