package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * The {@link AccountApplication} is a
 * cloud-native Spring Boot application
 * that manages a bounded context for
 * @{link Customer},
 *
 * @author Kenny Bastani
 * @author Josh Long
 * @{link Account}, @{link CreditCard},
 * and @{link Address}
 */
@SpringBootApplication
@EnableJpaAuditing
public class AccountApplication {

 public static void main(String[] args) {
  SpringApplication.run(AccountApplication.class, args);
 }
}
