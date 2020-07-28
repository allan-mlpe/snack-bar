package com.dextra.snackbar.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class LotsOfMeatDiscountCalculatorTest {
    @Test
    public void checkEligibleSnack() {
        // Given
        Snack snack = new Snack();
        Ingredient meat = new Ingredient("meat", 2.0);
        Ingredient egg = new Ingredient("egg", 0.3);

        snack.addIngredient(meat);
        snack.addIngredient(meat);
        snack.addIngredient(meat);
        snack.addIngredient(egg);

        LotsOfMeatSaleDiscountCalculator lotsOfMeatSaleDiscountCalculator =
                new LotsOfMeatSaleDiscountCalculator(snack);

        // When
        boolean eligible = lotsOfMeatSaleDiscountCalculator.isEligible();
        double discount = lotsOfMeatSaleDiscountCalculator.calculate();

        // Then
        assertTrue(eligible);
        assertEquals(2d, discount, 0d);
    }

    @Test
    public void checkInsufficientEligibleSnack() {
        // Given
        Snack snack = new Snack();
        Ingredient meat = new Ingredient("meat", 2.0);
        Ingredient egg = new Ingredient("egg", 0.3);

        snack.addIngredient(meat);
        snack.addIngredient(egg);

        LotsOfMeatSaleDiscountCalculator lotsOfMeatSaleDiscountCalculator =
                new LotsOfMeatSaleDiscountCalculator(snack);

        // When
        boolean eligible = lotsOfMeatSaleDiscountCalculator.isEligible();
        double discount = lotsOfMeatSaleDiscountCalculator.calculate();

        // Then
        assertTrue(eligible);
        assertEquals(0d, discount, 0d);
    }

    @Test
    public void checkIneligibleSnack() {
        // Given
        Snack snack = new Snack();
        Ingredient cheese = new Ingredient("cheese", 0.5);
        Ingredient egg = new Ingredient("egg", 0.3);

        snack.addIngredient(egg);

        LotsOfMeatSaleDiscountCalculator lotsOfMeatSaleDiscountCalculator =
                new LotsOfMeatSaleDiscountCalculator(snack);

        // When
        boolean eligible = lotsOfMeatSaleDiscountCalculator.isEligible();
        double discount = lotsOfMeatSaleDiscountCalculator.calculate();

        // Then
        assertFalse(eligible);
        assertEquals(0d, discount, 0d);
    }
}
