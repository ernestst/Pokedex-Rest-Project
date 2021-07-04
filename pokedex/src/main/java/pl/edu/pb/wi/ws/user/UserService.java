package pl.edu.pb.wi.ws.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.pb.wi.user.dao.UserDAO;
import pl.edu.pb.wi.user.dto.Role;
import pl.edu.pb.wi.user.dto.UserData;
import pl.edu.pb.wi.ws.response.User;
import pl.edu.pb.wi.ws.response.mappers.JsonToDtoMapper;

import java.security.Principal;

@Service
public class UserService implements UserServiceI {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(Principal principal) {
        UserData requestedUser = userDAO.findByLogin(principal.getName());
        if (requestedUser != null) {
            return requestedUser.getRole().getValue();
        }
        return null;
    }

    @Override
    public String register(User user) {
        UserData requestedUser = userDAO.findByLogin(user.getLogin());
        if (requestedUser == null) {
            userDAO.save(JsonToDtoMapper.mapUserToUserData(user, passwordEncoder));
            return "SUCCESS";
        }
        return "USER_ALREADY_EXISTS";
    }
}
