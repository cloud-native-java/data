package demo.order;

import demo.domain.BaseEntity;
import demo.product.Product;
import demo.shipment.Shipment;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
public class Order extends BaseEntity {

    private String id;
    private Shipment shipment;
    private Set<Product> products;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", shipment=" + shipment +
                ", products=" + products +
                "} " + super.toString();
    }
}
