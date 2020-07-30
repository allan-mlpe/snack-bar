package com.dextra.snackbar.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
public class Snack {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "snack_id", nullable = false)
    private List<SnackItem> snackItems;

    private Boolean custom = true;

    public Snack() {
        this.snackItems = new ArrayList<>();
    }

    public Double sumFullPrice() {
        return this.snackItems
                .stream()
                .mapToDouble(item -> item.getIngredient().getPrice() * item.getQuantity())
                .reduce(0d, (total, price) -> total + price);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SnackItem> getSnackItems() {
        return snackItems;
    }

    public void setSnackItems(List<SnackItem> snackItems) {
        this.snackItems = snackItems;
    }

    public Boolean getCustom() {
        return custom;
    }

    public void setCustom(Boolean custom) {
        this.custom = custom;
    }

    public Map<String, SnackItem> buildSnackItemMap() {
        Map<String, SnackItem> snackItemMap = new HashMap<>();

        Iterator<SnackItem> iterator = snackItems.iterator();

        while(iterator.hasNext()) {
            SnackItem item = iterator.next();
            String ingredientName = item.getIngredient().getName();

            if (!snackItemMap.containsKey(ingredientName)) {
                snackItemMap.put(ingredientName, item);
            }
        }

        return snackItemMap;
    }
}
