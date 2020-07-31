package com.dextra.snackbar.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "snackbar_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "snack_id", nullable = false)
    private Snack snack;

    @Column(nullable = false)
    private Double price;

    private Double discount = 0d;

    public Order() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Snack getSnack() {
        return snack;
    }

    public void setSnack(Snack snack) {
        this.snack = snack;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public static Order createInstance(Snack snack) {
        Order order = new Order();

        snack.setId(null);
        snack.setMenuItem(false);
        order.setSnack(snack);
        order.setPrice(snack.sumFullPrice());

        return order;
    }
}
