package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.controller.customexceptions.NoSuchObjectException;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.domain.primarykeys.StockKey;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class StockService {
    private static final String NO_STOCK_IS_FOUND = "No stock was found";
    private final StockRepository stockRepository;

    public Stock getStockById(UUID productId, UUID locationId) {
        return stockRepository.findById(StockKey.builder().location(locationId).product(productId).build())
                .orElseThrow(() -> new NoSuchObjectException(NO_STOCK_IS_FOUND));
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public Stock createStock(Stock stockToCreate) {
        return stockRepository.save(stockToCreate);
    }

    public Stock putStock(UUID productId, UUID locationId, Stock updates) {
        Optional<Stock> stockToUpdate = stockRepository.findById(new StockKey(productId, locationId));
        if (stockToUpdate.isPresent()) {
            return stockRepository.save(updates);
        }
        throw new NoSuchObjectException(NO_STOCK_IS_FOUND);
    }

    public void deleteStockById(UUID productId, UUID locationId) {
        Optional<Stock> stockToUpdate = stockRepository.findById(new StockKey(productId, locationId));
        if (stockToUpdate.isPresent()) {
            stockRepository.deleteById(new StockKey(productId, locationId));
        }
        throw new NoSuchObjectException(NO_STOCK_IS_FOUND);
    }

    public UUID findLocationsWithLargestStock(UUID productId) {
        return stockRepository.findLocationsWithLargestStockForEachProduct(productId);
    }
}
