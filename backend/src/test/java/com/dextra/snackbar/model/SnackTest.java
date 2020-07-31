package com.dextra.snackbar.model;

import com.dextra.snackbar.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnackTest {

    private Snack snack;
    private Ingredient meat;
    private Ingredient bacon;
    private Ingredient cheese;
    private Ingredient lettuce;
    private Ingredient egg;

    private List<SnackItem> snackItems;

    @BeforeEach
    public void init() {
        snack = new Snack();
        meat = new Ingredient(Constants.MEAT_INGREDIENT_NAME, 3.0);
        bacon = new Ingredient(Constants.BACON_INGREDIENT_NAME, 2.0);
        cheese = new Ingredient(Constants.BACON_INGREDIENT_NAME, 1.5);
        lettuce = new Ingredient(Constants.LETTUCE_INGREDIENT_NAME, 0.4);
        egg = new Ingredient(Constants.EGG_INGREDIENT_NAME, 0.8);

        snackItems = Arrays.asList(
                new SnackItem(meat, 1),
                new SnackItem(bacon, 2)
        );
    }

    @Test
    public void testGetFullPrice() {
        // Given
        snack.setSnackItems(snackItems);
        Double expectedPrice = 2 * bacon.getPrice() + 1 * meat.getPrice();

        // When
        double fullPrice = snack.sumFullPrice();

        assertEquals(expectedPrice, fullPrice, 0);
    }

    @Test
    public void testGetFullPriceFromEmptyIngredientList() {
        // When
        double fullPrice = snack.sumFullPrice();

        // Then
        assertEquals(0d, snack.sumFullPrice(), 0);
    }

    @Test
    public void testSumPriceAfterUpdatingIngredientPrices() {
        // Given
        snack.setSnackItems(snackItems);

        final Double PRICE_UPDATE_RATE = 0.5d;

        // When
        double fullPrice = snack.sumFullPrice();

        // Increase price of the ingredients
        snack.getSnackItems().forEach(item -> {
            Double price = item.getIngredient().getPrice();
            item.getIngredient().setPrice(price * (1 + PRICE_UPDATE_RATE));
        });

        double updatedPrice = snack.sumFullPrice();

        // Then
        assertEquals(7.0, fullPrice, 0);
        assertEquals(10.5, updatedPrice);
    }

    @Test
    public void testCheeseBaconPrice() {
        // Given
        List<SnackItem> cheeseBurgerItems = Arrays.asList(
                new SnackItem(cheese, 1),
                new SnackItem(meat, 1),
                new SnackItem(bacon, 1)
        );

        Double cheeseBaconItemPrice = 0d;
        for (SnackItem i : cheeseBurgerItems) {
            cheeseBaconItemPrice += i.getIngredient().getPrice();
        }

        snack.setSnackItems(cheeseBurgerItems);

        // When
        Double snackPrice = snack.sumFullPrice();

        // Then
        assertEquals(cheeseBaconItemPrice, snackPrice);
    }

    @Test
    public void testCheeseBurgerPrice() {
        // Given
        List<SnackItem> cheeseBurgerItems = Arrays.asList(
                new SnackItem(cheese, 1),
                new SnackItem(meat, 1)
        );

        Double cheeseBurgerItemPrice = 0d;
        for (SnackItem i : cheeseBurgerItems) {
            cheeseBurgerItemPrice += i.getIngredient().getPrice();
        }

        snack.setSnackItems(cheeseBurgerItems);

        // When
        Double snackPrice = snack.sumFullPrice();

        // Then
        assertEquals(cheeseBurgerItemPrice, snackPrice);
    }

    @Test
    public void testCheeseEggPrice() {
        // Given
        List<SnackItem> cheeseBurgerItems = Arrays.asList(
                new SnackItem(cheese, 1),
                new SnackItem(meat, 1),
                new SnackItem(egg, 1)
        );

        Double cheeseEggItemPrice = 0d;
        for (SnackItem i : cheeseBurgerItems) {
            cheeseEggItemPrice += i.getIngredient().getPrice();
        }

        snack.setSnackItems(cheeseBurgerItems);

        // When
        Double snackPrice = snack.sumFullPrice();

        // Then
        assertEquals(cheeseEggItemPrice, snackPrice);
    }

    @Test
    public void testCheeseEggBaconPrice() {
        // Given
        List<SnackItem> cheeseBurgerItems = Arrays.asList(
                new SnackItem(cheese, 1),
                new SnackItem(meat, 1),
                new SnackItem(egg, 1),
                new SnackItem(bacon, 1)
        );

        Double cheeseEggBaconItemPrice = 0d;
        for (SnackItem i : cheeseBurgerItems) {
            cheeseEggBaconItemPrice += i.getIngredient().getPrice();
        }

        snack.setSnackItems(cheeseBurgerItems);

        // When
        Double snackPrice = snack.sumFullPrice();

        // Then
        assertEquals(cheeseEggBaconItemPrice, snackPrice);
    }
}
