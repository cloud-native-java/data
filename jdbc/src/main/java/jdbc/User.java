package jdbc;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // <1>
public class User {

    //<2>
    Long id;
    String firstName;
    String lastName;
    String email;

    public User() { //<3>

    }

    public User(Long id, String firstName, String lastName, String email) { //<4>
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Id // <5>
    public Long getId() {
        return id;
    }

    public void setId(Long id) { //<6>
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
