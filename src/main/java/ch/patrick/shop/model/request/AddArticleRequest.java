package ch.patrick.shop.model.request;

import java.util.ArrayList;
import java.util.List;

/**
 * Anfrage für einen Artikel hinzuzufügen
 *
 * @author Patrick
 * @version 1.0
 */
public class AddArticleRequest {

    /**
     * Name des Bildes
     */
    private String icon;

    /**
     * Name des Artikels
     */

    private String name;

    /**
     * Preis des Artikel
     */

    private Double price;

    /**
     * Wahrheitswert ist es ein Artikel, wenn nein ist es ein Inhalt
     */

    private Boolean isPackage;

    /**
     * Liste für verfügbare Inhalte, sofern es ein Artikel ist
     */

    private List<Long> availableItems = new ArrayList<Long>();

    public AddArticleRequest() {

    }

    public AddArticleRequest(String icon, String name, Double price) {
        this.icon = icon;
        this.name = name;
        this.price = price;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public Boolean getIsPackage() {
        return isPackage;
    }

    public void setIsPackage(Boolean aPackage) {
        isPackage = aPackage;
    }

    public List<Long> getAvailableItems() {
        return availableItems;
    }

    public void setAvailableItems(List<Long> availableItems) {
        this.availableItems = availableItems;
    }
}
