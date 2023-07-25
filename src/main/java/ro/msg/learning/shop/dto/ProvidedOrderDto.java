package ro.msg.learning.shop.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProvidedOrderDto {
    private String customerId;
    private String createdAt;
    private String addressCountry;
    private String addressCity;
    private String addressCounty;
    private String addressStreet;
    private List<StockDto> products;
}
