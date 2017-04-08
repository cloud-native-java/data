package demo.catalog;

import demo.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
public class Catalog {

 @GraphId
 private Long id;

 @Relationship(type = "HAS_PRODUCT", direction = Relationship.OUTGOING)
 private Set<Product> products = new HashSet<>();

 private String name;

 public Catalog(String n, Collection<Product> p) {
  this.name = n;
  this.products.addAll(p);
 }

 public Catalog(String name) {
  this.name = name;
 }

}
