package demo.warehouse;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * A simple domain class for the {@link Warehouse}
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
@NodeEntity
public class Warehouse {

    @GraphId
    private Long id;

    public Warehouse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
