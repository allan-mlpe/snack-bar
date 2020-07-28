package com.dextra.snackbar.domain.model;

public class LotsOfCheeseDiscountCalculator extends AbstractDiscountCalculator {

    public LotsOfCheeseDiscountCalculator(Snack snack) {
        super(snack);
    }

    @Override
    public boolean isEligible() {
        return ingredients.containsKey("cheese");
    }

    @Override
    public double calculate() {
        double discount = 0d;
        if (this.isEligible()) {
            Ingredient cheese = ingredients.get("cheese");
            int quantity = cheese.getQuantity();

            discount = (quantity / 3) * cheese.getPrice();
        }

        return discount;
    }
}
