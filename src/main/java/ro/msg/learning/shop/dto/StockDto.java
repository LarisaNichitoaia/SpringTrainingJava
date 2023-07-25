package ro.msg.learning.shop.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockDto {
    private String productId;
    private String locationId;
    private Integer quantity;
}
