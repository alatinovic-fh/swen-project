package org.example.application.service;

import org.example.application.data.UserMemoryRepository;
import org.example.application.data.UserRepository;
import org.example.application.dto.UserCredentials;
import org.example.application.dto.UserData;
import org.example.application.entity.User;
import org.example.application.exception.AuthenticationFailedException;
import org.example.application.exception.UserAlreadyExistsException;
import org.example.application.exception.UserNotFoundException;

/**
 * This class is the bridge between the Contoller
 * and Repository
 *
 * @author Aleksandar Latinovic
 * */
public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserMemoryRepository();
    }


    public UserCredentials create (UserCredentials userCredentials) throws UserAlreadyExistsException {
        return userRepository.save(userCredentials);
    }

    public String auth(User user) throws AuthenticationFailedException, UserNotFoundException {
        // TODO Change User to UserCredentials
        User storedUser = userRepository.findUserByUsername(user.getUsername());
        if (storedUser.getPassword().equals(user.getPassword())) {
            return user.getUsername()+"-mtcgToken";
        }
        throw new AuthenticationFailedException("Login failed");
    }

    public UserData getUserData(String token, String searchedUser)  throws AuthenticationFailedException, UserNotFoundException {
        if(token.equals("Bearer admin-mtcgToken") || token.equals("Bearer " + searchedUser+"-mtcgToken")) {
            User user = userRepository.findUserByUsername(searchedUser);
            return new UserData(user.getFullName(), user.getBio(), user.getImage());
        }
        throw new AuthenticationFailedException("Not Authorized");
    }

    public UserData setUserData(String token, String searchedUser, UserData userData) {
        if(token.equals("Bearer admin-mtcgToken") || token.equals("Bearer " + searchedUser+"-mtcgToken")) {
            return userRepository.update(searchedUser, userData);
        }
        throw new AuthenticationFailedException("Not Authorized");
    }
}
