package demo.creditcard;

import demo.data.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CreditCard extends BaseEntity {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id;

 private String number;

 @Enumerated(EnumType.STRING)
 private CreditCardType type;

 public CreditCard(String number, CreditCardType type) {
  this.number = number;
  this.type = type;
 }
}
