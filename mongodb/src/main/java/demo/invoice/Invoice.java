package demo.invoice;

import demo.address.Address;
import demo.address.AddressType;
import demo.data.BaseEntity;
import demo.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Invoice extends BaseEntity {

 @Id
 private String invoiceId;

 private String customerId;

 private List<Order> orders = new ArrayList<Order>();

 private Address billingAddress;

 private InvoiceStatus invoiceStatus;

 public Invoice(String customerId, Address billingAddress) {
  this.customerId = customerId;
  this.billingAddress = billingAddress;
  this.billingAddress.setAddressType(AddressType.BILLING);
  this.invoiceStatus = InvoiceStatus.CREATED;
 }

 public void addOrder(Order order) {
  order.setAccountNumber(this.customerId);
  orders.add(order);
 }
}