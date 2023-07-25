package ro.msg.learning.shop.dto;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ProductDto {
    private String productId;
    private String productName;
    private String productDescription;
    private String productPrice;
    private String productWeight;
    private String productSupplier;
    private String productImageUrl;
    private String productCategoryId;
    private String productCategoryName;
    private String productCategoryDescription;
}
