package com.example.Carrie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/orders")
public class OrdersController {
    private final OrderRepository orderRepository;

    @Autowired
    public OrdersController(OrderRepository ordersRepository) {
        this.orderRepository = ordersRepository;
    }

    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>((List<Order>) orderRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Order> getOrder(@RequestParam UUID id) {
        Optional<Order> order = orderRepository.findById(id);
        return new ResponseEntity<>(order.get(), HttpStatus.OK);
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        Order newOrder = orderRepository.save(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PutMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Order> changeOrderLocationByCustomerName(@RequestBody Order order, @RequestParam String orderLocation)
    {
        Order currentOrder = orderRepository.findById(order.getId()).orElseThrow();
        currentOrder.setOrderLocation(orderLocation);
        orderRepository.save(currentOrder);
        return new ResponseEntity<>(currentOrder, HttpStatus.OK);
    }
}
