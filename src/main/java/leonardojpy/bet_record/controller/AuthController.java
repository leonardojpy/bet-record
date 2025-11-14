package leonardojpy.bet_record.controller;

import leonardojpy.bet_record.dto.LoginDto;
import leonardojpy.bet_record.model.User;
import leonardojpy.bet_record.repository.UserRepository;

import leonardojpy.bet_record.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public void registerUser(@RequestBody User user){
        userRepository.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginData) {
        Optional<User> userOptional = userRepository.findByEmail(loginData.getEmail());

        if (userOptional.isPresent() &&
                userOptional.get().getPassword().equals(loginData.getPassword())) {

            String token = JwtUtil.generateToken(loginData.getEmail());
            Map<String, String> response = new HashMap<>();
            response.put("message", "login efetuado com sucesso");
            response.put("token", token);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("credenciais inválidas");
        }
    }
}
