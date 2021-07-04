<template>
  <div>
    <NavBar></NavBar>
    <div v-show="teamLength === pokemons.length">
      <div v-for="(pokemon,index) in pokemons" v-bind:key="index" class="mt-5 mb-5">
        <h2>{{ pokemon.name }}</h2>
        <b-img :src="`data:image/png;base64,${pokemon.image}`"
               class="image md-5 mt-2"
               height="300px"
               width="300px"/>
        <div class="mt-2 mb-2">
          <label class="mr-1">PrimaryType: </label>
          <label> {{ pokemon.primaryType }} </label>
        </div>
        <div class="mt-2 mb-2">
          <label class="mr-1">SecondaryType: </label>
          <label> {{ pokemon.secondType }}</label>
        </div>
        <div class="mt-2 mb-2">
          <b-button @click="removeFromTeam(pokemon.nationalDex)">Remove from team</b-button>
        </div>
      </div>
    </div>
    <div v-show="teamLength !== pokemons.length">
      <img src="../assets/pokeball.gif">
    </div>
  </div>
</template>

<script>
import ApiServices from '../api-services';
import NavBar from "./NavBar";

export default {
  components: {NavBar},
  data() {
    return {
      pokemons: Array,
      teamLength: 0
    }
  },

  methods: {
    getTeam() {
      ApiServices.getUserTeamMember({
        login: localStorage.getItem('login'),
        password: localStorage.getItem('password')
      }).then(response => {
        const team = response.data;
        this.pokemons = [];
        if (Array.isArray(team)) {
          this.teamLength = team.length
          for (const index in team) {
            this.getTeamMemberImage(team[index]);
          }
        } else {
          this.teamLength = 1
          this.getTeamMemberImage(team);
        }
      })
    },
    getTeamMemberImage(pokemon) {
      ApiServices.getPokemonImageByCode(pokemon.code).then(response => {
        pokemon.image = response.data;
        this.pokemons.push(pokemon);
      })
      .catch(() => {
        this.pokemons.push(pokemon);
      })
    },
    removeFromTeam(dex) {
      ApiServices.removeUserTeamMember(dex, {
        login: localStorage.getItem('login'),
        password: localStorage.getItem('password')
      }).then(() => {
        this.$router.go();
      })
    }
  },

  mounted() {
    this.getTeam();
  }
}
</script>

<style scoped>

</style>
