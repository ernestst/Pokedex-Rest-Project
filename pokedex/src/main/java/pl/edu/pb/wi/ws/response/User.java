package pl.edu.pb.wi.ws.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String login;
    private String password;
}
