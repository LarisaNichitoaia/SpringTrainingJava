package ro.msg.learning.shop.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.customexceptions.NoSuchObjectException;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.service.StockService;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MostAbundant implements Strategy {

    private final OrderRepository orderRepository;
    private final StockService stockService;

    public void findLocationAndUpdateStock(List<StockDto> productsList) {
        for (StockDto product : productsList) {
            if (product.getQuantity() < 0) {
                throw new IllegalArgumentException("Quantity must be a positive number");
            }
            UUID locationWithBiggestStock =
                    orderRepository.findLocationsWithLargestStockForEachProduct(UUID.fromString(product.getProductId()));
            if (locationWithBiggestStock == null)
                throw new NoSuchObjectException("Product not available at any location.");
            Stock stockAvailable =
                    stockService.getStockById(UUID.fromString(product.getProductId()), locationWithBiggestStock);
            if (stockAvailable.getQuantity() > product.getQuantity()) {
                int newQuantity = stockAvailable.getQuantity() - product.getQuantity();
                stockAvailable.setQuantity(newQuantity);
                stockService.putStock(UUID.fromString(product.getProductId()), locationWithBiggestStock, stockAvailable);
            }
        }
    }

}
