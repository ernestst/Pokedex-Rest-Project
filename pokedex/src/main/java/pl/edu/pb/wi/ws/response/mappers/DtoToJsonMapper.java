package pl.edu.pb.wi.ws.response.mappers;

import pl.edu.pb.wi.pokemon.dto.EvolutionData;
import pl.edu.pb.wi.pokemon.dto.PokemonData;
import pl.edu.pb.wi.ws.response.Evolution;
import pl.edu.pb.wi.ws.response.Pokemon;

import java.util.Optional;

public class DtoToJsonMapper {

    public static Pokemon mapPokemonDataToPokemonResponse(PokemonData pokemonData){
        return Optional.ofNullable(pokemonData).map(pd -> Pokemon.builder()
                .code(pd.getCode())
                .name(pd.getName())
                .nationalDex(pd.getNationalDex())
                .primaryType(pd.getPrimaryType())
                .secondType(pd.getSecondType())
                .build())
                .orElse(null);
    }

    public static Pokemon mapPokemonDataToPokemonResponseWithLink(PokemonData pokemonData, String link){
        return Optional.ofNullable(pokemonData).map(pd -> Pokemon.builder()
                .code(pd.getCode())
                .name(pd.getName())
                .nationalDex(pd.getNationalDex())
                .primaryType(pd.getPrimaryType())
                .secondType(pd.getSecondType())
                .link(link + "/code?code=" + pd.getCode())
                .build())
                .orElse(null);
    }

    public static Evolution mapEvolutionDataToEvolutionResponse(EvolutionData evolutionData){
        return Optional.ofNullable(evolutionData).map(ed -> Evolution.builder()
                .item(ed.getRequiredItem())
                .additional(ed.getAdditionalRequirements())
                .level(ed.getRequiredLevel())
                .sourceNationalDex(ed.getPokemon().getNationalDex())
                .evolutionNationalDex(ed.getEvolution().getNationalDex())
                .build())
                .orElse(null);
    }
}
