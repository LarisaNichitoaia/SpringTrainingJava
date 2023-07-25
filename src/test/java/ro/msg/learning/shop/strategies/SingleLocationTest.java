package ro.msg.learning.shop.strategies;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.StockService;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class SingleLocationTest {

    @Mock
    private final LocationService locationService;
    @Mock
    private final StockService stockService;


    @Test
    void testFindLocationAndUpdateStock_StocksFound() {

    }

}
