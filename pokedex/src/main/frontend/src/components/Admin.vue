<template>
  <div class="container w-75">
    <NavBar></NavBar>
    <div>
      <div class="mr-5">
        <div class="mt-2 mb-2">
          <label>Name: </label>
          <b-form-input v-model="name" type="text"></b-form-input>
        </div>
        <div class="mt-2 mb-2">
          <label>Code: </label>
          <b-form-input v-model="code" type="text"></b-form-input>
        </div>
        <div class="mt-2 mb-2">
          <label>National Dex: </label>
          <b-form-input v-model="nationalDex" type="number"></b-form-input>
        </div>
        <div class="mt-2 mb-2">
          <label>PrimaryType: </label>
          <b-form-select
              v-model="primaryType"
              :options="options"
              class="mb-3"
          ></b-form-select>
        </div>
        <div class="mt-2 mb-2">
          <label>SecondaryType: </label>
          <b-form-select
              v-model="secondType"
              :options="options"
              class="mb-3"
          ></b-form-select>
        </div>
        <div>
          <b-form-file
              ref="file"
              @change="onFileChange"
              accept=".png">
          </b-form-file>
        </div>
      </div>
      <div v-show="imageToDisplay" class="mt-2 mb-2">
        <b-img :src="imageToDisplay" class="md-2 mt-2"
               height="300px"
               width="300px"/>
      </div>
    </div>
    <div>
      <b-button @click="addPokemon" class="mt-2 mb-5"> Add Pokemon</b-button>
    </div>
    <div>
      <div class="mt-2 mb-2">
        <label>Source National Dex: </label>
        <b-form-input v-model="sourceNationalDex" type="number"></b-form-input>
      </div>
      <div class="mt-2 mb-2">
        <label>Evolution National Dex: </label>
        <b-form-input v-model="evolutionNationalDex" type="number"></b-form-input>
      </div>
      <div class="mt-2 mb-2">
        <label>Level: </label>
        <b-form-input v-model="level" type="number"></b-form-input>
      </div>
      <div class="mt-2 mb-2">
        <label>Item: </label>
        <b-form-input v-model="item" type="text"></b-form-input>
      </div>
      <div class="mt-2 mb-2">
        <label>Additional: </label>
        <b-form-input v-model="additional" type="text"></b-form-input>
      </div>
      <div>
        <b-button @click="addEvolution">Add Evolution</b-button>
      </div>
    </div>
  </div>
</template>

<script>
import ApiServices from "../api-services";
import NavBar from "./NavBar";
import {mapActions} from 'vuex';

export default {
  components: {NavBar},

  data() {
    return {
      name: "",
      code: "",
      nationalDex: "",
      primaryType: "",
      secondType: "",
      imageToDisplay: "",
      imageToSent: "",
      options: ['Normal',
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
      sourceNationalDex: "",
      evolutionNationalDex: "",
      level: "",
      item: "",
      additional: ""
    }
  },
  methods: {
    ...mapActions([
      'createSuccessToast',
      'createWarningToast'
    ]),
    onFileChange(e) {
      const files = e.target.files || e.dataTransfer.files;
      if (!files.length)
        return;
      this.createImage(files[0]);
    },
    createImage(file) {
      const reader = new FileReader();
      reader.onload = (e) => {
        this.imageToSent = file;
        this.imageToDisplay = e.target.result;
      };
      reader.readAsDataURL(file);
    },
    addPokemon() {
      ApiServices.addPokemon({
        name: this.name,
        code: this.code,
        nationalDex: this.nationalDex,
        primaryType: this.primaryType.toUpperCase(),
        secondType: (this.secondType.toUpperCase() !== "") ? this.secondType.toUpperCase() : null
      }, {
        login: localStorage.getItem('login'),
        password: localStorage.getItem('password')
      }).then(() => {
        const form = new FormData();
        form.append("data", this.imageToSent);
        form.append("code", this.code);

        ApiServices.addPokemonImage(form, {
          login: localStorage.getItem('login'),
          password: localStorage.getItem('password')
        }).then(() => {
          this.createSuccessToast('Pokemon added');
        }, () => {
          this.createWarningToast('Error! Pokemon\'s image is broken.')
        })
      }, () => {
        this.createWarningToast('Error! Pokemon already exists!');
      })
    },
    addEvolution() {
      ApiServices.addPokemonEvolution({
        additional: this.additional,
        evolutionNationalDex: this.evolutionNationalDex,
        item: this.item,
        level: this.level,
        sourceNationalDex: this.sourceNationalDex
      }, {
        login: localStorage.getItem('login'),
        password: localStorage.getItem('password')
      }).then(() => {
        this.createSuccessToast('Evolution added');
      }, () => {
        this.createWarningToast('Error! Evolution already exists!');
      });
    }
  }
}
</script>

<style scoped>

</style>
