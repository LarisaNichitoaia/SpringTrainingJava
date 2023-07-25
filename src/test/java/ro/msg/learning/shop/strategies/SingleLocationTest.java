package ro.msg.learning.shop.strategies;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class SingleLocationTest {
    private static final ProductService productservice = Mockito.mock(ProductService.class);
    private static final LocationService locationService = Mockito.mock(LocationService.class);
    private static final StockService stockService = Mockito.mock(StockService.class);
    private static Location location1;
    private static Location location2;
    private static Location location3;
    private static Product product1;
    private static Product product2;
    @InjectMocks
    private SingleLocation singleLocation;

    @BeforeAll
    public static void initUseCase() {
        List<Location> locations = new ArrayList<>();
        location1 = new Location("n1", "country1", "city1", "county1", "street1");
        location2 = new Location("n2", "country2", "city2", "county2", "street2");
        location3 = new Location("n3", "country3", "city3", "county3", "street3");
        location1.setId(UUID.randomUUID());
        location2.setId(UUID.randomUUID());
        location3.setId(UUID.randomUUID());
        locations.add(location1);
        locations.add(location2);
        locations.add(location3);

        when(locationService.getAllLocations()).thenReturn(locations);
        when(locationService.getLocationById(location1.getId())).thenReturn(location1);
        when(locationService.getLocationById(location2.getId())).thenReturn(location2);
        when(locationService.getLocationById(location3.getId())).thenReturn(location3);

        ProductCategory productCategory1 = new ProductCategory("fruct", "dulce");

        product1 = new Product("p1", "d1", new BigDecimal(2), 1.0, productCategory1, "s1", "i1");
        product2 = new Product("p2", "d2", new BigDecimal(2), 1.0, productCategory1, "s2", "i2");
        product1.setId(UUID.randomUUID());
        product2.setId(UUID.randomUUID());

        when(productservice.getProductById(product1.getId())).thenReturn(product1);
        when(productservice.getProductById(product2.getId())).thenReturn(product2);
    }

    @Test
    void testFindLocationAndUpdateStock_ProductsTakenFromSingleLocation() {

        Stock stock1 = new Stock(product1, location1, 10);
        Stock stock2 = new Stock(product2, location1, 3);
        Stock stock3 = new Stock(product1, location2, 11);
        Stock stock4 = new Stock(product2, location2, 11);

        when(stockService.getStockById(product1.getId(), location1.getId())).thenReturn(stock1);
        when(stockService.getStockById(product2.getId(), location1.getId())).thenReturn(stock2);
        when(stockService.getStockById(product1.getId(), location2.getId())).thenReturn(stock3);
        when(stockService.getStockById(product2.getId(), location2.getId())).thenReturn(stock4);

        List<StockDto> productsToBuy = new ArrayList<>();
        StockDto stockDto1 = new StockDto(product1.getId().toString(), null, 3);
        StockDto stockDto2 = new StockDto(product2.getId().toString(), null, 3);
        productsToBuy.add(stockDto1);
        productsToBuy.add(stockDto2);

        List<OrderDetail> orderDetail = singleLocation.findLocationAndUpdateStock(productsToBuy);

        assertEquals(productservice.getProductById(orderDetail.get(0).getProduct().getId()), product1);
        assertEquals(productservice.getProductById(orderDetail.get(1).getProduct().getId()), product2);
        assertEquals(locationService.getLocationById(orderDetail.get(0).getShippedFrom().getId()), location1);
        assertEquals(locationService.getLocationById(orderDetail.get(1).getShippedFrom().getId()), location1);


        assertEquals(7, stock1.getQuantity());
        assertEquals(0, stock2.getQuantity());

        assertEquals(11, stock3.getQuantity());
        assertEquals(11, stock4.getQuantity());
    }

    @Test
    void testFindLocationAndUpdateStock_FirstLocationWithSuficientStock() {

        Stock stock1 = new Stock(product1, location1, 2);
        Stock stock2 = new Stock(product2, location1, 2);
        Stock stock3 = new Stock(product1, location2, 11);
        Stock stock4 = new Stock(product2, location2, 11);
        Stock stock5 = new Stock(product1, location3, 20);
        Stock stock6 = new Stock(product2, location3, 20);

        when(stockService.getStockById(product1.getId(), location1.getId())).thenReturn(stock1);
        when(stockService.getStockById(product2.getId(), location1.getId())).thenReturn(stock2);
        when(stockService.getStockById(product1.getId(), location2.getId())).thenReturn(stock3);
        when(stockService.getStockById(product2.getId(), location2.getId())).thenReturn(stock4);

        List<StockDto> productsToBuy = new ArrayList<>();
        StockDto stockDto1 = new StockDto(product1.getId().toString(), null, 3);
        StockDto stockDto2 = new StockDto(product2.getId().toString(), null, 3);
        productsToBuy.add(stockDto1);
        productsToBuy.add(stockDto2);

        List<OrderDetail> orderDetail = singleLocation.findLocationAndUpdateStock(productsToBuy);

        assertEquals(productservice.getProductById(orderDetail.get(0).getProduct().getId()), product1);
        assertEquals(productservice.getProductById(orderDetail.get(1).getProduct().getId()), product2);
        assertEquals(locationService.getLocationById(orderDetail.get(0).getShippedFrom().getId()), location2);
        assertEquals(locationService.getLocationById(orderDetail.get(1).getShippedFrom().getId()), location2);

        assertEquals(2, stock1.getQuantity());
        assertEquals(2, stock2.getQuantity());

        assertEquals(8, stock3.getQuantity());
        assertEquals(8, stock4.getQuantity());

        assertEquals(20, stock5.getQuantity());
        assertEquals(20, stock6.getQuantity());
    }

    @Test
    void testFindLocationAndUpdateStock_NoLocationWithSuficientStock() {

        Stock stock1 = new Stock(product1, location1, 2);
        Stock stock2 = new Stock(product2, location1, 2);
        Stock stock3 = new Stock(product1, location2, 1);
        Stock stock4 = new Stock(product2, location2, 1);

        when(stockService.getStockById(product1.getId(), location1.getId())).thenReturn(stock1);
        when(stockService.getStockById(product2.getId(), location1.getId())).thenReturn(stock2);
        when(stockService.getStockById(product1.getId(), location2.getId())).thenReturn(stock3);
        when(stockService.getStockById(product2.getId(), location2.getId())).thenReturn(stock4);

        List<StockDto> productsToBuy = new ArrayList<>();
        StockDto stockDto1 = new StockDto(product1.getId().toString(), null, 3);
        StockDto stockDto2 = new StockDto(product2.getId().toString(), null, 3);
        productsToBuy.add(stockDto1);
        productsToBuy.add(stockDto2);

        try {
            singleLocation.findLocationAndUpdateStock(productsToBuy);
        } catch (Exception e) {
            assertEquals("Products not available at any location.", e.getMessage());
        }

    }

    @Test
    void testFindLocationAndUpdateStock_RequiredQuantityNotPositiveNumber() {

        Stock stock1 = new Stock(product1, location1, 2);
        Stock stock2 = new Stock(product2, location1, 2);
        Stock stock3 = new Stock(product1, location2, 1);
        Stock stock4 = new Stock(product2, location2, 1);

        when(stockService.getStockById(product1.getId(), location1.getId())).thenReturn(stock1);
        when(stockService.getStockById(product2.getId(), location1.getId())).thenReturn(stock2);
        when(stockService.getStockById(product1.getId(), location2.getId())).thenReturn(stock3);
        when(stockService.getStockById(product2.getId(), location2.getId())).thenReturn(stock4);

        List<StockDto> productsToBuy = new ArrayList<>();
        StockDto stockDto1 = new StockDto(product1.getId().toString(), null, -33);
        productsToBuy.add(stockDto1);

        try {
            singleLocation.findLocationAndUpdateStock(productsToBuy);
        } catch (Exception e) {
            assertEquals("Quantity must be a positive number", e.getMessage());
        }
    }
}
