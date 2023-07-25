package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.dto.ProvidedOrderDto;
import ro.msg.learning.shop.dto.ViewOrderDto;

import java.time.LocalDateTime;

@Component
public class OrderMapper {
    public ViewOrderDto toDto(Order order) {
        LocalDateTime parsedDate = LocalDateTime.now();
        return ViewOrderDto.builder().id(order.getId().toString())
                .customerName(order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName())
                .createdAt(parsedDate.toString()).addressCountry(order.getAddressCountry())
                .addressCity(order.getAddressCity()).addressCounty(order.getAddressCounty())
                .addressStreet(order.getAddressStreet()).build();
    }

    public Order toEntity(Customer customer, ProvidedOrderDto orderDto) {
        return Order.builder().customer(customer)
                .addressCountry(orderDto.getAddressCountry()).addressCity(orderDto.getAddressCity())
                .addressCounty(orderDto.getAddressCounty()).addressStreet(orderDto.getAddressStreet()).build();

    }
}
