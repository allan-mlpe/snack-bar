package com.dextra.snackbar.service;

import com.dextra.snackbar.model.Ingredient;
import com.dextra.snackbar.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> listAll() {
        return ingredientRepository.findAll();
    }
}
