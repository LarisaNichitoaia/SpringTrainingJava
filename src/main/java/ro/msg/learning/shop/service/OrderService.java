package ro.msg.learning.shop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.dto.ProvidedOrderDto;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.strategies.Strategy;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final OrderDetailService orderDetailService;
    private final OrderMapper orderMapper;
    private final Strategy locationStrategy;

    @Transactional
    public Order createOrder(ProvidedOrderDto orderToCreate) {
        List<OrderDetail> strategyLocationAndProduct = locationStrategy.findLocationAndUpdateStock(orderToCreate.getProducts());
        Customer customer = customerService.getCustomerById(UUID.fromString(orderToCreate.getCustomerId()));
        Order order = orderMapper.toEntity(customer, orderToCreate);

        for (OrderDetail orderDetail : strategyLocationAndProduct) {
            orderDetail.setOrder(order);
            orderDetailService.save(orderDetail);
        }
        return orderRepository.save(order);
    }
}

