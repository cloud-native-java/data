package demo.shipment;

import demo.domain.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Shipment extends BaseEntity {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "id='" + id + '\'' +
                "} " + super.toString();
    }
}
