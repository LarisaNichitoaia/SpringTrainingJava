package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.service.OrderService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/Order")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

}
