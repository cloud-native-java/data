package demo.invoice;

import demo.domain.BaseEntity;
import demo.order.Order;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * A simple domain class for the {@link Invoice} concept of the order context.
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
@Document
public class Invoice extends BaseEntity {

    private String id;
    private Set<Order> orders;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", orders=" + orders +
                "} " + super.toString();
    }
}
