package com.dextra.snackbar.controller;

import com.dextra.snackbar.controller.dto.IngredientDTO;
import com.dextra.snackbar.model.Ingredient;
import com.dextra.snackbar.service.IngredientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ingredients")
public class IngredientController {

    private final IngredientService ingredientService;
    private final Logger logger = LoggerFactory.getLogger(IngredientController.class);

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public ResponseEntity<List<IngredientDTO>> listIngredients() {
        List<Ingredient> ingredients = this.ingredientService.listAll();
        List<IngredientDTO> ingredientDTOS = IngredientDTO.convertList(ingredients);

        return ResponseEntity.ok(ingredientDTOS);
    }
}
