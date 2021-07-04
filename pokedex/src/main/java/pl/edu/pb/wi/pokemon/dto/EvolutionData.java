package pl.edu.pb.wi.pokemon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "evolutions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvolutionData {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private PokemonData pokemon;

    @OneToOne
    private PokemonData evolution;

    @Column(name = "level")
    private int requiredLevel;

    @Column(name = "item")
    private String requiredItem;

    @Column(name = "additional")
    private String additionalRequirements;
}
