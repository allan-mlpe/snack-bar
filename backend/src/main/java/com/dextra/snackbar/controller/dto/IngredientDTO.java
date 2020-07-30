package com.dextra.snackbar.controller.dto;

import com.dextra.snackbar.model.Ingredient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class IngredientDTO extends ApiResponse {

    private UUID id;

    private String name;

    private Double price;

    public IngredientDTO() {

    }

    public static IngredientDTO convert(Ingredient ingredient) {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.id = ingredient.getId();
        ingredientDTO.name = ingredient.getName();
        ingredientDTO.price = ingredient.getPrice();

        return ingredientDTO;
    }

    public static List<IngredientDTO> convertList(List<Ingredient> ingredients) {
        return ingredients
                .stream()
                .map(ingredient -> IngredientDTO.convert(ingredient))
                .collect(Collectors.toList());
    }

    public Ingredient toModel() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(this.id);
        ingredient.setName(this.name);
        ingredient.setPrice(this.price);

        return ingredient;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
