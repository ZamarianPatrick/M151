package ch.patrick.shop.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Entität für Orte
 *
 * @author Patrick
 * @version 1.0
 */
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_city")
    @SequenceGenerator(name = "seq_city", allocationSize = 10)
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String zip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
