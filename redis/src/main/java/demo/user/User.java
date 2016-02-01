package demo.user;

import java.io.Serializable;
import java.util.UUID;

/**
 * This model represents a user.
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
public class User implements Serializable {

    private String userId;
    private String firstName;
    private String lastName;

    public User() {
        userId = UUID.randomUUID().toString();
    }

    public User(String firstName, String lastName) {
        userId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
