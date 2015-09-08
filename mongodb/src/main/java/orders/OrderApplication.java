package orders;

import orders.domain.Invoice;
import orders.domain.Order;
import orders.domain.Product;
import orders.domain.Shipment;
import orders.repositories.InvoiceRepository;
import orders.repositories.OrderRepository;
import orders.repositories.ProductRepository;
import orders.repositories.ShipmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
@EnableMongoRepositories
public class OrderApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(OrderApplication.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    ShipmentRepository shipmentRepository;

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    public MongoClientFactoryBean mongo() {
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        mongo.setHost("localhost");
        return mongo;
    }

    @Override
    public void run(String... strings) throws Exception {

        log.info("Creating a new product..");

        // Create a new product
        Product product = new Product("Fog Machine", "SKU-12345");

        // Save the product
        productRepository.save(product);

        // Create a new order
        Order order = new Order();

        // Add fog machine to the new order
        order.setProducts(new HashSet<>(Arrays.asList(new Product[]{product})));

        // Create a new shipment
        Shipment shipment = new Shipment();

        // Save the shipment
        shipmentRepository.save(shipment);

        // Add the shipment to the order
        order.setShipment(shipment);

        // Create a new invoice
        Invoice invoice = new Invoice();

        // Save the invoice
        invoice = invoiceRepository.save(invoice);

        // Add the order to the invoice
        invoice.setOrders(new HashSet<>(Arrays.asList(new Order[]{order})));

        // Save the order
        order = orderRepository.save(order);

        // Log the result
        log.info(order.toString());
    }
}
