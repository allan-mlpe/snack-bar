package com.dextra.snackbar.domain.model;

import com.dextra.snackbar.domain.model.discount.DiscountCalculatorManager;

import java.util.HashMap;
import java.util.Map;

public class Snack {

    private String name;

    private final Map<String, Ingredient> ingredients;

    private final DiscountCalculatorManager discountCalculatorManager;

    public Snack() {
        this.ingredients = new HashMap<>();
        this.discountCalculatorManager = new DiscountCalculatorManager();
    }

    public void addIngredient(Ingredient ingredient) {
        String ingredientName = ingredient.getName();

        if (this.ingredients.containsKey(ingredientName)) {
            int currentQuantity = ingredient.getQuantity();

            this.ingredients.get(ingredientName).setQuantity(currentQuantity+1);
        } else {
            this.ingredients.put(ingredient.getName(), ingredient);
        }
    }

    public void removeIngredient(Ingredient ingredient) {
        String ingredientName = ingredient.getName();

        if (this.ingredients.containsKey(ingredientName)) {
            int currentQuantity = ingredient.getQuantity();

            if (currentQuantity > 1) {
                this.ingredients.get(ingredientName).setQuantity(currentQuantity-1);
            } else {
                this.ingredients.remove(ingredientName);
            }
        }
    }

    public double getFullPrice() {
        return this.ingredients
                .values()
                .stream()
                .mapToDouble(ingredient -> ingredient.getPrice() * ingredient.getQuantity())
                .reduce(0d, (total, ingredientPrice) -> total + ingredientPrice);
    }

    public double getFinalPrice() {
        return getFullPrice() - discountCalculatorManager.getDiscount(this);
    }

    public Map<String, Ingredient> getIngredients() {
        return ingredients;
    }
}
