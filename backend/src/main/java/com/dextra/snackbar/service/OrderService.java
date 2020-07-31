package com.dextra.snackbar.service;

import com.dextra.snackbar.model.Order;
import com.dextra.snackbar.model.Snack;
import com.dextra.snackbar.model.discount.DiscountCalculatorManager;
import com.dextra.snackbar.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    private DiscountCalculatorManager discountCalculatorManager;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    private Order buildOrderFromSnack(Snack snack) {
        Order order = Order.createInstance(snack);
        Double discount = discountCalculatorManager.calculateDiscount(snack);
        order.setDiscount(discount);

        return order;
    }

    public Order save(Snack snack) {
        Order order = buildOrderFromSnack(snack);

        return this.orderRepository.save(order);
    }

    public List<Order> listAll() {
        return this.orderRepository.findAll();
    }
}
