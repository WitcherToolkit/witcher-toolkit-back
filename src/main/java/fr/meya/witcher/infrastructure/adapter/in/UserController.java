package fr.meya.witcher.infrastructure.adapter.in;

import fr.meya.witcher.application.mapper.UserMapper;
import fr.meya.witcher.infrastructure.adapter.out.IUserRepository;
import fr.meya.witcher.message.response.UserVolatile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/by-email")
    public ResponseEntity<UserVolatile> getUserByEmail(@RequestParam String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toUserDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}