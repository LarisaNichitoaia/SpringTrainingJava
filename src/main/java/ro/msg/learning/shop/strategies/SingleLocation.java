package ro.msg.learning.shop.strategies;
import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.controller.customexceptions.NoSuchObjectException;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class SingleLocation implements Strategy {

    private final ProductService productService;
    private final LocationService locationService;
    private final StockService stockService;

    public List<OrderDetail> findLocationAndUpdateStock(List<StockDto> productsList) {
        List<Location> locations = locationService.getAllLocations();

        for (Location currentLocation : locations) {
            List<OrderDetail> locationAndProducts = new ArrayList<>();
            int numberOfProductsFound = 0;
            for (StockDto currentProduct : productsList) {
                if (currentProduct.getQuantity() < 0) {
                    throw new IllegalArgumentException("Quantity must be a positive number");
                }
                Stock stockAvailable = stockService.getStockById(UUID.fromString(currentProduct.getProductId())
                        , currentLocation.getId());
                if (stockAvailable != null && stockAvailable.getQuantity() >= currentProduct.getQuantity()) {
                    numberOfProductsFound++;
                    Product productWanted = productService.getProductById(UUID.fromString(currentProduct.getProductId()));
                    OrderDetail orderDetail = OrderDetail.builder().product(productWanted).shippedFrom(currentLocation)
                            .quantity(currentProduct.getQuantity()).build();
                    locationAndProducts.add(orderDetail);
                }
            }
            if (numberOfProductsFound == productsList.size()) {
                updateStocks(currentLocation, productsList);
                return locationAndProducts;
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
