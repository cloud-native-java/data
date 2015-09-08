package customer.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A {@link CreditCard} entity is used for processing payments and belongs
 * to an account.
 *
 * @author Kenny Bastani
 */
@Entity
@Table(name = "creditCard")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    private final String number, type;

    CreditCard() {
        this.number = null;
        this.type = null;
    }

    public CreditCardId getId() {
        return new CreditCardId(id);
    }

    @Value
    @Embeddable
    @RequiredArgsConstructor
    @SuppressWarnings("serial")
    public static class CreditCardId implements Serializable {

        private final Long creditCardId;

        CreditCardId() {
            this.creditCardId = null;
        }
    }

}
