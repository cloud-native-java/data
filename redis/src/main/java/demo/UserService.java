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
        this.redisTemplate = new RedisTemplate<>();
        this.redisTemplate.setConnectionFactory(redisTemplate.getConnectionFactory());
        this.redisTemplate.afterPropertiesSet();
    }

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

    @Cacheable(value = "user")
    public User getUser(String id) {
        return this.redisTemplate.opsForValue()
                .get(getFieldKey(id));
    }

    @CachePut(value = "user", key = "#id")
    public User updateUser(String id, User user) {
        if(userExists(user.getUserId())) {
            this.redisTemplate.opsForValue()
                    .set(getFieldKey(user), user);
        }

        return getUser(user.getUserId());
    }

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

    public boolean userExists(String id) {
        return getUser(id) != null;
    }

    private String getFieldKey(User user) {
        return getFieldKey(user.getUserId());
    }

    private String getFieldKey(String id) {
        return String.format("%s_%s", "users", id);
    }
}
