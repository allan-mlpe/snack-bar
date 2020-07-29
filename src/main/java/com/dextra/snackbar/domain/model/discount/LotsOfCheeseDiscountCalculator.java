package com.dextra.snackbar.domain.model.discount;

import com.dextra.snackbar.domain.model.Ingredient;
import com.dextra.snackbar.domain.model.Snack;

import java.util.Map;

public class LotsOfCheeseDiscountCalculator implements IDiscountCalculator {

    @Override
    public boolean isEligible(Snack snack) {
        Map<String, Ingredient> ingredients = snack.getIngredients();
        return ingredients.containsKey("cheese");
    }

    @Override
    public double calculate(Snack snack) {
        double discount = 0d;
        Map<String, Ingredient> ingredients = snack.getIngredients();

        if (this.isEligible(snack)) {
            Ingredient cheese = ingredients.get("cheese");
            int quantity = cheese.getQuantity();

            discount = (quantity / 3) * cheese.getPrice();
        }
        return discount;
    }
}
