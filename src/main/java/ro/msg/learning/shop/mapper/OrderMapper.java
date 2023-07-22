package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.dto.ViewOrderDto;

@Component
public class OrderMapper {
    public ViewOrderDto toDto(Order order) {
        return ViewOrderDto.builder().id(order.getCustomerId().toString()).customer(order.getCustomerId())
                .createdAt(order.getCreatedAt()).addressCountry(order.getAddressCountry())
                .addressCity(order.getAddressCity()).addressCounty(order.getAddressCounty())
                .addressStreet(order.getAddressStreet()).build();
    }

}
