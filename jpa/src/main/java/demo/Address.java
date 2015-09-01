package demo;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    Long id;
    String street1;
    String street2;
    String state;
    String city;
    Integer zipCode;
    String country;
    String addressType;
    Customer customer;

    public Address() {
    }

    public Address(Customer customer, String addressType, String street1, String street2, String state, String city, Integer zipCode, String country) {
        this.customer = customer;
        this.addressType = addressType;
        this.street1 = street1;
        this.street2 = street2;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street1='" + street1 + '\'' +
                ", street2='" + street2 + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zipCode=" + zipCode +
                ", country='" + country + '\'' +
                ", addressType='" + addressType + '\'' +
                ", customerId=" + customer.getId() +
                '}';
    }
}
