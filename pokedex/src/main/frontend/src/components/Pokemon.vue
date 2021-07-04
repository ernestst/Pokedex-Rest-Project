<template>
  <div>
    <NavBar></NavBar>
    <div v-show="!isLoading && (evolutionInfos === undefined || evolutionLength === evolutionInfos.length)">
      <div>
        <b-img :src="`data:image/png;base64,${image}`"
               class="image md-5 mt-2"
               height="300px"
               width="300px"/>
        <div>
          <label class="mr-1">Name: </label>
          <label> {{ pokemon.name }} </label>
        </div>
        <div>
          <label class="mr-1">PrimaryType: </label>
          <label> {{ pokemon.primaryType }} </label>
        </div>
        <div>
          <label class="mr-1">SecondaryType: </label>
          <label> {{ pokemon.secondType }}</label>
        </div>
      </div>
      <div v-show="ifLoggedUser" class="mb-5">
        <b-button @click="addToTeam">Add to the team</b-button>
      </div>
      <div>
        <div v-for="(evolutionInfo, index) in evolutionInfos" v-bind:key="index" class="mb-3">
          <h2>Evolution nr {{ index + 1 }}</h2>
          <b-img
              :src="`data:image/jpg;base64,${getEvolution(evolutionInfo.evolutionNationalDex).image}`"
              class="image md-5 mt-2"
              height="300px"
              width="300px"/>
          <div>
            <label class="mr-1">Name: </label>
            <label> {{ getEvolution(evolutionInfo.evolutionNationalDex).name }} </label>
          </div>
          <div>
            <label class="mr-1">PrimaryType: </label>
            <label> {{ getEvolution(evolutionInfo.evolutionNationalDex).primaryType }} </label>
          </div>
          <div>
            <label class="mr-1">SecondaryType: </label>
            <label> {{ getEvolution(evolutionInfo.evolutionNationalDex).secondType }}</label>
          </div>
          <div>
            <label class="mr-1">Level: </label>
            <label> {{ evolutionInfo.level }} </label>
          </div>
          <div>
            <label class="mr-1">Item: </label>
            <label> {{ evolutionInfo.item }} </label>
          </div>
          <div>
            <label class="mr-1">Additional: </label>
            <label> {{ evolutionInfo.additional }} </label>
          </div>
        </div>
      </div>
    </div>
    <div v-show="!(!isLoading && (evolutionInfos === undefined || evolutionLength === evolutionInfos.length))">
      <img src="../assets/pokeball.gif">
    </div>
  </div>
</template>

<script>
import ApiServices from '../api-services';
import NavBar from "./NavBar";
import {mapActions} from 'vuex';

export default {

  name: "Pokemon",
  components: {NavBar},
  data() {
    return {
      pokemon: '',
      image: undefined,
      evolutions: [],
      imageEvolution: '',
      evolutionInfos: [],
      evolutionLength: 0,
      isLoading: true,
    }
  },

  methods: {
    ...mapActions([
      'createSuccessToast',
      'createWarningToast'
    ]),
    getPokemonByCode() {
      ApiServices.getPokemonById(this.$route.params.id).then(response => {
        this.pokemon = response.data;
        this.pokemon.primaryType = this.pokemon.primaryType ? this.fixString(this.pokemon.primaryType) : "";
        this.pokemon.secondType = this.pokemon.secondType ? this.fixString(this.pokemon.secondType) : "";
        this.isLoading = false;
        this.getPokemonEvolutions();
        this.getPokemonImageByCode(this.pokemon.code);
      })
    },
    getPokemonImageByCode(code) {
      ApiServices.getPokemonImageByCode(code).then(response => {
        this.image = response.data;
        this.isLoading = false;
      }).catch((error) => {
        this.isLoading = false;
        console.log(error);
      })
    },
    getPokemonEvolutions() {
      ApiServices.getPokemonEvolutions(this.$route.params.id).then(response => {
        this.evolutionInfos = response.data;
        if (this.evolutionInfos !== undefined) {
          if (Array.isArray(response.data)) {
            this.evolutionInfos = response.data;
            this.evolutionLength = this.evolutionInfos.length;
          } else {
            this.evolutionInfos = [];
            this.evolutionLength = 1;
            this.evolutionInfos.push(response.data)
          }
          for (const index in this.evolutionInfos) {
            this.getPokemonEvolution(this.evolutionInfos[index].evolutionNationalDex);
          }
        }
      }, (error) => {
        console.log(error);
      })
    },
    getPokemonEvolution(id) {
      ApiServices.getPokemonById(id).then(response => {
        response = response.data;
        response.primaryType = response.primaryType ? this.fixString(response.primaryType) : "";
        response.secondType = response.secondType ? this.fixString(response.secondType) : "";
        if (this.evolutions.length === 0) {
          this.evolutions = [];
        }
        this.getPokemonEvolutionImage(response.code, response);
      })
    },
    getPokemonEvolutionImage(code, pokemon) {
      ApiServices.getPokemonImageByCode(code).then(response => {
        pokemon.image = response.data;
        this.evolutions.push(pokemon)
      }).catch(() => {
        this.evolutions.push(pokemon)
      })
    },
    fixString(type) {
      return type.charAt(0).toUpperCase() + type.toLowerCase().slice(1);
    },
    getEvolution(dex) {
      return this.evolutions.find(pok => pok.nationalDex === dex);
    },
    ifLoggedUser() {
      return !localStorage.getItem('login');
    },
    addToTeam() {
      ApiServices.addUserTeamMember(this.pokemon.nationalDex, {
        login: localStorage.getItem('login'),
        password: localStorage.getItem('password')
      }).then(() => {
        this.createSuccessToast(this.pokemon.name + ' has been added');
      }, () => {
        this.createWarningToast('Team is full or pokemon already in your team');
      });
    }
  },
  mounted() {
    this.getPokemonByCode();
  }
}
</script>

<style scoped>

</style>
