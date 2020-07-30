package com.dextra.snackbar.controller.dto;

import com.dextra.snackbar.model.SnackItem;

import java.util.List;
import java.util.stream.Collectors;

public class SnackItemDTO extends ApiResponse {

    private Long id;

    private IngredientDTO ingredientDTO;

    private Integer quantity;

    public SnackItemDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IngredientDTO getIngredientDTO() {
        return ingredientDTO;
    }

    public void setIngredientDTO(IngredientDTO ingredientDTO) {
        this.ingredientDTO = ingredientDTO;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public static SnackItemDTO convert(SnackItem snackItem) {
        SnackItemDTO snackItemDTO = new SnackItemDTO();
        IngredientDTO ingredientDTO = IngredientDTO.convert(snackItem.getIngredient());

        snackItemDTO.setIngredientDTO(ingredientDTO);
        snackItemDTO.setQuantity(snackItem.getQuantity());

        return snackItemDTO;
    }

    public static List<SnackItemDTO> convertList(List<SnackItem> snackItems) {
        return snackItems.stream()
                .map(snackItem -> SnackItemDTO.convert(snackItem))
                .collect(Collectors.toList());
    }

    public SnackItem toModel() {
        SnackItem snackItem = new SnackItem();

        snackItem.setId(id);
        snackItem.setIngredient(ingredientDTO.toModel());
        snackItem.setQuantity(quantity);

        return snackItem;
    }
}
