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
public class Invoice {

    private String id;
    private Set<Order> orders;

    @Version
    private Long version;
    @CreatedDate
    private DateTime createdAt;
    @LastModifiedDate
    private DateTime lastModified;
}
