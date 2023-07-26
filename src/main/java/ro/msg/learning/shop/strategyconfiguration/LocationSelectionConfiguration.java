package ro.msg.learning.shop.strategyconfiguration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;
import ro.msg.learning.shop.strategies.MostAbundant;
import ro.msg.learning.shop.strategies.SingleLocation;
import ro.msg.learning.shop.strategies.Strategy;

@Configuration
@RequiredArgsConstructor
public class LocationSelectionConfiguration {
    private final ProductService productService;
    private final LocationService locationService;
    private final StockService stockService;
    @Value("${location-selection.strategy}")
    private LocationStrategy strategy;

    @Bean
    public Strategy strategy() {
        if (strategy.equals(LocationStrategy.MOST_ABUNDANT)) {
            return new MostAbundant(productService, locationService, stockService);
        } else {
            return new SingleLocation(productService, locationService, stockService);
        }
    }

    public enum LocationStrategy {
        SINGLE_LOCATION,
        MOST_ABUNDANT
    }
}
