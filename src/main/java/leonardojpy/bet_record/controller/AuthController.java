package leonardojpy.bet_record.controller;

import leonardojpy.bet_record.model.User;
import leonardojpy.bet_record.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public void registerUser(@RequestBody User user){
        userRepository.save(user);
    }
}
