package pl.edu.pb.wi.pokemon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pb.wi.pokemon.dto.enums.Type;
import pl.edu.pb.wi.user.dto.UserData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "pokemons")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PokemonData {
    @Id
    @Column(name = "id")
    private int nationalDex;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "primary_type", nullable = false)
    private Type primaryType;

    @Column(name = "secondary_type")
    private Type secondType;

    @ManyToMany(mappedBy = "pokemonTeam")
    private Collection<UserData> userCollection;
}
