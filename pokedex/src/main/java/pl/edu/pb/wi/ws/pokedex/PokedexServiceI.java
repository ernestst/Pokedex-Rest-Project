package pl.edu.pb.wi.ws.pokedex;

import org.springframework.data.domain.Page;
import pl.edu.pb.wi.pokemon.dto.enums.Type;
import pl.edu.pb.wi.ws.response.Pokemon;


import java.util.List;

public interface PokedexServiceI {
    Pokemon getPokemonById(int dex);

    Pokemon getPokemonByCode(String code);

    Page<Pokemon> getPokemonsByName(int page, String name, String link);

    Page<Pokemon> getPokemonsByType(int page, List<Type> types, String link);

    Page<Pokemon> getPokemonsAdvanced(int page, String name, List<Type> types, String link);

    Page<Pokemon> getPokemons(int page, String link);

    void savePokemon(Pokemon pokemon);
}
