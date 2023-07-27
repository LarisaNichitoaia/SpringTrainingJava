package ro.msg.learning.shop.mapper;

import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductCategoryDto;

public class ProductCategoryMapper {
    public ProductCategory toEntity(ProductCategoryDto productCategoryDto) {
        return ProductCategory.builder().name(productCategoryDto.getName())
                .description(productCategoryDto.getDescription()).build();

    }

    public ProductCategoryDto toDto(ProductCategory productCategory) {
        return ProductCategoryDto.builder().name(productCategory.getName())
                .description(productCategory.getDescription()).build();
    }
}
