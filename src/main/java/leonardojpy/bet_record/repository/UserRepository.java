package leonardojpy.bet_record.repository;

import leonardojpy.bet_record.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
