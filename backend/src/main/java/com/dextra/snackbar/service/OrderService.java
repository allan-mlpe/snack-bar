package com.dextra.snackbar.service;

import com.dextra.snackbar.model.Order;
import com.dextra.snackbar.model.Snack;
import com.dextra.snackbar.model.discount.DiscountCalculatorManager;
import com.dextra.snackbar.repository.IngredientRepository;
import com.dextra.snackbar.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private IngredientRepository ingredientRepository;

    private DiscountCalculatorManager discountCalculatorManager;

    public OrderService(OrderRepository orderRepository, IngredientRepository ingredientRepository) {
        this.orderRepository = orderRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public Order save(Snack snack) {
        Order order = new Order();
        order.setSnack(snack);

        Double price = 0d;
        price += snack.sumFullPrice();

        Double discount = discountCalculatorManager.getDiscount(snack);

        order.setPrice(price);
        order.setDiscount(discount);

        return this.orderRepository.save(order);
    }

    public List<Order> listAll() {
        return this.orderRepository.findAll();
    }
}
