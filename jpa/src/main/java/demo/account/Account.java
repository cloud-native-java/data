package demo.account;

import demo.address.Address;
import demo.creditcard.CreditCard;
import demo.customer.Customer;
import demo.data.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This entity stores information about a
 * {@link Customer}'s {@link Account}.
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
@Entity
public class Account extends BaseEntity {

	private Long id;
	private String accountNumber;
	private Set<CreditCard> creditCards;
	private Set<Address> addresses;

	public Account() {
	}

	public Account(String accountNumber) {
		this.accountNumber = accountNumber;
		this.creditCards = new HashSet<>();
		this.addresses = new HashSet<>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<CreditCard> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(Set<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "Account{" + "id=" + id + ", accountNumber='" + accountNumber + '\''
				+ ", creditCards=" + creditCards + ", addresses=" + addresses + "} "
				+ super.toString();
	}
}
