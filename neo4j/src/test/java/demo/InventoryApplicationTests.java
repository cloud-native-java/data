package demo;

import demo.address.Address;
import demo.address.AddressRepository;
import demo.catalog.Catalog;
import demo.catalog.CatalogRepository;
import demo.inventory.Inventory;
import demo.inventory.InventoryRepository;
import demo.inventory.InventoryStatus;
import demo.product.Product;
import demo.product.ProductRepository;
import demo.shipment.Shipment;
import demo.shipment.ShipmentRepository;
import demo.shipment.ShipmentStatus;
import demo.warehouse.Warehouse;
import demo.warehouse.WarehouseRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { InventoryApplication.class })
public class InventoryApplicationTests {

 private Logger log = LoggerFactory.getLogger(InventoryApplicationTests.class);

 @Autowired
 private ProductRepository productRepository;

 @Autowired
 private ShipmentRepository shipmentRepository;

 @Autowired
 private WarehouseRepository warehouseRepository;

 @Autowired
 private AddressRepository addressRepository;

 @Autowired
 private CatalogRepository catalogRepository;

 @Autowired
 private InventoryRepository inventoryRepository;
//
// @Autowired
// private Neo4jConfiguration neo4jConfiguration;

 @Autowired
 private Session session;

 @Before
 public void setup() {
  try {
   this.session
    .query("MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n, r;", new HashMap<>())
    .queryResults();
  }
  catch (Exception e) {
   e.printStackTrace();
   Assert.fail("Neo4j isn't running or this test can't connect to it!");
  }
 }

 @Test
 public void inventoryTest() {
  Warehouse warehouse = new Warehouse("Pivotal SF");

  List<Product> products = Arrays
   .asList(
    new Product("Best. Cloud. Ever. (T-Shirt, Men's Large)", "SKU-24642", 21.99),
    new Product("Like a BOSH (T-Shirt, Women's Medium)", "SKU-34563", 14.99),
    new Product("We're gonna need a bigger VM (T-Shirt, Women's Small)",
     "SKU-12464", 13.99),
    new Product("cf push awesome (Hoodie, Men's Medium)", "SKU-64233", 21.99))
   .stream().collect(Collectors.toList());

  productRepository.save(products);

  Product product1 = productRepository.findOne(products.get(0).getId());

  assertThat(product1, is(notNullValue()));
  assertThat(product1.getName(), is(products.get(0).getName()));
  assertThat(product1.getUnitPrice(), is(products.get(0).getUnitPrice()));

  Catalog catalog = new Catalog("Fall Catalog");

  catalog.getProducts().addAll(products);

  catalogRepository.save(catalog);

  Catalog catalog1 = catalogRepository.findOne(catalog.getId());

  assertThat(catalog1, is(notNullValue()));
  assertThat(catalog1.getName(), is(catalog.getName()));

  Address warehouseAddress = new Address("875 Howard St", null, "CA",
   "San Francisco", "United States", 94103);

  Address shipToAddress = new Address("1600 Amphitheatre Parkway", null, "CA",
   "Mountain View", "United States", 94043);

  // Save the addresses
  addressRepository.save(Arrays.asList(warehouseAddress, shipToAddress));

  Address address1 = addressRepository.findOne(shipToAddress.getId());

  assertThat(address1, is(notNullValue()));
  assertThat(address1.toString(), is(shipToAddress.toString()));

  Address address2 = addressRepository.findOne(warehouseAddress.getId());

  assertThat(address2, is(notNullValue()));
  assertThat(address2.toString(), is(warehouseAddress.toString()));

  log.info(warehouseAddress.toString());
  log.info(shipToAddress.toString());

  warehouse.setAddress(warehouseAddress);
  warehouse = warehouseRepository.save(warehouse);

  Warehouse warehouse1 = warehouseRepository.findOne(warehouse.getId());

  assertThat(warehouse1, is(notNullValue()));
  assertThat(warehouse1.toString(), is(warehouse.toString()));

  log.info(warehouse.toString());

  Warehouse finalWarehouse = warehouse;

  // Create a new set of inventories
  // with a
  // randomized inventory number
  Set<Inventory> inventories = products
   .stream()
   .map(
    a -> new Inventory(IntStream.range(0, 9)
     .mapToObj(x -> Integer.toString(new Random().nextInt(9)))
     .collect(Collectors.joining("")), a, finalWarehouse,
     InventoryStatus.IN_STOCK)).collect(Collectors.toSet());

  inventoryRepository.save(inventories);

  Shipment shipment = new Shipment(inventories, shipToAddress, warehouse,
   ShipmentStatus.SHIPPED);

  shipmentRepository.save(shipment);

  Shipment shipment1 = shipmentRepository.findOne(shipment.getId());

  assertThat(shipment1, is(notNullValue()));
  assertThat(shipment1.toString(), is(shipment.toString()));
 }

}
