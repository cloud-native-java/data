package demo.inventory;

import demo.product.Product;
import demo.warehouse.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
public class Inventory {

 @GraphId
 private Long id;

 private String inventoryNumber;

 @Relationship(type = "PRODUCT_TYPE", direction = "OUTGOING")
 private Product product;

 @Relationship(type = "STOCKED_IN", direction = "OUTGOING")
 private Warehouse warehouse;

 private InventoryStatus status;

 public Inventory(String inventoryNumber, Product product, Warehouse warehouse,
  InventoryStatus status) {
  this.inventoryNumber = inventoryNumber;
  this.product = product;
  this.warehouse = warehouse;
  this.status = status;
 }

}
