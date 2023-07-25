package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.controller.customexceptions.NoSuchObjectException;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {
    public static final String NO_PRODUCT_IS_FOUND = "No product was found!";
    private final ProductCategoryService productCategoryService;
    private final ProductRepository productRepository;

    public Product getProductById(UUID productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            return product;
        }
        throw new NoSuchObjectException(NO_PRODUCT_IS_FOUND);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product productToCreate) {
        return productRepository.save(productToCreate);
    }

    public Product putProduct(Product updatesDto) {
        Optional<Product> productToUpdate = productRepository.findById(updatesDto.getId());
        if (productToUpdate.isPresent()) {
            return productRepository.save(updatesDto);
        }
        throw new NoSuchObjectException(NO_PRODUCT_IS_FOUND);
    }

    public ProductCategory createOrBringExistingCategory(ProductCategory productCategory) {
        ProductCategory category = productCategoryService.findByName(productCategory.getName());
        if (category == null) {
            category = productCategoryService.createProductCategory(productCategory.getName(),
                    productCategory.getDescription());
        }
        return category;
    }

    public void deleteProductById(UUID productId) {
        Optional<Product> productToDelete = productRepository.findById(productId);
        if (productToDelete.isPresent()) {
            productRepository.deleteById(productId);
        }
        throw new NoSuchObjectException(NO_PRODUCT_IS_FOUND);
    }

}
