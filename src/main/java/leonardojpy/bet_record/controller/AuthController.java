package leonardojpy.bet_record.controller;

import leonardojpy.bet_record.dto.LoginDto;
import leonardojpy.bet_record.dto.LoginResponseDto;
import leonardojpy.bet_record.dto.RegisterDto;
import leonardojpy.bet_record.model.User;
import leonardojpy.bet_record.repository.UserRepository;

import leonardojpy.bet_record.security.JwtUtil;
import leonardojpy.bet_record.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto dto) {
        try {
            userService.register(dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }


    private User toEntity(RegisterDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setBalance(0.0);
        return user;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginData) {


        Optional<User> userOptional = userRepository.findByEmail(loginData.getEmail());


        if (userOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("email nao encontrado (MUDAR LOG DEPOIS)");
        }

        User user = userOptional.get();


        boolean senhaCorreta = passwordEncoder.matches(
                loginData.getPassword(),
                user.getPassword()
        );

        if (!senhaCorreta) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("senha incorreta (MUDAR LOG DEPOIS)");
        }


        String token = jwtUtil.generateToken(user.getEmail());


        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}
