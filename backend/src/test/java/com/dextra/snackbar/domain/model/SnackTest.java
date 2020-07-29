package com.dextra.snackbar.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SnackTest {

    private Snack snack;
    private Ingredient meat;
    private Ingredient bacon;

    @BeforeEach
    public void init() {
        snack = new Snack();
        meat = new Ingredient("meat", 2.0);
        bacon = new Ingredient("bacon", 0.5);
    }

    @Test
    public void testAddIngredient() {
        // When
        snack.addIngredient(meat);

        // Then
        assertEquals(snack.getIngredients().size(), 1);
    }

    @Test
    public void testAddAnIngredientTwice() {
        // When
        snack.addIngredient(meat);
        snack.addIngredient(meat);

        Map<String, Ingredient> ingredients = snack.getIngredients();

        // Then
        assertEquals(1, ingredients.size());
        assertEquals(2, ingredients.get(meat.getName()).getQuantity());
    }

    @Test
    public void testAddTwoIngredients() {
        // When
        snack.addIngredient(meat);
        snack.addIngredient(bacon);

        // Then
        assertEquals(2, snack.getIngredients().size());
    }

    @Test
    public void testRemoveIngredient() {
        // Given
        snack.addIngredient(meat);
        snack.addIngredient(bacon);

        // When
        snack.removeIngredient(meat);

        Map<String, Ingredient> ingredients = snack.getIngredients();

        // Then
        assertEquals(1, ingredients.size());
        assertNull(ingredients.get(meat.getName()));
    }

    @Test
    public void testRemoveDuplicatedIngredient() {
        // Given
        snack.addIngredient(meat);
        snack.addIngredient(meat);

        // When
        snack.removeIngredient(meat);

        Map<String, Ingredient> ingredients = snack.getIngredients();

        // Then
        assertEquals(1, ingredients.size());
        assertEquals(1, ingredients.get(meat.getName()).getQuantity());
    }

    @Test
    public void testRemoveIngredientFromEmptyIngredientList() {
        // When
        snack.removeIngredient(meat);

        // Then
        assertEquals(0, snack.getIngredients().size());
    }

    @Test
    public void testGetFullPrice() {
        // Given
        snack.addIngredient(meat);
        snack.addIngredient(bacon);
        snack.addIngredient(bacon);

        // When
        double fullPrice = snack.getFullPrice();

        assertEquals(3.0, fullPrice, 0);
    }

    @Test
    public void testGetFullPriceFromEmptyIngredientList() {
        // When
        double fullPrice = snack.getFullPrice();

        // Then
        assertEquals(0d, snack.getFullPrice(), 0);
    }
}
