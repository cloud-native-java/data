package demo;

import demo.address.AddressRepository;
import demo.catalog.CatalogRepository;
import demo.inventory.InventoryRepository;
import demo.product.ProductRepository;
import demo.shipment.ShipmentRepository;
import demo.warehouse.WarehouseRepository;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;

import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
class GraphConfiguration extends Neo4jConfiguration {

 @Autowired
 private Neo4jProperties properties;

 @Bean
 public Neo4jServer neo4jServer() {
  String uri = this.properties.getUri();
  String pw = this.properties.getPassword();
  String user = this.properties.getUsername();
  return new RemoteServer(uri, user, pw);
 }

 @Bean
 public SessionFactory getSessionFactory() {
  Class<?>[] packageClasses = { ProductRepository.class,
   ShipmentRepository.class, WarehouseRepository.class,
   AddressRepository.class, InventoryRepository.class, CatalogRepository.class };
  String[] packageNames = Arrays.stream(packageClasses)
   .map(c -> getClass().getPackage().getName()).collect(Collectors.toList())
   .toArray(new String[packageClasses.length]);
  return new SessionFactory(packageNames);
 }

 @Bean
 public Session getSession() throws Exception {
  return super.getSession();
 }
}
