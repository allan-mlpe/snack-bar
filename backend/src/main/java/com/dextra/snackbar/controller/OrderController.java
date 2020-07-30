package com.dextra.snackbar.controller;

import com.dextra.snackbar.controller.dto.ApiResponse;
import com.dextra.snackbar.controller.dto.OrderDTO;
import com.dextra.snackbar.controller.dto.SnackDTO;
import com.dextra.snackbar.model.Order;
import com.dextra.snackbar.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    private OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(IngredientController.class);

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveOrder(@RequestBody SnackDTO snackDTO) {
        try {
            Order order = this.orderService.save(snackDTO.toModel());

            return ResponseEntity.ok(OrderDTO.convert(order));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders() {
        List<Order> orders = this.orderService.listAll();
        List<OrderDTO> orderDTOS = OrderDTO.convertList(orders);

        return ResponseEntity.ok(orderDTOS);
    }
}
