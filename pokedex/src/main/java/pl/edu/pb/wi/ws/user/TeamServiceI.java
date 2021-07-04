package pl.edu.pb.wi.ws.user;

import pl.edu.pb.wi.ws.response.Pokemon;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.security.Principal;
import java.util.List;

public interface TeamServiceI {
    List<Pokemon> getUserTeamMembers(Principal principal);
    List<Pokemon> addUserTeamMember(int dex, Principal principal);
    List<Pokemon> removeUserTeamMember(int dex, Principal principal);
}
