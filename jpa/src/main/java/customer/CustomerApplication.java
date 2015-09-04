package customer;

import customer.domain.Account;
import customer.domain.Address;
import customer.domain.CreditCard;
import customer.domain.Customer;
import customer.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.HashSet;

/**
 * The {@link CustomerApplication} is a cloud-native Spring Data JPA application that manages
 * a bounded context for @{link Customer}, @{link Account}, @{link CreditCard}, and @{link Address}
 *
 * @author Kenny Bastani
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class CustomerApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CustomerApplication.class);

    @Autowired
    CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    @Bean
    PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Override
    public void run(String... strings) throws Exception {

        // Create a new account
        Account account = new Account("12345", new HashSet<>(), new HashSet<>());

        // Create a new credit card for the account
        CreditCard creditCard = new CreditCard("0000-0000-0000-0000", "Visa");

        // Add the credit card to the account
        account.getCreditCards().add(creditCard);

        // Create a new customer for the account
        Customer customer = new Customer("Jane", "Doe", "jane.doe@gmail.com", account);

        // Create a new shipping address for the customer
        Address address = new Address("1600 Pennsylvania Ave NW", null,
                "DC", "Washington", "United States", "shipping", 20500);

        // Add address to customer
        account.getAddresses().add(address);

        log.info("Saving the Customer record to the CustomerRepository...");

        // Save the customer object, cascading saves to the object graph
        customer = customerRepository.save(customer);

        // Log out the state of the customer entity
        log.info(customer.toString());

        log.info("Getting the saved record from the CustomerRepository...");

        // Query for the customer object to ensure cascading persistence of the object graph
        log.info(customerRepository.findOne(customer.getId()
                .getCustomerId()).toString());
    }

}
