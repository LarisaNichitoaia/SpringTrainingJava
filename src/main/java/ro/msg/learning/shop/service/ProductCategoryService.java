package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepo;

    public ProductCategory createProductCategory(String name, String description) {
        ProductCategory productCategory = ProductCategory.builder().name(name).description(description).build();
        return productCategoryRepo.save(productCategory);
    }

    public ProductCategory findByName(String name){
        return productCategoryRepo.findCategoryByName(name);
    }
}
