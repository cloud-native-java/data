package orders.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Data
@Document
@ToString(callSuper = true)
public class Invoice extends BaseEntity {

    private String id;
    private Set<Order> orders;
}
