package com.dextra.snackbar.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LightSaleDiscountCalculatorTest {

    @Test
    public void checkEligibleSnack() {
        // Given
        Snack snack = new Snack();
        Ingredient lettuce = new Ingredient("lettuce", 0.20);
        Ingredient meat = new Ingredient("meat", 2.0);
        Ingredient egg = new Ingredient("egg", 0.3);

        snack.addIngredient(lettuce);
        snack.addIngredient(egg);
        snack.addIngredient(meat);

        LightSaleDiscountCalculator lightSaleDiscountCalculator =
                new LightSaleDiscountCalculator(snack);

        // When
        boolean eligible = lightSaleDiscountCalculator.isEligible();
        double discount = lightSaleDiscountCalculator.calculate();

        // Then
        assertTrue(eligible);
        assertEquals(0.25, discount, 0d);
    }

    @Test
    public void checkIneligibleSnack() {
        // Given
        Snack snack = new Snack();
        Ingredient meat = new Ingredient("meat", 2.0);
        Ingredient egg = new Ingredient("egg", 0.3);

        snack.addIngredient(meat);
        snack.addIngredient(egg);

        LightSaleDiscountCalculator lightSaleDiscountCalculator = new LightSaleDiscountCalculator(snack);

        // When
        boolean eligible = lightSaleDiscountCalculator.isEligible();
        double discount = lightSaleDiscountCalculator.calculate();

        // Then
        assertFalse(eligible);
        assertEquals(0d, discount, 0d);
    }
}
