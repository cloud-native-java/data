package demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(RedisApplication.class)
public class RedisApplicationTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        cleanUp();
    }

    @After
    public void tearDown() {
        cleanUp();
    }

    private void cleanUp() {
        // Make sure that test records do not yet exist
        Arrays.asList(
                "test_create_jane_doe",
                "test_get_john_doe",
                "test_update_johnny_appleseed",
                "test_delete_sally_ride")
                .forEach(id -> userService.deleteUser(id));
    }

    @Test
    public void testCreateUser() throws Exception {
        // Setup test data
        User expectedUser = new User("Jane", "Doe");
        expectedUser.setUserId("test_create_jane_doe");

        // Test create user success
        this.mvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(expectedUser))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated());

        // Test create user conflict
        this.mvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(expectedUser))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isConflict());

        // Clean up
        userService.deleteUser(expectedUser.getUserId());
    }

    @Test
    public void testGetUser() throws Exception {
        // Setup test data
        User expectedUser = new User("John", "Doe");
        expectedUser.setUserId("test_get_john_doe");

        userService.createUser(expectedUser);

        // Test get user success
        this.mvc.perform(get("/users/{id}", expectedUser.getUserId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(objectMapper.writeValueAsString(expectedUser)));

        // Delete user
        userService.deleteUser(expectedUser.getUserId());

        // Test get user not found
        this.mvc.perform(get("/users/{id}", expectedUser.getUserId()))
                .andExpect(status().isNotFound());

        // Clean up
        userService.deleteUser(expectedUser.getUserId());
    }

    @Test
    public void testUpdateUser() throws Exception {
        // Setup test data
        User expectedUser = new User("Johnny", "Appleseed");
        expectedUser.setUserId("test_update_johnny_appleseed");

        // Test get user not found
        this.mvc.perform(get("/users/{id}", expectedUser.getUserId()))
                .andExpect(status().isNotFound());

        // Test re-create user for cache invalidation
        userService.createUser(expectedUser);

        this.mvc.perform(get("/users/{id}", expectedUser.getUserId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(objectMapper.writeValueAsString(expectedUser)));

        // Change first name
        expectedUser.setFirstName("John");

        // Test update user for cache invalidation
        this.mvc.perform(put("/users/{id}", expectedUser.getUserId())
                .content(objectMapper.writeValueAsString(expectedUser))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNoContent());

        // Test that user was updated
        this.mvc.perform(get("/users/{id}", expectedUser.getUserId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(objectMapper.writeValueAsString(expectedUser)));

        // Clean up
        userService.deleteUser(expectedUser.getUserId());
    }

    @Test
    public void testDeleteUser() throws Exception {
        // Setup test data
        User expectedUser = new User("Sally", "Ride");
        expectedUser.setUserId("test_delete_sally_ride");
        userService.createUser(expectedUser);

        // Test getting the user to put into cache
        this.mvc.perform(get("/users/{id}", expectedUser.getUserId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(objectMapper.writeValueAsString(expectedUser)));

        // Delete the user and remove from cache
        this.mvc.perform(delete("/users/{id}", expectedUser.getUserId()))
                .andExpect(status().isNoContent());

        // Test get user not found
        this.mvc.perform(get("/users/{id}", expectedUser.getUserId()))
                .andExpect(status().isNotFound());
    }
}
