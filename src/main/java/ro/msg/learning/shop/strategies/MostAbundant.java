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
public class MostAbundant implements Strategy {
    private final ProductService productService;
    private final LocationService locationService;
    private final StockService stockService;

    public List<OrderDetail> findLocationAndUpdateStock(List<StockDto> productsList) {
        List<OrderDetail> locationsAndProducts = new ArrayList<>();
        for (StockDto product : productsList) {
            if (product.getQuantity() < 0) {
                throw new IllegalArgumentException("Quantity must be a positive number");
            }
            UUID locationWithBiggestStock =
                    stockService.findLocationsWithLargestStock(UUID.fromString(product.getProductId()));
            if (locationWithBiggestStock == null)
                throw new NoSuchObjectException("Product not available at any location.");
            Stock stockAvailable =
                    stockService.getStockById(UUID.fromString(product.getProductId()), locationWithBiggestStock);
            if (stockAvailable != null && stockAvailable.getQuantity() > product.getQuantity()) {
                Product productWanted = productService.getProductById(UUID.fromString(product.getProductId()));
                Location location = locationService.getLocationById(locationWithBiggestStock);
                OrderDetail orderDetail = OrderDetail.builder().product(productWanted).shippedFrom(location)
                        .quantity(product.getQuantity()).build();
                locationsAndProducts.add(orderDetail);

                int newQuantity = stockAvailable.getQuantity() - product.getQuantity();
                stockAvailable.setQuantity(newQuantity);
                stockService.putStock(UUID.fromString(product.getProductId()), locationWithBiggestStock, stockAvailable);
            }
        }
        return locationsAndProducts;
    }

}
