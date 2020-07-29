package com.dextra.snackbar.domain.model.discount;

import com.dextra.snackbar.domain.model.Ingredient;
import com.dextra.snackbar.domain.model.Snack;
import com.dextra.snackbar.domain.model.discount.LotsOfMeatSaleDiscountCalculator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class LotsOfMeatDiscountCalculatorTest {

    private LotsOfMeatSaleDiscountCalculator lotsOfMeatSaleDiscountCalculator;

    @Before
    public void init() {
        lotsOfMeatSaleDiscountCalculator =
                new LotsOfMeatSaleDiscountCalculator();
    }

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

        // When
        boolean eligible = lotsOfMeatSaleDiscountCalculator.isEligible(snack);
        double discount = lotsOfMeatSaleDiscountCalculator.calculate(snack);

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

        // When
        boolean eligible = lotsOfMeatSaleDiscountCalculator.isEligible(snack);
        double discount = lotsOfMeatSaleDiscountCalculator.calculate(snack);

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

        // When
        boolean eligible = lotsOfMeatSaleDiscountCalculator.isEligible(snack);
        double discount = lotsOfMeatSaleDiscountCalculator.calculate(snack);

        // Then
        assertFalse(eligible);
        assertEquals(0d, discount, 0d);
    }
}
