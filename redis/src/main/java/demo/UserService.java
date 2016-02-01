package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private RedisTemplate<String, User> redisTemplate;

    @Autowired
    public UserService(RedisTemplate redisTemplate) {
        this.redisTemplate = (RedisTemplate<String, User>) redisTemplate;
    }

    /**
     * Create a new user
     *
     * @param user is the new {@link User} you want to create
     * @return the user that was created
     */
    @CacheEvict(value = "user", key = "#user.getUserId()")
    public User createUser(User user) {

        User result = null;

        if(!userExists(user.getUserId())) {
            this.redisTemplate.opsForValue()
                    .set(getFieldKey(user), user);

            result = getUser(user.getUserId());
        }

        return result;
    }

    /**
     * Gets a user by ID
     *
     * @param id is the identifier of the user you want to retrieve
     * @return the desired {@link User} or null if no user with the ID exists
     */
    @Cacheable(value = "user")
    public User getUser(String id) {
        return this.redisTemplate.opsForValue()
                .get(getFieldKey(id));
    }

    /**
     * Updates an existing user
     *
     * @param user is the {@link User} you would like to update
     * @return the updated {@link User}
     */
    @CachePut(value = "user", key = "#id")
    public User updateUser(String id, User user) {
        if(userExists(user.getUserId())) {
            this.redisTemplate.opsForValue()
                    .set(getFieldKey(user), user);
        }

        return getUser(user.getUserId());
    }

    /**
     * Delete a user with the specified ID
     *
     * @param id is the identifier of the {@link User} to delete
     * @return true if the user was deleted
     */
    @CacheEvict(value = "user", key = "#id")
    public boolean deleteUser(String id) {

        boolean deleted = false;

        if (userExists(id)) {
            this.redisTemplate.opsForValue()
                    .set(getFieldKey(id), null);

            deleted = true;
        }

        return deleted;
    }

    /**
     * Returns true if a user exists
     *
     * @param id is the identifier of the {@link User}
     * @return true if the user exists
     */
    public boolean userExists(String id) {
        return getUser(id) != null;
    }

    /**
     * Gets the field key for the user repository
     * @param user is the user
     * @return a field key for the user repository
     */
    private String getFieldKey(User user) {
        return getFieldKey(user.getUserId());
    }

    /**
     * Gets the field key for the user repository
     * @param id is the user's ID
     * @return a field key for the user repository
     */
    private String getFieldKey(String id) {
        return String.format("%s_%s", "users", id);
    }
}
