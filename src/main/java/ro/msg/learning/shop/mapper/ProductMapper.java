package ro.msg.learning.shop.mapper;


import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.ProductDto;
@Component
public class ProductMapper {
    public ProductDto toDto(Product product) {
        return ProductDto.builder().idProduct(product.getId().toString()).nameProduct(product.getName()).descriptionProduct(product.getDescription())
                        .priceProduct(product.getPrice()).weightProduct(product.getWeight())
                        .supplierProduct(product.getSupplier()).imageUrlProduct(product.getImageUrl())
                        .idProductCategory(product.getProductCategory().getId().toString())
                        .productCategoryName(product.getProductCategory().getName())
                        .productCategoryDescription(product.getProductCategory().getDescription()).build();
    }
}
