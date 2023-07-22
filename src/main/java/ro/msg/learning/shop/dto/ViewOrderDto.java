package ro.msg.learning.shop.dto;

import lombok.Builder;
import lombok.Data;
import ro.msg.learning.shop.domain.Customer;

import java.time.LocalDateTime;

@Data
@Builder
public class ViewOrderDto {
    private String id;
    private Customer customer;
    private LocalDateTime createdAt;
    private String addressCountry;
    private String addressCity;
    private String addressCounty;
    private String addressStreet;
}
