package com.dextra.snackbar.controller.dto;

import com.dextra.snackbar.model.Order;
import com.dextra.snackbar.model.Snack;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderDTO extends ApiResponse {

    private UUID id;

    private SnackDTO snack;

    private Double price;

    private Double discount;

    public OrderDTO() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public SnackDTO getSnack() {
        return snack;
    }

    public void setSnack(SnackDTO snack) {
        this.snack = snack;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public static OrderDTO convert(Order order) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setPrice(order.getPrice());
        orderDTO.setDiscount(order.getDiscount());

        SnackDTO snackDTO = SnackDTO.convert(order.getSnack());
        orderDTO.setSnack(snackDTO);

        return orderDTO;
    }

    public static List<OrderDTO> convertList(List<Order> orders) {
        return orders.stream()
                .map(order -> OrderDTO.convert(order))
                .collect(Collectors.toList());
    }

    public Order toModel() {
        Order order = new Order();

        order.setId(this.id);
        order.setPrice(this.price);
        order.setDiscount(this.discount);

        Snack snack = this.snack.toModel();
        order.setSnack(snack);

        return order;
    }
}
