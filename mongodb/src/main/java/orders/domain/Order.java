package orders.domain;

import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document
public class Order {

    private String id;
    private Shipment shipment;
    private Set<Product> products;

    @Version
    private Long version;
    @CreatedDate
    private DateTime createdAt;
    @LastModifiedDate
    private DateTime lastModified;

}
