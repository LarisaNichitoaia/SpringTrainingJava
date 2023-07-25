package ro.msg.learning.shop.controller;

import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.mapper.ProductMapper;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID productId) {
        ProductDto product = productMapper.toDto(productService.getProductById(productId));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts().stream().map(productMapper::toDto).toList();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody @NonNull ProductDto productParams) {
        ProductCategory category = productService.createOrBringExistingCategory(ProductCategory.builder()
                .name(productParams.getProductCategoryName()).description(productParams.getProductCategoryDescription())
                .build());
        Product product = productMapper.toEntity(productParams, category);
        ProductDto productDto = productMapper.toDto(productService.createProduct(product));
        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> putProduct(@RequestBody @NonNull ProductDto updatesDto) {
        ProductCategory category = productService.createOrBringExistingCategory(ProductCategory.builder()
                .name(updatesDto.getProductCategoryName()).description(updatesDto.getProductCategoryDescription())
                .build());
        Product updates = productMapper.toEntity(updatesDto, category);
        updates.setId(UUID.fromString(updatesDto.getProductId()));
        ProductDto productDto = productMapper.toDto(productService.putProduct(updates));
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID productId) {
        productService.deleteProductById(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
