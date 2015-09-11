package customer.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * This entity stores information about a {@link Customer}'s {@link Account}.
 *
 * @author Kenny Bastani
 */
@Entity
@Table(name = "account")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Account {

    private
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    private final String accountNumber;

    private
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    final Set<CreditCard> creditCards;

    private
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    final Set<Address> addresses;

    public Account() {
        this.accountNumber = null;
        this.creditCards = new HashSet<>();
        this.addresses = new HashSet<>();
    }

    public AccountId getId() {
        return new AccountId(id);
    }

    @Value
    @Embeddable
    @RequiredArgsConstructor
    @SuppressWarnings("serial")
    public static class AccountId implements Serializable {

        private final Long accountId;

        AccountId() {
            this.accountId = null;
        }
    }
}
