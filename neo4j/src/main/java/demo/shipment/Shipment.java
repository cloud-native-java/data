package demo.shipment;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * A simple domain class for the {@link Shipment}
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
@NodeEntity
public class Shipment {

    @GraphId
    private Long id;

    public Shipment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "id='" + id + '\'' +
                "} " + super.toString();
    }
}
