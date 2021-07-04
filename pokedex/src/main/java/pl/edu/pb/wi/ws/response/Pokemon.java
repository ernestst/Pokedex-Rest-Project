package pl.edu.pb.wi.ws.response;

import lombok.Builder;
import lombok.Data;
import pl.edu.pb.wi.pokemon.dto.enums.Type;

@Data
@Builder
public class Pokemon {
    private int nationalDex;
    private String code;
    private String name;
    private Type primaryType;
    private Type secondType;
    private String link;
}
