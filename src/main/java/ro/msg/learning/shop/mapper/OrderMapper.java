package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.dto.ViewOrderDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class OrderMapper {
    public ViewOrderDto toDto(Order order) {
        String dateString = "2023-10-20T12:03:23";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ROOT);
        LocalDateTime parsedDate = LocalDateTime.parse(dateString, formatter);
        return ViewOrderDto.builder().id(order.getId().toString())
                .customerName(order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName())
                .createdAt(parsedDate.toString()).addressCountry(order.getAddressCountry())
                .addressCity(order.getAddressCity()).addressCounty(order.getAddressCounty())
                .addressStreet(order.getAddressStreet()).build();
    }

}
