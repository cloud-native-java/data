package service;

import demo.CustomerApplication;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import demo.account.Account;
import demo.address.Address;
import demo.address.AddressType;
import demo.creditcard.CreditCard;
import demo.creditcard.CreditCardType;
import demo.customer.Customer;
import demo.customer.CustomerRepository;

import java.util.HashSet;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CustomerApplication.class)
public class CustomerApplicationTest extends TestCase {

    private Logger log = LoggerFactory.getLogger(CustomerApplicationTest.class);

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void customerTest() {

        log.info("*** Starting Customer Test");

        // Create a new account
        Account account = new Account("12345", new HashSet<>(), new HashSet<>());

        // Create a new credit card for the account
        CreditCard creditCard = new CreditCard("1234567801234567", CreditCardType.VISA);

        // Add the credit card to the account
        account.getCreditCards().add(creditCard);

        // Create a new customer for the account
        Customer customer = new Customer("Jane", "Doe", "jane.doe@gmail.com", account);

        // Create a new shipping address for the customer
        Address address = new Address("1600 Pennsylvania Ave NW", null,
                "DC", "Washington", "United States", AddressType.SHIPPING, 20500);

        // Add address to customer
        account.getAddresses().add(address);

        log.info("Saving the Customer record to the CustomerRepository...");

        // Save the customer object, cascading saves to the object graph
        customer = customerRepository.save(customer);

        // Log out the state of the customer entity
        log.info(customer.toString());

        log.info("Getting the saved record from the CustomerRepository...");

        // Query for the customer object to ensure cascading persistence of the object graph
        log.info(customerRepository.findOne(customer.getId()).toString());

    }

}