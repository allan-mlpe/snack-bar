package com.dextra.snackbar.model.discount;

import com.dextra.snackbar.model.Ingredient;
import com.dextra.snackbar.model.Snack;
import com.dextra.snackbar.model.SnackItem;
import com.dextra.snackbar.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LotsOfMeatDiscountCalculatorTest {

    private LotsOfMeatSaleDiscountCalculator lotsOfMeatSaleDiscountCalculator;

    @BeforeEach
    public void init() {
        lotsOfMeatSaleDiscountCalculator =
                new LotsOfMeatSaleDiscountCalculator();
    }

    @Test
    public void checkEligibleSnack() {
        // Given
        Snack snack = new Snack();
        Ingredient meat = new Ingredient(Constants.MEAT_INGREDIENT_NAME, 2.0);
        Ingredient egg = new Ingredient(Constants.EGG_INGREDIENT_NAME, 0.3);

        List<SnackItem> snackItems = Arrays.asList(
            new SnackItem(meat, 3),
            new SnackItem(egg, 1)
        );

        snack.setSnackItems(snackItems);

        // When
        double discount = lotsOfMeatSaleDiscountCalculator.calculate(snack);

        // Then
        assertEquals(2d, discount, 0d);
    }

    @Test
    public void checkInsufficientEligibleSnack() {
        // Given
        Snack snack = new Snack();
        Ingredient meat = new Ingredient(Constants.MEAT_INGREDIENT_NAME, 2.0);
        Ingredient egg = new Ingredient(Constants.EGG_INGREDIENT_NAME, 0.3);

        List<SnackItem> snackItems = Arrays.asList(
            new SnackItem(egg, 1),
            new SnackItem(meat, 1)
        );

        snack.setSnackItems(snackItems);

        // When
        double discount = lotsOfMeatSaleDiscountCalculator.calculate(snack);

        // Then
        assertEquals(0d, discount, 0d);
    }

    @Test
    public void checkIneligibleSnack() {
        // Given
        Snack snack = new Snack();
        Ingredient egg = new Ingredient(Constants.EGG_INGREDIENT_NAME, 0.3);
        List<SnackItem> snackItems = Arrays.asList(
            new SnackItem(egg, 1)
        );

        snack.setSnackItems(snackItems);

        // When
        double discount = lotsOfMeatSaleDiscountCalculator.calculate(snack);

        // Then
        assertEquals(0d, discount, 0d);
    }
}
