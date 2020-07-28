package com.dextra.snackbar.domain.model;

import java.util.Map;

public class CalculatePriceStrategy {

    public double calculate(Snack snack) {
        double fullPrice = snack.getFullPrice();

        return fullPrice;
    }

}
