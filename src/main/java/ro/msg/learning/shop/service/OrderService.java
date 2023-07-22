package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.repository.OrderRepository;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

}
