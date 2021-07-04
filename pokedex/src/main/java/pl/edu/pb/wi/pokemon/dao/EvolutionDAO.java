package pl.edu.pb.wi.pokemon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pb.wi.pokemon.dto.EvolutionData;

import java.util.List;

@Repository
public interface EvolutionDAO extends JpaRepository<EvolutionData, Integer> {
    List<EvolutionData> findEvolutionDataByPokemon_NationalDex(int dex);
}
