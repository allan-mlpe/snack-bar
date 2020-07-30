package com.dextra.snackbar.model.discount;

import com.dextra.snackbar.model.Snack;
import com.dextra.snackbar.model.SnackItem;

import java.util.*;

public class DiscountCalculatorManager {

    private Set<IDiscountCalculator> discounts;

    public DiscountCalculatorManager() {
        discounts = new HashSet<>();

        discounts.add(new LightSaleDiscountCalculator());
        discounts.add(new LotsOfCheeseDiscountCalculator());
        discounts.add(new LotsOfMeatSaleDiscountCalculator());
    }

    public double getDiscount(Snack snack) {

        return discounts.stream()
                .mapToDouble(dis -> dis.calculate(snack)).sum();
    }
}
