package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.ViewStockDto;

@Component
public class StockMapper {
    public ViewStockDto toViewDto(Stock stock) {
        return ViewStockDto.builder().product(stock.getProduct()).location(stock.getLocation())
                .quantity(stock.getQuantity()).build();
    }
}
