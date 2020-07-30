package com.dextra.snackbar.model.discount;

import com.dextra.snackbar.model.Snack;
import com.dextra.snackbar.model.SnackItem;
import com.dextra.snackbar.util.Constants;

import java.util.Map;

public class LotsOfCheeseDiscountCalculator implements IDiscountCalculator {

    private boolean isEligible(Map<String, SnackItem> snackItemMap) {
        return snackItemMap.containsKey(Constants.CHEESE_INGREDIENT_NAME);
    }

    @Override
    public double calculate(Snack snack) {
        double discount = 0d;
        Map<String, SnackItem> snackItemMap = snack.buildSnackItemMap();

        if (this.isEligible(snackItemMap)) {
            SnackItem item = snackItemMap.get(Constants.CHEESE_INGREDIENT_NAME);

            int quantity = item.getQuantity();

            discount = (quantity / 3) * item.getIngredient().getPrice();
        }
        return discount;
    }
}
