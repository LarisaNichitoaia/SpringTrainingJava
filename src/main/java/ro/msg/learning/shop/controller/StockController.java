package ro.msg.learning.shop.controller;

import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.msg.learning.shop.dto.CreateStockDto;
import ro.msg.learning.shop.dto.ViewStockDto;
import ro.msg.learning.shop.mapper.StockMapper;
import ro.msg.learning.shop.service.StockService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/Stock")
public class StockController {
    public static final String STOCK_NOT_FOUND = "Stock Not Found";
    private final StockService stockService;
    private final StockMapper stockMapper;

    @GetMapping("/{productId}/{locationId}")
    public ResponseEntity<ViewStockDto> getStockById(@PathVariable UUID productId, @PathVariable UUID locationId) {
        try {
            ViewStockDto viewStockDto = stockMapper.toViewDto(stockService.getStockById(productId, locationId));
            return new ResponseEntity<>(viewStockDto, HttpStatus.FOUND);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, STOCK_NOT_FOUND, exception);
        }

    }

    @GetMapping
    public ResponseEntity<List<ViewStockDto>> getAllStocks() {
        try {
            List<ViewStockDto> stocks = stockService.getAllStocks().stream().map(stockMapper::toViewDto).toList();
            return new ResponseEntity<>(stocks, HttpStatus.FOUND);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, STOCK_NOT_FOUND, exception);
        }
    }

    @PostMapping
    public ResponseEntity<ViewStockDto> createStock(@RequestBody @NonNull CreateStockDto stockParams) {
        ViewStockDto viewStockDto = stockMapper.toViewDto(stockService.createStock(stockParams));
        return new ResponseEntity<>(viewStockDto, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}/{locationId}")
    public ResponseEntity<ViewStockDto> putStock(@PathVariable UUID productId, @PathVariable UUID locationId,
                                                 @RequestBody @NonNull CreateStockDto updates) {
        try {
            ViewStockDto stockToUpdate = stockMapper.toViewDto(stockService.putStock(productId, locationId, updates));
            return new ResponseEntity<>(stockToUpdate, HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, STOCK_NOT_FOUND, exception);
        }
    }

    @DeleteMapping("/{productId}/{locationId}")
    public ResponseEntity<Void> deleteStock(@PathVariable UUID productId, @PathVariable UUID locationId) {
        try {
            stockService.deleteStockById(productId, locationId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, STOCK_NOT_FOUND, exception);
        }
    }

}
