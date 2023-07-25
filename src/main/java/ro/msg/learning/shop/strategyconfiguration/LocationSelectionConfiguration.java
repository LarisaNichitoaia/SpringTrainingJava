package ro.msg.learning.shop.strategyconfiguration;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ro.msg.learning.shop.strategies.MostAbundant;
import ro.msg.learning.shop.strategies.SingleLocation;
import ro.msg.learning.shop.strategies.Strategy;

@Configuration
@RequiredArgsConstructor
public class LocationSelectionConfiguration {
    private final SingleLocation singleLocation;
    private final MostAbundant mostAbundant;
    @Value("${locationSelection.strategy}")
    private String strategy;

    @Bean
    @Primary
    public Strategy strategy() {
        if ("default".equals(strategy)) {
            return mostAbundant;
        } else {
            return singleLocation;
        }
    }
}
