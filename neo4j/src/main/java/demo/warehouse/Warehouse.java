package demo.warehouse;

import demo.address.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NoArgsConstructor
@AllArgsConstructor
@Data
@NodeEntity
public class Warehouse {

 @GraphId
 private Long id;

 private String name;

 @Relationship(type = "HAS_ADDRESS")
 private Address address;

 public Warehouse(String n, Address a) {
  this.name = n;
  this.address = a;
 }

 public Warehouse(String name) {
  this.name = name;
 }
}
