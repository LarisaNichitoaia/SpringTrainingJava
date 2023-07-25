package ro.msg.learning.shop.strategies;

import ro.msg.learning.shop.dto.StockDto;

import java.util.List;

public interface Strategy {
    void findLocationAndUpdateStock(List<StockDto> productsList);
}
