package ro.msg.learning.shop.dto;

import lombok.Builder;
import lombok.Data;
import ro.msg.learning.shop.domain.Product;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDto {
    private LocalDateTime createdAt;
    private String addressCountry;
    private String addressCity;
    private String addressCounty;
    private String addressStreet;
    private List<Product> productList;
}
