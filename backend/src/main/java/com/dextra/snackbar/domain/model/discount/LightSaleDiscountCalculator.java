package com.dextra.snackbar.domain.model.discount;

import com.dextra.snackbar.domain.model.Ingredient;
import com.dextra.snackbar.domain.model.Snack;

import java.util.Map;

public class LightSaleDiscountCalculator implements IDiscountCalculator {

    @Override
    public boolean isEligible(Snack snack) {
        Map<String, Ingredient> ingredients = snack.getIngredients();

        return ingredients.containsKey("lettuce") && !ingredients.containsKey("bacon");
    }

    @Override
    public double calculate(Snack snack) {
        double discount = 0d;
        double fullPrice = snack.getFullPrice();

        if (isEligible(snack)) {
            discount = 0.1 * fullPrice;
        }
        return discount;
    }
}
