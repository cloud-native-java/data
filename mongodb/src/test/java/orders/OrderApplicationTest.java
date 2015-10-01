package orders;

import demo.OrderApplication;
import demo.invoice.Invoice;
import demo.invoice.InvoiceRepository;
import demo.order.Order;
import demo.order.OrderRepository;
import junit.framework.TestCase;
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
    OrderRepository orderRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Test
    public void orderTest() {
        // Create a new order
        Order order = new Order();

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

        // The lastModified and createdAt timestamps should now be different
        log.info(orderRepository.save(order).toString());
    }
}