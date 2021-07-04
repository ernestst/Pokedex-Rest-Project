package pl.edu.pb.wi.ws.user;

import pl.edu.pb.wi.user.dto.Role;
import pl.edu.pb.wi.ws.response.User;

import java.security.Principal;

public interface UserServiceI {
    String login(Principal principal);

    String register(User user );
}
