package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByEmail(String email);
}
