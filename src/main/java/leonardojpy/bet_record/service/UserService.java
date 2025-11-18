package leonardojpy.bet_record.service;

import leonardojpy.bet_record.dto.RegisterDto;
import leonardojpy.bet_record.exceptions.EmailAlreadyExistsException;
import leonardojpy.bet_record.model.User;
import leonardojpy.bet_record.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

        public void register(RegisterDto dto) {


            if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
                throw new EmailAlreadyExistsException("Email já está em uso");
            }

            User user = new User();
            user.setName(dto.getName());
            user.setEmail(dto.getEmail());
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            user.setBalance(0.0);

            userRepository.save(user);
        }
}
