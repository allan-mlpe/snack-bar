package com.dextra.snackbar.domain.model.discount;

import com.dextra.snackbar.domain.model.Ingredient;
import com.dextra.snackbar.domain.model.Snack;
import com.dextra.snackbar.domain.model.discount.LotsOfCheeseDiscountCalculator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class LotsOfCheeseDiscountCalculatorTest {

    private LotsOfCheeseDiscountCalculator lotsOfCheeseDiscountCalculator;

    @Before
    public void init() {
        lotsOfCheeseDiscountCalculator = new LotsOfCheeseDiscountCalculator();
    }

    @Test
    public void checkEligibleSnack() {
        // Given
        Snack snack = new Snack();
        Ingredient meat = new Ingredient("meat", 2.0);
        Ingredient egg = new Ingredient("egg", 0.3);
        Ingredient cheese = new Ingredient("cheese", 0.5);

        snack.addIngredient(cheese);
        snack.addIngredient(cheese);
        snack.addIngredient(cheese);
        snack.addIngredient(cheese);
        snack.addIngredient(egg);
        snack.addIngredient(meat);

        // When
        boolean eligible = lotsOfCheeseDiscountCalculator.isEligible(snack);
        double discount = lotsOfCheeseDiscountCalculator.calculate(snack);

        // Then
        assertTrue(eligible);
        assertEquals(0.5, discount, 0d);
    }

    @Test
    public void checkInsufficientEligibleSnack() {
        // Given
        Snack snack = new Snack();
        Ingredient meat = new Ingredient("meat", 2.0);
        Ingredient cheese = new Ingredient("cheese", 0.5);

        snack.addIngredient(cheese);
        snack.addIngredient(cheese);
        snack.addIngredient(meat);

        // When
        boolean eligible = lotsOfCheeseDiscountCalculator.isEligible(snack);
        double discount = lotsOfCheeseDiscountCalculator.calculate(snack);

        // Then
        assertTrue(eligible);
        assertEquals(0d, discount, 0d);
    }

    @Test
    public void checkIneligibleSnack() {
        // Given
        Snack snack = new Snack();
        Ingredient meat = new Ingredient("meat", 2.0);

        snack.addIngredient(meat);

        // When
        boolean eligible = lotsOfCheeseDiscountCalculator.isEligible(snack);
        double discount = lotsOfCheeseDiscountCalculator.calculate(snack);

        // Then
        assertFalse(eligible);
        assertEquals(0d, discount, 0d);
    }
}
