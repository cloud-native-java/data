package orders.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseEntity {

    private Long lastModified, createdAt;
}
