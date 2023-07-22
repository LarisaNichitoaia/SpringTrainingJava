package ro.msg.learning.shop.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDto {
    private String idProduct;
    private String nameProduct;
    private String descriptionProduct;
    private BigDecimal priceProduct;
    private Double weightProduct;
    private String supplierProduct;
    private String imageUrlProduct;
    private String idProductCategory;
    private String productCategoryName;
    private String productCategoryDescription;
}
