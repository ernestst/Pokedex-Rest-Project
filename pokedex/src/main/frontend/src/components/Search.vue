<template>
  <div class="container w-75">
    <NavBar></NavBar>
    <div class="pt-3">
      <b-form-input v-model="pokemonName" placeholder="Pokemon Name"></b-form-input>
    </div>
    <div class="pt-2">
      <b-form-group label-for="tags-component-select">
        <b-form-tags
            v-model="value"
            size="lg"
            class="mb-2"
            add-on-change
            no-outer-focus
        >
          <template>
            <ul v-if="selectedTypes.length > 0" class="list-inline d-inline-block mb-2">
              <li v-for="type in selectedTypes" :key="type" class="list-inline-item">
                <b-form-tag
                    @remove="removeType(type)"
                    :title="type"
                    variant="warning"
                >{{ type }}
                </b-form-tag>
              </li>
            </ul>
            <b-form-select
                @change="selectType()"
                v-model="selectedType"
                :disabled="allTypes.length === 0"
                :options="allTypes"
            >
            </b-form-select>
          </template>
        </b-form-tags>
      </b-form-group>
    </div>
    <b-button @click="getPokemons()"
              class="my-2 my-sm-0">
      Search
    </b-button>
    <div v-if="rows > 0" class="mt-3">
      <b-table
          id="my-table"
          :items="pokemons"
          :current-page="currentPage"
          :per-page="perPage"
          :fields="fields"
          hover
          @row-clicked="renderPokemonPage"
      ></b-table>
      <b-pagination
          v-model="currentPageComputed"
          :total-rows="rows"
          :per-page="perPage"
          class="mt-4"
          align="center"
          @change="getAllPokemonsFromPagination"
      ></b-pagination>
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
      pokemons: [],
      allTypes: [{text: 'Type', value: 'Type', disabled: true},
        'Normal',
        'Fire',
        'Fighting',
        'Water',
        'Flying',
        'Grass',
        'Poison',
        'Electric',
        'Ground',
        'Psychic',
        'Rock',
        'Ice',
        'Bug',
        'Dragon',
        'Ghost',
        'Dark',
        'Steel',
        'Fairy'],
      pokemonName: '',
      value: [],
      selectedTypes: [],
      selectedType: "Type",
      currentPage: 1,
      rows: 0,
      perPage: 10
    }
  },

  methods: {
    getAllPokemonsFromPagination(pageNum) {
      ApiServices.getPokemons(pageNum).then(response => {
        this.pokemons = response.getPokemonsResponse.return.results;
        this.rows = response.getPokemonsResponse.return.pages * 10;
      })
    },

    removeType(type) {
      this.allTypes.push(type);
      const index = this.selectedTypes.indexOf(type);
      this.selectedTypes.splice(index, 1);
    },

    selectType() {
      this.selectedTypes.push(this.selectedType);
      const index = this.allTypes.indexOf(this.selectedType);
      this.allTypes.splice(index, 1);
      this.selectedType = "Type"
    },

    renderPokemonPage(item) {
      this.$router.push({
        name: 'pokemon', params: {id: item.nationalDex}
      });
    },

    getPokemons() {
      let typesRequest = '';
      for (const index in this.selectedTypes) {
        if (typesRequest !== '') {
          typesRequest = ','
        }

        typesRequest += (this.selectedTypes[index].toUpperCase());
      }
      ApiServices.getPokemonWithNameAndTypes(typesRequest, this.pokemonName, this.currentPage).then(response => {
        if (Array.isArray(response.data.content)) {
          this.pokemons = response.data.content;
        } else {
          this.pokemons = [];
          this.pokemons.push(response.data.content);
        }
        this.rows = response.data.totalPages * 10;
      });
    },
  },

  computed: {
    fields() {
      return [
        'name', 'primaryType', 'secondType'
      ]
    },
    currentPageComputed() {
      return this.currentPage
    }
  }
}
</script>

<style scoped>

</style>
