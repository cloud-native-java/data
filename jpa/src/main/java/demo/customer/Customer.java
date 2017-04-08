package demo.customer;

import demo.account.Account;
import demo.data.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id;

 private String firstName;

 private String lastName;

 private String email;

 @OneToOne(cascade = CascadeType.ALL)
 private Account account;

 public Customer(String firstName, String lastName, String email,
  Account account) {
  this.firstName = firstName;
  this.lastName = lastName;
  this.email = email;
  this.account = account;
 }
}
