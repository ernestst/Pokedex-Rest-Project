package pl.edu.pb.wi.ws.pokedex;

import org.springframework.stereotype.Service;
import pl.edu.pb.wi.pokemon.dao.EvolutionDAO;
import pl.edu.pb.wi.pokemon.dao.PokemonDAO;
import pl.edu.pb.wi.ws.response.Evolution;
import pl.edu.pb.wi.ws.response.mappers.DtoToJsonMapper;
import pl.edu.pb.wi.ws.response.mappers.JsonToDtoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvolutionService implements EvolutionServiceI {

    private final EvolutionDAO evolutionDAO;
    private final PokemonDAO pokemonDAO;

    public EvolutionService(EvolutionDAO evolutionDAO, PokemonDAO pokemonDAO) {
        this.evolutionDAO = evolutionDAO;
        this.pokemonDAO = pokemonDAO;
    }

    @Override
    public List<Evolution> getPokemonEvolutions(int dex) {
        return evolutionDAO.findEvolutionDataByPokemon_NationalDex(dex).stream()
                .map(DtoToJsonMapper::mapEvolutionDataToEvolutionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void savePokemonEvolution(Evolution evolution) {
        evolutionDAO.save(JsonToDtoMapper.mapEvolutionToEvolutionData(evolution));
    }
}
