package fr.meya.witcher.application.service;

import fr.meya.witcher.domain.model.persistent.User;
import fr.meya.witcher.domain.port.in.IUserService;
import fr.meya.witcher.infrastructure.adapter.out.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}