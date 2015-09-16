package orders;

import demo.OrderApplication;
import junit.framework.TestCase;
import demo.invoice.Invoice;
import demo.order.Order;
import demo.product.Product;
import demo.shipment.Shipment;
import demo.invoice.InvoiceRepository;
import demo.order.OrderRepository;
import demo.product.ProductRepository;
import demo.shipment.ShipmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashSet;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrderApplication.class)
public class OrderApplicationTest extends TestCase {

    private Logger log = LoggerFactory.getLogger(OrderApplicationTest.class);

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

    @Test
    public void orderTest() {
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

        Product cloudFoundrySwag = new Product("Cloud Foundry Swag", "SKU-1765");

        cloudFoundrySwag = productRepository.save(cloudFoundrySwag);

        // Update the object
        order.getProducts().add(cloudFoundrySwag);

        // The lastModified and createdAt timestamps should now be different
        log.info(orderRepository.save(order).toString());
    }


}