package orders.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = false)
@Data
@Document
@ToString(callSuper = true)
public class Product extends BaseEntity  {

    private String id;
    private final String name, sku;
}
