package ro.msg.learning.shop.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.domain.primarykeys.StockKey;
import ro.msg.learning.shop.dto.CreateStockDto;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class StockService {
    private static final String NO_STOCK_IS_FOUND = "No stock was found";
    private final StockRepository stockRepository;
    private final ProductService productService;
    private final LocationService locationService;

    public Stock getStockById(UUID productId, UUID locationId) {
        Stock stock = stockRepository.findById(StockKey.builder().location(locationId).product(productId).build())
                .orElse(null);
        if (stock != null) {
            return stock;
        }
        throw new EntityNotFoundException(NO_STOCK_IS_FOUND);
    }

    public List<Stock> getAllStocks() {
        List<Stock> allStocks = stockRepository.findAll();
        if (!allStocks.isEmpty()) {
            return allStocks;
        }
        throw new EntityNotFoundException(NO_STOCK_IS_FOUND);
    }

    public Stock createStock(CreateStockDto stockToCreate) {
        Location location = locationService.getLocationById(stockToCreate.getLocation());
        Product product = productService.getProductById(stockToCreate.getProduct());
        Stock stock = Stock.builder().product(product).location(location).quantity(stockToCreate.getQuantity()).build();
        return stockRepository.save(stock);
    }

    public Stock putStock(UUID productId, UUID locationId, CreateStockDto updates) {
        Stock stockToUpdate = stockRepository.findById(new StockKey(productId, locationId)).orElse(null);
        if (stockToUpdate != null) {
            stockToUpdate.setQuantity(updates.getQuantity());
            return stockRepository.save(stockToUpdate);
        } else throw new EntityNotFoundException(NO_STOCK_IS_FOUND);
    }

    public void deleteStockById(UUID productId, UUID locationId) {
        Stock stockToUpdate = stockRepository.findById(new StockKey(productId, locationId)).orElse(null);
        if (stockToUpdate != null) {
            stockRepository.deleteById(new StockKey(productId, locationId));
        } else throw new EntityNotFoundException(NO_STOCK_IS_FOUND);
    }
}
