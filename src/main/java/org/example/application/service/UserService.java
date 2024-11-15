package org.example.application.service;

import org.example.application.data.JpaUserRepository;
import org.example.application.data.UserRepository;
import org.example.application.entity.User;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new JpaUserRepository();
    }

    public User create (User user) {
        return userRepository.save(user);
    }

}
