package demo.product;

import demo.domain.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product extends BaseEntity {

    private String id;
    private String name, sku;

    public Product(String name, String sku) {
        this.name = name;
        this.sku = sku;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
