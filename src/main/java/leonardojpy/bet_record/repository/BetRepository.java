package leonardojpy.bet_record.repository;

import leonardojpy.bet_record.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository <Bet, Long> {

}
