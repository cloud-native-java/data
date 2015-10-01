package demo.product;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * A simple domain class for the {@link Product} concept in the order context.
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
@NodeEntity
public class Product {

    @GraphId
    private Long id;
    private String name, sku;

    public Product() {
    }

    public Product(String name, String sku) {
        this.name = name;
        this.sku = sku;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                "} " + super.toString();
    }
}
