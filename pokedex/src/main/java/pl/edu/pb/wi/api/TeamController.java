package pl.edu.pb.wi.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pb.wi.ws.response.Pokemon;
import pl.edu.pb.wi.ws.user.TeamServiceI;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamServiceI teamService;

    public TeamController(TeamServiceI teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    List<Pokemon> getUserTeamMembers(Principal principal){
        return teamService.getUserTeamMembers(principal);
    }

    @PostMapping
    List<Pokemon> addUserTeamMember(@RequestParam int dex, Principal principal) {
        return teamService.addUserTeamMember(dex, principal);
    }

    @DeleteMapping
    List<Pokemon> removeUserTeamMember(@RequestParam int dex, Principal principal) {
        return teamService.removeUserTeamMember(dex, principal);
    }
}
