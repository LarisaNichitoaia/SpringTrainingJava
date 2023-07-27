package ro.msg.learning.shop.strategies;

import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.dto.StockDto;

import java.util.List;

public interface Strategy {
    public List<OrderDetail> findLocationAndUpdateStock(List<StockDto> productsList);
}
