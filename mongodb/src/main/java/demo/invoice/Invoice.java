package demo.invoice;

import demo.domain.BaseEntity;
import demo.order.Order;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple domain class for the {@link Invoice} concept of the order context.
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
@Document
public class Invoice extends BaseEntity {

    private String id;
    private List<Order> orders = new ArrayList<Order>();
    private String accountNumber;

    public Invoice(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", orders=" + orders +
                ", accountNumber='" + accountNumber + '\'' +
                "} " + super.toString();
    }
}
