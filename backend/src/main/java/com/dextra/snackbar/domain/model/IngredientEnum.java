package com.dextra.snackbar.domain.model;

public enum IngredientEnum {
    ALFACE("Alface"),
    BACON("bacon"),
    CARNE("Hambúrguer de Carne"),
    OVO("Ovo"),
    QUEIJO("Queijo");

    private String label;

    IngredientEnum(String label) {
        this.label = label;
    }
}
