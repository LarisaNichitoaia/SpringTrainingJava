package ro.msg.learning.shop.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.mapper.StockMapper;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stock")
public class StockController {
    private final ProductService productService;
    private final LocationService locationService;
    private final StockService stockService;
    private final StockMapper stockMapper;

    @GetMapping("/{productId}/{locationId}")
    public ResponseEntity<StockDto> getStockById(@PathVariable UUID productId, @PathVariable UUID locationId) {
        StockDto viewStockDto = stockMapper.toDto(stockService.getStockById(productId, locationId));
        return new ResponseEntity<>(viewStockDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StockDto>> getAllStocks() {
        List<StockDto> stocks = stockService.getAllStocks().stream().map(stockMapper::toDto).toList();
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StockDto> createStock(@RequestBody StockDto stockParams) {
        Product product = productService.getProductById(UUID.fromString(stockParams.getProductId()));
        Location location = locationService.getLocationById(UUID.fromString(stockParams.getLocationId()));
        Stock stock = stockMapper.toEntity(product, location, stockParams);
        StockDto viewStockDto = stockMapper.toDto(stockService.createStock(stock));
        return new ResponseEntity<>(viewStockDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StockDto> putStock(@RequestBody StockDto updatesDto) {
        Product product = productService.getProductById(UUID.fromString(updatesDto.getProductId()));
        Location location = locationService.getLocationById(UUID.fromString(updatesDto.getLocationId()));
        Stock updates = stockMapper.toEntity(product, location, updatesDto);
        StockDto stockToUpdate = stockMapper.toDto(stockService.putStock(product.getId(), location.getId(), updates));
        return new ResponseEntity<>(stockToUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}/{locationId}")
    public ResponseEntity<Void> deleteStock(@PathVariable UUID productId, @PathVariable UUID locationId) {
        stockService.deleteStockById(productId, locationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
