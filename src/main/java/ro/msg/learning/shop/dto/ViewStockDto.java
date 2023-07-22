package ro.msg.learning.shop.dto;

import lombok.Builder;
import lombok.Data;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;

@Data
@Builder
public class ViewStockDto {
    private Product product;
    private Location location;
    private Integer quantity;
}
