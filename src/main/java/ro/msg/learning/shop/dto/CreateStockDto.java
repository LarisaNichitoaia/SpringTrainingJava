package ro.msg.learning.shop.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateStockDto {
    private UUID product;
    private UUID location;
    private Integer quantity;
}
