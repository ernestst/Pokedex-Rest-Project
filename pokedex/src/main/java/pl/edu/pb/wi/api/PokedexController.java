package pl.edu.pb.wi.api;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pb.wi.pokemon.dto.enums.Type;
import pl.edu.pb.wi.ws.pokedex.PokedexServiceI;
import pl.edu.pb.wi.ws.response.Pokemon;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokedexController {

    private final PokedexServiceI pokedexService;

    public PokedexController(PokedexServiceI pokedexService) {
        this.pokedexService = pokedexService;
    }

    @GetMapping("/dex")
    Pokemon getPokemonEvolutions(@RequestParam int dex){
        return pokedexService.getPokemonById(dex);
    }

    @GetMapping("/code")
    Pokemon getPokemonByCode(@RequestParam String code){
        return pokedexService.getPokemonByCode(code);
    }

    @GetMapping("/name")
    Page<Pokemon> getPokemonsByName(@RequestParam int page, @RequestParam String name, HttpServletRequest request){
        return pokedexService.getPokemonsByName(page, name, request.getRequestURL().toString());
    }

    @GetMapping("/types")
    Page<Pokemon> getPokemonsByType(@RequestParam int page, @RequestParam List<Type> types, HttpServletRequest request){
        return pokedexService.getPokemonsByType(page, types, request.getRequestURL().toString());
    }

    @GetMapping("/advanced")
    Page<Pokemon> getPokemonsAdvanced(@RequestParam int page, @RequestParam String name, @RequestParam List<Type> types, HttpServletRequest request){
        return pokedexService.getPokemonsAdvanced(page, name, types, request.getRequestURL().toString());
    }

    @GetMapping
    Page<Pokemon> getPokemons(@RequestParam int page, HttpServletRequest request){
        return pokedexService.getPokemons(page, request.getRequestURL().toString());
    }

    @PostMapping("/add")
    void savePokemon(@RequestBody Pokemon pokemon){
        pokedexService.savePokemon(pokemon);
    }

}
