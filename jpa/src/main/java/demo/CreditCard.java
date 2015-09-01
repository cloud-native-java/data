package demo;

import javax.persistence.*;

@Entity
@Table(name = "creditCard")
public class CreditCard {
    Long id;
    String number;
    String type;
    Account account;

    public CreditCard(String number, String type, Account account) {
        this.number = number;
        this.type = type;
        this.account = account;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId", nullable = false)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", accountId=" + account.getId() +
                '}';
    }
}
