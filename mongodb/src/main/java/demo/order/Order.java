package demo.order;

import demo.address.Address;
import demo.address.AddressType;
import demo.data.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Order extends BaseEntity {

 @Id
 private String orderId;

 private String accountNumber;

 private OrderStatus orderStatus;

 private List<LineItem> lineItems = new ArrayList<>();

 private Address shippingAddress;

 public Order(String accountNumber, Address shippingAddress) {
  this.accountNumber = accountNumber;
  this.shippingAddress = shippingAddress;
  this.shippingAddress.setAddressType(AddressType.SHIPPING);
  this.orderStatus = OrderStatus.PENDING;
 }

 public void addLineItem(LineItem lineItem) {
  this.lineItems.add(lineItem);
 }
}
