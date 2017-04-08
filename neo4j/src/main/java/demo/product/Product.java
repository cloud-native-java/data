package demo.product;

import demo.catalog.Catalog;
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
public class Product {

 @GraphId
 private Long id;

 private String name, productId;

 private Double unitPrice;

 public Product(String name, String productId, Double unitPrice) {
  this.name = name;
  this.productId = productId;
  this.unitPrice = unitPrice;
 }

}
