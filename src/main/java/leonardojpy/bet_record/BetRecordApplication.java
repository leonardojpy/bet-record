package leonardojpy.bet_record;

import leonardojpy.bet_record.model.Bet;
import leonardojpy.bet_record.model.Result;
import leonardojpy.bet_record.model.User;
import leonardojpy.bet_record.repository.BetRepository;
import leonardojpy.bet_record.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class BetRecordApplication {

	public static void main(String[] args) {
		SpringApplication.run(BetRecordApplication.class, args);


	}

	@Bean
	CommandLineRunner runner(UserRepository userRepo, BetRepository betRepo){
		return args -> {
			User user = new User();
			user.setName("fabio");
			user.setEmail("fabio@test.com");
			user.setPassword("test");
			userRepo.save(user);

			Bet bet = new Bet();
			bet.setDate(LocalDateTime.now());
			bet.setOdd(2.5);
			bet.setStake(50.0);
			bet.setResult(Result.WON);
			bet.setUser(user);

			betRepo.save(bet);

			System.out.println("Added user and bet succesfully!");
		};
	}
}
