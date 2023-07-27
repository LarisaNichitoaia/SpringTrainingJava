package ro.msg.learning.shop.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.dto.ProvidedOrderDto;
import ro.msg.learning.shop.dto.ViewOrderDto;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.service.CustomerService;
import ro.msg.learning.shop.service.OrderService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<ViewOrderDto> createOrder(@RequestBody ProvidedOrderDto providedOrder) {
        Customer customer = customerService.getCustomerById(UUID.fromString(providedOrder.getCustomerId()));
        Order order = orderMapper.toEntity(customer, providedOrder);
        ViewOrderDto viewOrderDto = orderMapper.toDto(orderService.createOrder(order, providedOrder.getProducts()));
        return new ResponseEntity<>(viewOrderDto, HttpStatus.CREATED);
    }
}
