package pl.edu.pb.wi.ws.pokedex;

import org.springframework.stereotype.Service;
import pl.edu.pb.wi.ws.response.Evolution;

import java.util.List;

public interface EvolutionServiceI {
    List<Evolution> getPokemonEvolutions(int dex);

    void savePokemonEvolution(Evolution evolution);
}
