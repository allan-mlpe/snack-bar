package com.dextra.snackbar.model.discount;

import com.dextra.snackbar.model.Snack;
import com.dextra.snackbar.model.SnackItem;
import com.dextra.snackbar.util.Constants;

import java.util.Map;

public class LightSaleDiscountCalculator implements IDiscountCalculator {

    private Boolean isEligible(Map<String, SnackItem> snackItemMap) {
        return snackItemMap.containsKey(Constants.LETTUCE_INGREDIENT_NAME)
                && !snackItemMap.containsKey(Constants.BACON_INGREDIENT_NAME);
    }

    @Override
    public double calculate(Snack snack) {
        Double discount = 0d;
        Double fullPrice = snack.sumFullPrice();
        Map<String, SnackItem> snackItemMap = snack.buildSnackItemMap();

        if (isEligible(snackItemMap)) {
            discount = 0.1 * fullPrice;
        }
        return discount;
    }
}
