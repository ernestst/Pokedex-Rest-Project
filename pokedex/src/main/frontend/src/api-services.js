import axios from './axios.js';

class ApiServices {
    static getPokemons = (page) => axios.get('pokemon?page=' + page);

    static getPokemonById = (nationalDex) => axios.get('pokemon/dex?dex=' + nationalDex);

    static getPokemonImageByCode = (code) => axios.get('image?code=' + code);

    static getPokemonEvolutions = (nationalDex) => axios.get('evolution?dex=' + nationalDex);

    static getPokemonWithNameAndTypes = (types, name, page) => axios.get('pokemon/advanced?page=' + page + '&name=' + name + '&types=' + types);

    static login = (user) => axios.get('user', {auth: {username: user.login, password: user.password}});

    static register = (login, password) => axios.post('user', {login: login, password: password});

    static addPokemon = (pokemon, user) => axios.post('pokemon/add', pokemon, {
        auth: {username: user.login, password: user.password}
    });

    static addPokemonImage = (form, user) => axios.post('image/add', form, {
        auth: {username: user.login, password: user.password},
        headers: {'Content-Type': 'multipart/form-data'}
    });

    static addPokemonEvolution = (evolution, user) => axios.post('evolution/add', evolution, {
        auth: {username: user.login, password: user.password},
    });

    static addUserTeamMember = (dex, user) => axios.post('team?dex=' + dex, {}, {
        auth: {username: user.login, password: user.password},
    });

    static getUserTeamMember = (user) => axios.get('team', {
        auth: {username: user.login, password: user.password},
    });

    static removeUserTeamMember = (dex, user) => axios.delete('team?dex=' + dex, {
        auth: {username: user.login, password: user.password},
    });
}

export default ApiServices;
