package demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

 @Id
 private String id = UUID.randomUUID().toString();

 private String firstName, lastName;

 public User(String firstName, String lastName) {
  id = UUID.randomUUID().toString();
  this.firstName = firstName;
  this.lastName = lastName;
 }
}
