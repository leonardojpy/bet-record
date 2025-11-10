package leonardojpy.bet_record.controller;

import leonardojpy.bet_record.dto.RegisterDto;
import leonardojpy.bet_record.dto.UserDto;
import leonardojpy.bet_record.model.User;
import leonardojpy.bet_record.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok(new UserDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    
}


