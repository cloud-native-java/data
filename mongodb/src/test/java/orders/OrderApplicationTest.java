package orders;

import demo.OrderApplication;
import demo.address.Address;
import demo.invoice.Invoice;
import demo.invoice.InvoiceRepository;
import demo.order.LineItem;
import demo.order.Order;
import demo.order.OrderRepository;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class OrderApplicationTest {

 @Autowired
 private OrderRepository orderRepository;

 @Autowired
 private InvoiceRepository invoiceRepository;

 @Before
 @After
 public void reset() {
  orderRepository.deleteAll();
  invoiceRepository.deleteAll();
 }

 @Test
 public void orderTest() {

  Address shippingAddress = new Address("1600 Pennsylvania Ave NW", null, "DC",
   "Washington", "United States", 20500);
  Order order = new Order("12345", shippingAddress);
  order.addLineItem(new LineItem("Best. Cloud. Ever. (T-Shirt, Men's Large)",
   "SKU-24642", 1, 21.99, .06));
  order.addLineItem(new LineItem("Like a BOSH (T-Shirt, Women's Medium)",
   "SKU-34563", 3, 14.99, .06));
  order.addLineItem(new LineItem(
   "We're gonna need a bigger VM (T-Shirt, Women's Small)", "SKU-12464", 4,
   13.99, .06));
  order.addLineItem(new LineItem("cf push awesome (Hoodie, Men's Medium)",
   "SKU-64233", 2, 21.99, .06));
  order = orderRepository.save(order);
  // <1>
  Assert.assertNotNull(order.getOrderId());
  Assert.assertEquals(order.getLineItems().size(), 4);

  // <2>
  Assert.assertEquals(order.getLastModified(), order.getCreatedAt());
  order = orderRepository.save(order);
  Assert.assertNotEquals(order.getLastModified(), order.getCreatedAt());

  // <3>
  Address billingAddress = new Address("875 Howard St", null, "CA",
   "San Francisco", "United States", 94103);
  String accountNumber = "918273465";

  Invoice invoice = new Invoice(accountNumber, billingAddress);
  invoice.addOrder(order);
  invoice = invoiceRepository.save(invoice);
  Assert.assertEquals(invoice.getOrders().size(), 1);
 }
}