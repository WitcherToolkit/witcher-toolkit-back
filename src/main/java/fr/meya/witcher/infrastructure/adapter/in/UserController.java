package fr.meya.witcher.infrastructure.adapter.in;

import fr.meya.witcher.application.mapper.UserMapper;
import fr.meya.witcher.domain.port.in.IUserService;
import fr.meya.witcher.message.response.UserVolatile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;
    private final UserMapper userMapper;

    public UserController(IUserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/by-email")
    public ResponseEntity<UserVolatile> getUserByEmail(@RequestParam String email) {
        log.info("Recherche de l'utilisateur avec l'email : {}", email);

        return userService.findByEmail(email)
                .map(userMapper::toUserDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}