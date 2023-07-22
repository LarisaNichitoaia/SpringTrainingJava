package ro.msg.learning.shop.controller;

import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.mapper.ProductMapper;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Product")
public class ProductController {
    public static final String PRODUCT_NOT_FOUND = "Product Not Found";
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID productId) {
        try {
            ProductDto product = productMapper.toDto(productService.getProductById(productId));
            return new ResponseEntity<>(product, HttpStatus.FOUND);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, PRODUCT_NOT_FOUND, exception);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
            List<ProductDto> products = productService.getAllProducts().stream().map(productMapper::toDto).toList();
            return new ResponseEntity<>(products, HttpStatus.FOUND);

    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody @NonNull ProductDto productParams) {
        ProductDto productDto = productMapper.toDto(productService.createProduct(productParams));
        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> putProduct(@PathVariable UUID productId,
                                                 @RequestBody @NonNull ProductDto updates) {
        try {
            ProductDto productDto = productMapper.toDto(productService.putProduct(productId, updates));
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, PRODUCT_NOT_FOUND, exception);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID productId) {
        try {
            productService.deleteProductById(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, PRODUCT_NOT_FOUND, exception);
        }
    }

}
