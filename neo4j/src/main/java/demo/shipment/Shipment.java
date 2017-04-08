package demo.shipment;

import demo.address.Address;
import demo.inventory.Inventory;
import demo.warehouse.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
public class Shipment {

 @GraphId
 private Long id;

 @Relationship(type = "CONTAINS_PRODUCT")
 private Set<Inventory> inventories = new HashSet<>();

 @Relationship(type = "SHIP_TO")
 private Address deliveryAddress;

 @Relationship(type = "SHIP_FROM")
 private Warehouse fromWarehouse;

 private ShipmentStatus shipmentStatus;

 public Shipment(Set<Inventory> inventories, Address deliveryAddress,
  Warehouse fromWarehouse, ShipmentStatus shipmentStatus) {
  this.inventories = inventories;
  this.deliveryAddress = deliveryAddress;
  this.fromWarehouse = fromWarehouse;
  this.shipmentStatus = shipmentStatus;
 }
}
