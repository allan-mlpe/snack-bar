package com.dextra.snackbar.domain.model;

import java.util.HashMap;
import java.util.Map;


public class Snack {

    private String name;

    private Map<String, Ingredient> ingredients;

    public Snack() {
        this.ingredients = new HashMap<>();
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

    public Map<String, Ingredient> getIngredients() {
        return ingredients;
    }
}
