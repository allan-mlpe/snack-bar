package com.dextra.snackbar.domain.model;

import java.util.Map;

public class LotsOfMeatSaleDiscountCalculator extends AbstractDiscountCalculator {

    public LotsOfMeatSaleDiscountCalculator(Snack snack) {
        super(snack);
    }

    @Override
    public boolean isEligible() {
        return ingredients.containsKey("meat");
    }

    @Override
    public double calculate() {
        double discount = 0d;
        if (this.isEligible()) {
            Ingredient cheese = ingredients.get("meat");
            int quantity = cheese.getQuantity();

            discount = (quantity / 3) * cheese.getPrice();
        }
        return discount;
    }
}
