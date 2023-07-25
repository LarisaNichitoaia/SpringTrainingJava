package ro.msg.learning.shop.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.customexceptions.NoSuchObjectException;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.StockService;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SingleLocation implements Strategy {

    private final LocationService locationService;
    private final StockService stockService;

    public void findLocationAndUpdateStock(List<StockDto> productsList) {
        List<Location> locations = locationService.getAllLocations();

        for (Location currentLocation : locations) {
            int numberOfProductsFound = 0;
            for (StockDto product : productsList) {
                if (product.getQuantity() < 0) {
                    throw new IllegalArgumentException("Quantity must be a positive number");
                }
                Stock stockAvailable = stockService.getStockById(UUID.fromString(product.getProductId()), currentLocation.getId());
                if (stockAvailable != null && stockAvailable.getQuantity() > product.getQuantity()) {
                    numberOfProductsFound++;
                }
            }
            if (numberOfProductsFound == productsList.size()) {
                updateStocks(currentLocation, productsList);
                return;
            }
        }
        throw new NoSuchObjectException("Products not available at any location.");
    }

    private void updateStocks(Location location, List<StockDto> productsList) {
        for (StockDto product : productsList) {
            Stock stock = stockService.getStockById(UUID.fromString(product.getProductId()), location.getId());
            int newQuantity = stock.getQuantity() - product.getQuantity();
            stock.setQuantity(newQuantity);
            stockService.putStock(UUID.fromString(product.getProductId()), location.getId(), stock);
        }

    }
}
