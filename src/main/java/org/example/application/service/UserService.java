package org.example.application.service;

import org.example.application.data.JpaUserRepository;
import org.example.application.data.UserRepository;
import org.example.application.entity.User;
import org.example.application.exception.UserAlreadyExistsException;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new JpaUserRepository();
    }

    public User create (User user) throws UserAlreadyExistsException {
        return userRepository.save(user);
    }

    public String auth(User user) {
        return userRepository.verify(user);
    }
}
