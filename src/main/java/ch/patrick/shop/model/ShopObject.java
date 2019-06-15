package ch.patrick.shop.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Entität für Inhalte
 *
 * @author Patrick
 * @version 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ShopObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_shop_object")
    @SequenceGenerator(name = "seq_shop_object", allocationSize = 10)
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private Double price;

    @NotNull
    @NotEmpty
    private String icon;

    public ShopObject() {

    }

    public ShopObject(@NotNull @NotEmpty String name, @NotNull @NotEmpty Double price, @NotNull @NotEmpty String icon) {
        this.name = name;
        this.price = price;
        this.icon = icon;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
