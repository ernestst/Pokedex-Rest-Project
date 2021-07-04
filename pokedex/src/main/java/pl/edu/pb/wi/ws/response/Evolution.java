package pl.edu.pb.wi.ws.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Evolution {
    private int sourceNationalDex;
    private int evolutionNationalDex;
    private int level;
    private String item;
    private String additional;
}
