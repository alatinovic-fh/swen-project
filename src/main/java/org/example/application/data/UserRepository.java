package org.example.application.data;

import org.example.application.entity.User;

/**
 * Saves The use
 */
public interface UserRepository {

    public User save(User user);

    String verify(User user);
}
