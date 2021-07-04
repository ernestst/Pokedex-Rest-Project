package pl.edu.pb.wi.ws.response.mappers;

import org.springframework.security.crypto.password.PasswordEncoder;
import pl.edu.pb.wi.pokemon.dto.EvolutionData;
import pl.edu.pb.wi.pokemon.dto.PokemonData;
import pl.edu.pb.wi.user.dto.Role;
import pl.edu.pb.wi.user.dto.UserData;
import pl.edu.pb.wi.ws.response.Evolution;
import pl.edu.pb.wi.ws.response.Pokemon;
import pl.edu.pb.wi.ws.response.User;

import java.util.Optional;

public class JsonToDtoMapper {
    public static PokemonData mapPokemonToPokemonData(Pokemon pokemon) {
        return Optional.ofNullable(pokemon).map(p -> PokemonData.builder()
                .code(p.getCode())
                .name(p.getName())
                .nationalDex(p.getNationalDex())
                .primaryType(p.getPrimaryType())
                .secondType(p.getSecondType())
                .build())
                .orElse(null);
    }

    public static EvolutionData mapEvolutionToEvolutionData(Evolution evolution){
        return Optional.ofNullable(evolution).map(e -> EvolutionData.builder()
                .requiredItem(e.getItem())
                .additionalRequirements(e.getAdditional())
                .requiredLevel(e.getEvolutionNationalDex())
                .evolution(PokemonData.builder().nationalDex(e.getEvolutionNationalDex()).build())
                .pokemon(PokemonData.builder().nationalDex(e.getSourceNationalDex()).build())
                .build())
                .orElse(null);
    }

    public static UserData mapUserToUserData(User user, PasswordEncoder passwordEncoder){
        return Optional.ofNullable(user).map(u -> UserData.builder()
                .login(u.getLogin())
                .password(passwordEncoder.encode(u.getPassword()))
                .role(Role.USER)
                .build())
                .orElse(null);
    }
}
