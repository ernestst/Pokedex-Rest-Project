package pl.edu.pb.wi.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pb.wi.ws.pokedex.EvolutionServiceI;
import pl.edu.pb.wi.ws.response.Evolution;

import java.util.List;

@RestController
@RequestMapping("/evolution")
public class EvolutionController {

    private final EvolutionServiceI evolutionService;

    public EvolutionController(EvolutionServiceI evolutionService) {
        this.evolutionService = evolutionService;
    }

    @GetMapping
    List<Evolution> getPokemonEvolutions(@RequestParam int dex){
        return evolutionService.getPokemonEvolutions(dex);
    }

    @PostMapping("/add")
    void savePokemonEvolution(@RequestBody Evolution evolution){
        evolutionService.savePokemonEvolution(evolution);
    }
}
