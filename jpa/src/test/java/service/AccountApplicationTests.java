package service;

import demo.AccountApplication;
import demo.account.Account;
import demo.address.Address;
import demo.address.AddressType;
import demo.creditcard.CreditCard;
import demo.creditcard.CreditCardType;
import demo.customer.Customer;
import demo.customer.CustomerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountApplication.class)
@ActiveProfiles(profiles = "test")
public class AccountApplicationTests {

 @Autowired
 private CustomerRepository customerRepository;

 @Test
 public void customerTest() {
  Account account = new Account("12345");
  Customer customer = new Customer("Jane", "Doe", "jane.doe@gmail.com", account);
  CreditCard creditCard = new CreditCard("1234567801234567",
   CreditCardType.VISA);

  customer.getAccount().getCreditCards().add(creditCard);

  String street1 = "1600 Pennsylvania Ave NW";
  Address address = new Address(street1, null, "DC", "Washington",
   "United States", AddressType.SHIPPING, 20500);
  customer.getAccount().getAddresses().add(address);

  customer = customerRepository.save(customer);
  Customer persistedResult = customerRepository.findOne(customer.getId());
  Assert.assertNotNull(persistedResult.getAccount()); // <1>
  Assert.assertNotNull(persistedResult.getCreatedAt());
  Assert.assertNotNull(persistedResult.getLastModified()); // <2>

  Assert.assertTrue(persistedResult.getAccount().getAddresses().stream()
   .anyMatch(add -> add.getStreet1().equalsIgnoreCase(street1))); // <3>
 }
}