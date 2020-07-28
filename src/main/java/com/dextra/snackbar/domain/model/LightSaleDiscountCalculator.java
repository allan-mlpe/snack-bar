package com.dextra.snackbar.domain.model;

import java.util.Map;

public class LightSaleDiscountCalculator extends AbstractDiscountCalculator {

    public LightSaleDiscountCalculator(Snack snack) {
        super(snack);
    }

    @Override
    public boolean isEligible() {
        return ingredients.containsKey("lettuce") && !ingredients.containsKey("bacon");
    }

    @Override
    public double calculate() {
        double discount = 0d;

        if (isEligible()) {
            discount = 0.1 * fullPrice;
        }
        return discount;
    }
}
