package demo.account;

import demo.address.Address;
import demo.creditcard.CreditCard;
import demo.customer.Customer;
import demo.data.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id; // <1>

 private String accountNumber;

 // <2>
 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
 private Set<CreditCard> creditCards = new HashSet<>();

 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
 private Set<Address> addresses = new HashSet<>();

 public Account(String accountNumber, Set<Address> addresses) {
  this.accountNumber = accountNumber;
  this.addresses.addAll(addresses);
 }

 public Account(String accountNumber) {
  this.accountNumber = accountNumber;
 }
}
