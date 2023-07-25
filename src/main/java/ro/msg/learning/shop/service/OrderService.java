package ro.msg.learning.shop.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.dto.ProvidedOrderDto;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.strategies.Strategy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final Strategy locationStrategy;

    public Order createOrder(ProvidedOrderDto orderToCreate) {
        String dateString = orderToCreate.getCreatedAt();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime parsedDate = LocalDateTime.parse(dateString, inputFormatter);
        locationStrategy.findLocationAndUpdateStock(orderToCreate.getProducts());
        Customer customer = customerService.getCustomerById(UUID.fromString(orderToCreate.getCustomerId()));
        Order order = Order.builder().customer(customer).createdAt(parsedDate)
                .addressCountry(orderToCreate.getAddressCountry()).addressCity(orderToCreate.getAddressCity())
                .addressCounty(orderToCreate.getAddressCounty()).addressStreet(orderToCreate.getAddressStreet()).build();
        return orderRepository.save(order);
    }
}

