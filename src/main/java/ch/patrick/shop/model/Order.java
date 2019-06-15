package ch.patrick.shop.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entität für Bestellungen
 *
 * @author Patrick
 * @version 1.0
 */
@Entity(name = "shopOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_order")
    @SequenceGenerator(name = "seq_order", allocationSize = 10)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "customerFk")
    private Customer customer;

    @OneToMany(
            mappedBy = "order",
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER)
    private List<OrderPosition> orderPositions = new ArrayList<>();

    @NotNull
    private LocalDate orderDate;

    private String comments;

    public Order(Customer customer, @NotNull @NotEmpty LocalDate date, @NotNull @NotEmpty String comments) {
        this(customer, date);
        this.comments = comments;
    }

    public Order(Customer customer, @NotNull @NotEmpty LocalDate date){
        this.customer = customer;
        this.orderDate = date;
    }

    public Order() {

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(List<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Double getPrice(){
        Double price = 0D;
        for(OrderPosition position : this.orderPositions){
            price += position.getPrice();
        }
        price = round(price, 2);
        return price;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
