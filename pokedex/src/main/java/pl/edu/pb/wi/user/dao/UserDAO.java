package pl.edu.pb.wi.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pb.wi.user.dto.UserData;

@Repository
public interface UserDAO extends JpaRepository<UserData, Long> {
    UserData findByLogin(String login);
}
