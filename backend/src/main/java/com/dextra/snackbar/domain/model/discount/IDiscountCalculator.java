package com.dextra.snackbar.domain.model.discount;

import com.dextra.snackbar.domain.model.Snack;

public interface IDiscountCalculator {

    boolean isEligible(Snack snack);

    double calculate(Snack snack);
}
