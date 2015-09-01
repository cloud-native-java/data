package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        // Create a new account
        Account account = new Account("123456");

        // Create a new credit card for the account
        CreditCard creditCard = new CreditCard("0000-0000-0000-0000", "Visa", account);

        // Add the credit card to the account
        account.setCreditCards(new HashSet<>(Arrays.asList(creditCard)));

        // Create a new customer for the account
        Customer customer = new Customer(account, "Jane", "Doe", "jane.doe@gmail.com");

        // Create a new shipping addresses for the customer
        Address address = new Address(customer, "shipping", "1600 Pennsylvania Ave NW",
                null, "DC", "Washington", 20500, "United States");

        // Add address to customer
        customer.setAddresses(new HashSet<>(Arrays.asList(address)));

        // Save the customer object, cascading saves to the object graph
        log.info(String.valueOf(customerRepository.save(customer)));
    }
}
