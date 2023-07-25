package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.StockDto;

@Component
public class StockMapper {
    public StockDto toDto(Stock stock) {
        return StockDto.builder().productId(stock.getProduct().getId().toString())
                .locationId(stock.getLocation().getId().toString())
                .quantity(stock.getQuantity()).build();
    }

    public Stock toEntity(Product product, Location location, StockDto stockDto) {
        return Stock.builder().product(product).location(location).quantity(stockDto.getQuantity()).build();
    }
}
