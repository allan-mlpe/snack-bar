package com.dextra.snackbar.controller;

import com.dextra.snackbar.controller.dto.ApiResponse;
import com.dextra.snackbar.controller.dto.IngredientDTO;
import com.dextra.snackbar.controller.exception.IngredientNotFoundException;
import com.dextra.snackbar.model.Ingredient;
import com.dextra.snackbar.service.IngredientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PostMapping
    public ResponseEntity<ApiResponse> save(
            @RequestBody IngredientDTO ingredientDTO
    ) {
        try {
            IngredientDTO insertedIngredient = IngredientDTO.convert(
                    ingredientService.save(ingredientDTO.toModel())
            );
            return ResponseEntity.ok(insertedIngredient);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(
            @PathVariable UUID id,
            @RequestBody IngredientDTO ingredientDTO
    ) {
        try {
            Ingredient ingredient = findIngredient(id);

            Ingredient modifiedIngredient = ingredientDTO.toModel();
            modifiedIngredient.setId(ingredient.getId());

            IngredientDTO updated = IngredientDTO.convert(
                this.ingredientService.save(modifiedIngredient)
            );

            return ResponseEntity.ok(updated);

        } catch (IngredientNotFoundException e) {
            logger.error(e.getMessage());
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable UUID id) {
        try {
            Ingredient ingredient = findIngredient(id);

            return ResponseEntity.ok(IngredientDTO.convert(ingredient));
        } catch (IngredientNotFoundException e) {
            logger.error(e.getMessage());
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable UUID id) {
        try {
            findIngredient(id);
            ingredientService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IngredientNotFoundException e) {
            logger.error(e.getMessage());
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    private Ingredient findIngredient(UUID id) throws IngredientNotFoundException {
        Ingredient ingredient = ingredientService.findById(id);

        if (ingredient == null) {
            throw new IngredientNotFoundException(
                String.format("Ingredient '%s' not found", id.toString())
            );
        }
        return ingredient;
    }
}
