package customer.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A simple {@link Address} entity for an account.
 *
 * @author Kenny Bastani
 */
@Entity
@Table(name = "address")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Address {

    private
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    private final String street1, street2, state, city, country, addressType;
    private final Integer zipCode;

    Address() {
        street2 = null;
        street1 = null;
        state = null;
        city = null;
        country = null;
        addressType = null;
        zipCode = null;
    }

    public AddressId getId() {
        return new AddressId(id);
    }

    @Value
    @Embeddable
    @RequiredArgsConstructor
    @SuppressWarnings("serial")
    public static class AddressId implements Serializable {

        private final Long addressId;

        AddressId() {
            this.addressId = null;
        }
    }

}
