package com.dextra.snackbar.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnackTest {

    private Snack snack;
    private Ingredient meat;
    private Ingredient bacon;
    private List<SnackItem> snackItems;

    @BeforeEach
    public void init() {
        snack = new Snack();
        meat = new Ingredient("meat", 2.0);
        bacon = new Ingredient("bacon", 0.5);
        snackItems = Arrays.asList(
                new SnackItem(meat, 1),
                new SnackItem(bacon, 2)
        );
    }

    @Test
    public void testGetFullPrice() {
        // Given
        snack.setSnackItems(snackItems);

        // When
        double fullPrice = snack.sumFullPrice();

        assertEquals(3.0, fullPrice, 0);
    }

    @Test
    public void testGetFullPriceFromEmptyIngredientList() {
        // When
        double fullPrice = snack.sumFullPrice();

        // Then
        assertEquals(0d, snack.sumFullPrice(), 0);
    }
}
