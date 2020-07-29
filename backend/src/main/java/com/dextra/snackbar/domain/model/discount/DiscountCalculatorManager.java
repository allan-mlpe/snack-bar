package com.dextra.snackbar.domain.model.discount;

import com.dextra.snackbar.domain.model.Snack;

import java.util.HashSet;
import java.util.Set;

public class DiscountCalculatorManager {

    private Set<IDiscountCalculator> discounts;

    public DiscountCalculatorManager() {
        discounts = new HashSet<>();

        discounts.add(new LightSaleDiscountCalculator());
        discounts.add(new LotsOfCheeseDiscountCalculator());
        discounts.add(new LotsOfMeatSaleDiscountCalculator());
    }

    public double getDiscount(Snack snack) {
        return discounts.stream().mapToDouble(dis -> dis.calculate(snack)).sum();
    }
}
