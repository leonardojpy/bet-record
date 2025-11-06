package leonardojpy.bet_record.repository;

import leonardojpy.bet_record.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
