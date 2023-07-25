package ro.msg.learning.shop.mapper;


import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductDto;

import java.math.BigDecimal;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product) {
        return ProductDto.builder().productId(product.getId().toString()).productName(product.getName())
                .productDescription(product.getDescription())
                .productPrice(product.getPrice().toString()).productWeight(product.getWeight().toString())
                .productSupplier(product.getSupplier()).productImageUrl(product.getImageUrl())
                .productCategoryId(product.getProductCategory().getId().toString())
                .productCategoryName(product.getProductCategory().getName())
                .productCategoryDescription(product.getProductCategory().getDescription()).build();
    }

    public Product toEntity(ProductDto productDto, ProductCategory productCategory) {
        return Product.builder().name(productDto.getProductName()).description(productDto.getProductDescription())
                .price(new BigDecimal(productDto.getProductPrice()))
                .weight(Double.parseDouble(productDto.getProductWeight()))
                .productCategory(productCategory)
                .imageUrl(productDto.getProductImageUrl()).supplier(productDto.getProductSupplier()).build();
    }
}
