package ch.patrick.shop.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Enität für Adressen
 *
 * @author Patrick
 * @version 1.0
 */
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address")
    @SequenceGenerator(name = "seq_address", allocationSize = 10)
    private Long id;

    @NotNull
    @NotEmpty
    private String street;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "cityFk")
    private City city;

    public Address() {

    }

    public Address(@NotNull @NotEmpty String street, City city) {
        this.street = street;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
