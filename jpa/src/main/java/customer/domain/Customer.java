package customer.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The {@link Customer} entity is a root object in the customer bounded context.
 *
 * @author Kenny Bastani
 */
@Entity
@Table(name = "customer")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@SuppressWarnings({"serial", "unused" })
public class Customer implements Serializable {

    private
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    private final String firstName, lastName, email;

    private final
    @OneToOne(cascade = CascadeType.ALL)
    Account account;

    Customer() {
        firstName = null;
        lastName = null;
        email = null;
        account = null;
    }

    public CustomerId getId() {
        return new CustomerId(id);
    }

    @Value
    @Embeddable
    @RequiredArgsConstructor
    @SuppressWarnings("serial")
    public static class CustomerId implements Serializable {

        private final Long customerId;

        CustomerId() {
            this.customerId = null;
        }
    }

}
