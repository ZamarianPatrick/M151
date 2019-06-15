package ch.patrick.shop.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Entität für Benutzer
 *
 * @author Patrick
 * @version 1.0
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_customer")
    @SequenceGenerator(name = "seq_customer", allocationSize = 10)
    private Long id;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String firstname;

    @NotNull
    @NotEmpty
    private String surname;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registeredSince;

    @NotNull
    @NotEmpty
    private String telephonNumber;

    @NotNull
    @NotEmpty
    private String email;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "addressFk")
    private Address address;

    public Customer() {
        this.registeredSince = new Date();
    }

    public Customer(@NotNull @NotEmpty String username,
                    @NotNull @NotEmpty String password,
                    @NotNull @NotEmpty String firstname,
                    @NotNull @NotEmpty String surname,
                    @NotNull @NotEmpty String telephonNumber,
                    @NotNull @NotEmpty String email,
                    Address address) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.registeredSince = new Date();
        this.telephonNumber = telephonNumber;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getRegisteredSince() {
        return registeredSince;
    }

    public void setRegisteredSince(Date registeredSince) {
        this.registeredSince = registeredSince;
    }

    public String getTelephonNumber() {
        return telephonNumber;
    }

    public void setTelephonNumber(String telephonNumber) {
        this.telephonNumber = telephonNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
}
