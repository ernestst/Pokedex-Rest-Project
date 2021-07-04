package pl.edu.pb.wi.pokemon.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pb.wi.pokemon.dto.PokemonData;
import pl.edu.pb.wi.pokemon.dto.enums.Type;

import java.util.List;

@Repository
public interface PokemonDAO extends JpaRepository<PokemonData, Integer> {
    PokemonData findByCode(String code);
    Page<PokemonData> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<PokemonData> findByPrimaryTypeInOrSecondTypeIn(List<Type> primaryType, List<Type> secondType, Pageable pageable);
    Page<PokemonData> findDataByNameContainsIgnoreCaseAndPrimaryTypeInOrNameContainsIgnoreCaseAndSecondTypeIn(String name1, List<Type> primaryType, String name2, List<Type> secondType, Pageable pageable);
}
