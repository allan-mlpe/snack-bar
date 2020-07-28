package com.dextra.snackbar.domain.model;

import java.util.Map;

public abstract class AbstractDiscountCalculator implements IDiscountCalculator {

    protected Map<String, Ingredient> ingredients;

    protected double fullPrice;

    public AbstractDiscountCalculator(Snack snack) {
        this.ingredients = snack.getIngredients();
        this.fullPrice = snack.getFullPrice();
    }
}
