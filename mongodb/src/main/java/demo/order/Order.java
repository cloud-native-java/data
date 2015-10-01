package demo.order;

import demo.domain.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A simple domain class for the {@link Order} concept in the order context.
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
@Document
public class Order extends BaseEntity {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                "} " + super.toString();
    }
}
