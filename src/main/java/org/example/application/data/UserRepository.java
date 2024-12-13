package org.example.application.data;

import org.example.application.dto.UserCredentials;
import org.example.application.dto.UserData;
import org.example.application.entity.User;

/**
 * Saves The use
 */
public interface UserRepository {

    public UserCredentials save(UserCredentials user);

    User findUserByUsername(String username);

    UserData update(String username, UserData userdata);
}
