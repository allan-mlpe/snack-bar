package com.dextra.snackbar.domain.model.discount;

import com.dextra.snackbar.domain.model.Ingredient;
import com.dextra.snackbar.domain.model.Snack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LightSaleDiscountCalculatorTest {

    private LightSaleDiscountCalculator lightSaleDiscountCalculator;

    @BeforeEach
    public void init() {
         lightSaleDiscountCalculator =
            new LightSaleDiscountCalculator();
    }

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

        // When
        boolean eligible = lightSaleDiscountCalculator.isEligible(snack);
        double discount = lightSaleDiscountCalculator.calculate(snack);

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

        // When
        boolean eligible = lightSaleDiscountCalculator.isEligible(snack);
        double discount = lightSaleDiscountCalculator.calculate(snack);

        // Then
        assertFalse(eligible);
        assertEquals(0d, discount, 0d);
    }
}
