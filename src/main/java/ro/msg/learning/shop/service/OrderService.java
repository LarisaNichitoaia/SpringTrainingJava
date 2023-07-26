package ro.msg.learning.shop.service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.strategies.Strategy;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final OrderDetailService orderDetailService;
    private final OrderMapper orderMapper;
    private final Strategy locationStrategy;

    @Transactional
    public Order createOrder(Order orderToCreate, List<StockDto> productList) {
        List<OrderDetail> strategyLocationAndProduct = locationStrategy.findLocationAndUpdateStock(productList);
        orderRepository.save(orderToCreate);
        for (OrderDetail orderDetail : strategyLocationAndProduct) {
            orderDetail.setOrder(orderToCreate);
            orderDetailService.save(orderDetail);
        }
        return orderToCreate;
    }
}

