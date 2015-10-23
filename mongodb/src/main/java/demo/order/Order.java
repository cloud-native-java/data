package demo.order;

import demo.domain.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple domain class for the {@link Order} concept in the order context.
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
@Document
public class Order extends BaseEntity {

    private String id;
    private String accountNumber;
    private List<LineItem> lineItems = new ArrayList<>();
    private ShippingAddress shippingAddress;

    public Order(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", lineItems=" + lineItems +
                ", shippingAddress=" + shippingAddress +
                "} " + super.toString();
    }
}
