package com.dextra.snackbar.service;

import com.dextra.snackbar.model.Ingredient;
import com.dextra.snackbar.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> listAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient findById(UUID id) {
        return this.ingredientRepository.findById(id).orElse(null);
    }

    public void delete(UUID id) {
        this.ingredientRepository.deleteById(id);
    }
}
