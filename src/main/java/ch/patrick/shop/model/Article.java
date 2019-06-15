package ch.patrick.shop.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entität für Artikel
 *
 * @author Patrick
 * @version 1.0
 */
@Entity
public class Article extends ShopObject{

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @LazyCollection(LazyCollectionOption.FALSE)
    List<ShopObject> availableItems = new ArrayList<>();

    public List<ShopObject> getAvailableItems() {
        return availableItems;
    }
}
