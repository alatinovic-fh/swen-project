package org.example.application.service;

import org.example.application.data.UserMemoryRepository;
import org.example.application.data.UserRepository;
import org.example.application.entity.User;
import org.example.application.exception.AuthenticationFailedException;
import org.example.application.exception.UserAlreadyExistsException;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserMemoryRepository();
    }

    public User create (User user) throws UserAlreadyExistsException {
        return userRepository.save(user);
    }

    public String auth(User user) throws AuthenticationFailedException {
        if (userRepository.verify(user)){
            return user.getUsername() +"-mtcgToken";
        }
        return null;
    }
}
