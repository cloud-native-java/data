package jdbc;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    // Private fields <1>
    Long id;
    String firstName;
    String lastName;
    String email;

    // No argument constructor for JPA <2>
    public User() {
    }

    // Overloaded constructors can be used to initialize required fields <3>
    public User(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Public getters and setters <4>

    /**
     * An ID field is required for most Spring Data projects and is used
     * to uniquely identify an object in a data store.
     */
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
