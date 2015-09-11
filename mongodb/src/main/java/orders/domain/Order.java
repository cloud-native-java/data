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
public class Order extends BaseEntity  {

    private String id;
    private Shipment shipment;
    private Set<Product> products;

}
