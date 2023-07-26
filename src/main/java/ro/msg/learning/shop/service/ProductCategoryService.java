package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.controller.customexceptions.NoSuchObjectException;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    public static final String NO_PRODUCT_CATEGORY_FOUND = "No product category found!";
    private final ProductCategoryRepository productCategoryRepo;

    public ProductCategory createProductCategory(ProductCategory productCategory) {
        return productCategoryRepo.save(productCategory);
    }

    public ProductCategory findByName(String name) {
        Optional<ProductCategory> productCategory = productCategoryRepo.findByName(name);
        if (productCategory.isPresent()) {
            return productCategory.get();
        }
        throw new NoSuchObjectException(NO_PRODUCT_CATEGORY_FOUND);
    }
}
