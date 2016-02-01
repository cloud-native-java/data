package demo.user;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * The {@link UserService} provides basic CRUD data management for the {@link User} domain class.
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
public interface UserService {

    /**
     * Create a new user or replace an existing user with the same ID
     *
     * @param user is the new {@link User} you want to create
     * @return the user that was created
     */
    @CacheEvict(value = "user", key = "#user.getUserId()")
    User createUser(User user);

    /**
     * Gets a user by ID
     *
     * @param id is the identifier of the user you want to retrieve
     * @return the desired {@link User} or null if no user with the ID exists
     */
    @Cacheable(value = "user")
    User getUser(String id);

    /**
     * Updates an existing user
     *
     * @param user is the {@link User} you would like to update
     * @return the updated {@link User}
     */
    @CachePut(value = "user", key = "#id")
    User updateUser(String id, User user);

    /**
     * Delete a user with the specified ID
     *
     * @param id is the identifier of the {@link User} to delete
     * @return true if the user was deleted
     */
    @CacheEvict(value = "user", key = "#id")
    boolean deleteUser(String id);

    /**
     * Returns true if a user exists
     *
     * @param id is the identifier of the {@link User}
     * @return true if the user exists
     */
    boolean userExists(String id);
}
