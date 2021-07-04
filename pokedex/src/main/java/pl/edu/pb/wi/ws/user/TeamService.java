package pl.edu.pb.wi.ws.user;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import pl.edu.pb.wi.pokemon.dao.PokemonDAO;
import pl.edu.pb.wi.pokemon.dto.PokemonData;
import pl.edu.pb.wi.user.dao.UserDAO;
import pl.edu.pb.wi.user.dto.UserData;
import pl.edu.pb.wi.ws.response.Pokemon;
import pl.edu.pb.wi.ws.response.mappers.DtoToJsonMapper;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService implements TeamServiceI{

    private final UserDAO userDAO;

    private final PokemonDAO pokemonDAO;

    public TeamService(UserDAO userDAO, PokemonDAO pokemonDAO) {
        this.userDAO = userDAO;
        this.pokemonDAO = pokemonDAO;
    }

    @Override
    public List<Pokemon> getUserTeamMembers(Principal principal) {
        UserData userData = userDAO.findByLogin(principal.getName());

        return userData.getPokemonTeam().stream()
                .map(DtoToJsonMapper::mapPokemonDataToPokemonResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<Pokemon> addUserTeamMember(int dex, Principal principal) {
        UserData userData = userDAO.findByLogin(principal.getName());

        if(userData.getPokemonTeam().size() == 6){
            throw new RestClientException("TEAM_IS_FULL");
        }
        PokemonData pokemonData = pokemonDAO.findById(dex).get();

        if(userData.getPokemonTeam().contains(pokemonData)){
            throw new RestClientException("POKEMON_ALREADY_IN_TEAM");
        }
        userData.getPokemonTeam().add(pokemonData);

        userDAO.save(userData);
        return userData.getPokemonTeam().stream()
                .map(DtoToJsonMapper::mapPokemonDataToPokemonResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<Pokemon> removeUserTeamMember(int dex, Principal principal) {
        UserData userData = userDAO.findByLogin(principal.getName());
        PokemonData pokemonData = pokemonDAO.findById(dex).get();
        userData.getPokemonTeam().remove(pokemonData);

        userDAO.save(userData);
        return userData.getPokemonTeam().stream()
                .map(DtoToJsonMapper::mapPokemonDataToPokemonResponse)
                .collect(Collectors.toList());
    }
}
