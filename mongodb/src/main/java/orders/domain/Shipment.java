package orders.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = false)
@Data
@Document
@ToString(callSuper = true)
public class Shipment extends BaseEntity {

    private String id;
}
