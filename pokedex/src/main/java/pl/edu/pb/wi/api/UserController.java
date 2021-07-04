package pl.edu.pb.wi.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pb.wi.user.dto.Role;
import pl.edu.pb.wi.ws.pokedex.EvolutionServiceI;
import pl.edu.pb.wi.ws.response.Evolution;
import pl.edu.pb.wi.ws.response.User;
import pl.edu.pb.wi.ws.user.UserServiceI;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceI userService;

    public UserController(UserServiceI userService) {
        this.userService = userService;
    }

    @GetMapping
    public String login(Principal principal){
        return userService.login(principal);
    }

    @PostMapping
    public String register(@RequestBody User user){
        return userService.register(user);
    }
}
