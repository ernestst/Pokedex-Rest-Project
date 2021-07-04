package pl.edu.pb.wi.ws.pokedex;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.edu.pb.wi.pokemon.dao.PokemonDAO;
import pl.edu.pb.wi.pokemon.dto.enums.Type;
import pl.edu.pb.wi.ws.response.Pokemon;
import pl.edu.pb.wi.ws.response.mappers.DtoToJsonMapper;
import pl.edu.pb.wi.ws.response.mappers.JsonToDtoMapper;

import java.util.List;


@Service
public class PokedexService implements PokedexServiceI {

    private final PokemonDAO pokemonDAO;
    private final Integer PAGE_SIZE = 10;

    public PokedexService(PokemonDAO pokemonDAO) {
        this.pokemonDAO = pokemonDAO;
    }

    @Override
    public Pokemon getPokemonById(int dex) {
        return DtoToJsonMapper.mapPokemonDataToPokemonResponse(pokemonDAO.findById(dex).get());
    }

    @Override
    public Pokemon getPokemonByCode(String code) {
        return DtoToJsonMapper.mapPokemonDataToPokemonResponse(pokemonDAO.findByCode(code));
    }

    @Override
    public Page<Pokemon> getPokemonsByName(int page, String name, String link) {
        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);
        return pokemonDAO.findByNameContainingIgnoreCase(name, pageable).map(pd->DtoToJsonMapper.mapPokemonDataToPokemonResponseWithLink(pd, link));
    }

    @Override
    public Page<Pokemon> getPokemonsByType(int page, List<Type> types, String link) {
        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);
        return pokemonDAO.findByPrimaryTypeInOrSecondTypeIn(types, types, pageable).map(pd->DtoToJsonMapper.mapPokemonDataToPokemonResponseWithLink(pd, link));
    }

    @Override
    public Page<Pokemon> getPokemonsAdvanced(int page, String name, List<Type> types, String link) {
        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);
        if (!"".equals(name) && types.size() != 0) {
            return pokemonDAO.findDataByNameContainsIgnoreCaseAndPrimaryTypeInOrNameContainsIgnoreCaseAndSecondTypeIn(name, types, name, types, pageable).map(pd->DtoToJsonMapper.mapPokemonDataToPokemonResponseWithLink(pd, link));
        } else if("".equals(name) && types.size() != 0) {
            return getPokemonsByType(page, types, link);
        } else if (!"".equals(name)) {
            return getPokemonsByName(page, name, link);
        } else {
            return pokemonDAO.findAll(pageable).map(pd->DtoToJsonMapper.mapPokemonDataToPokemonResponseWithLink(pd, link));
        }
    }

    @Override
    public Page<Pokemon> getPokemons(int page, String link) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return pokemonDAO.findAll(pageable).map(pd->DtoToJsonMapper.mapPokemonDataToPokemonResponseWithLink(pd, link));
    }

    @Override
    public void savePokemon(Pokemon pokemon) {
        pokemonDAO.save(JsonToDtoMapper.mapPokemonToPokemonData(pokemon));
    }
}
