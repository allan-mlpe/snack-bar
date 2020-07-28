package com.dextra.snackbar.domain.model;

public interface IDiscountCalculator {

    public boolean isEligible();

    public double calculate();
}
