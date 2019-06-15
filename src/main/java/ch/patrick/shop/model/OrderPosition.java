package ch.patrick.shop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Entität für Bestellungspositionen
 *
 * @author Patrick
 * @version 1.0
 */
@Entity
public class OrderPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_order_position")
    @SequenceGenerator(name = "seq_order_position", allocationSize = 10)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "orderFk")
    private Order order;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "articleFk")
    private Article article;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<ShopObject> items = new ArrayList<>();

    public OrderPosition() {
    }

    public OrderPosition(Order order, Article article, List<ShopObject> items) {
        this.order = order;
        this.article = article;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<ShopObject> getItems() {
        return items;
    }

    public void setItems(List<ShopObject> items) {
        this.items = items;
    }

    public Double getItemsPrice(){
        Double price = 0D;
        for(ShopObject shopObject : items){
            price += shopObject.getPrice();
        }
        return round(price, 2);
    }

    public Double getPrice(){
        return round(getItemsPrice() + article.getPrice(), 2);
    }


    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
