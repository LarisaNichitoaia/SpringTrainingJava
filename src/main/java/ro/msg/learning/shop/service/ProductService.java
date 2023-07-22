package ro.msg.learning.shop.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.repository.ProductRepository;

import java.util.List;
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
        throw new EntityNotFoundException(NO_PRODUCT_IS_FOUND);
    }

    public List<Product> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        if (!allProducts.isEmpty()) {
            return allProducts;
        }
        throw new EntityNotFoundException(NO_PRODUCT_IS_FOUND);
    }

    public Product createProduct(ProductDto productToCreate) {
        ProductCategory category = createOrBringExistingCategory(productToCreate);
        Product product = Product.builder().name(productToCreate.getNameProduct())
                .description(productToCreate.getDescriptionProduct())
                .price(productToCreate.getPriceProduct())
                .weight(productToCreate.getWeightProduct())
                .productCategory(category).supplier(productToCreate.getSupplierProduct())
                .imageUrl(productToCreate.getImageUrlProduct()).build();
        return productRepository.save(product);
    }

    public Product putProduct(UUID productId, ProductDto updatesDto) {
        Product productToUpdate = productRepository.findById(productId).orElse(null);
        if (productToUpdate != null) {
            productToUpdate.setName(updatesDto.getNameProduct());
            productToUpdate.setDescription(updatesDto.getDescriptionProduct());
            productToUpdate.setPrice(updatesDto.getPriceProduct());
            productToUpdate.setWeight(updatesDto.getWeightProduct());
            ProductCategory category = createOrBringExistingCategory(updatesDto);
            productToUpdate.setProductCategory(category);
            productToUpdate.setSupplier(updatesDto.getSupplierProduct());
            productToUpdate.setImageUrl(updatesDto.getImageUrlProduct());
            return productRepository.save(productToUpdate);
        }
        else throw new EntityNotFoundException(NO_PRODUCT_IS_FOUND);
    }

    private ProductCategory createOrBringExistingCategory(ProductDto updatesDto) {
        ProductCategory category = productCategoryService.findByName(updatesDto.getProductCategoryName());
        if(category == null){
            category = productCategoryService.createProductCategory(updatesDto.getProductCategoryName(), updatesDto.getProductCategoryDescription());
        }
        return category;
    }

    public void deleteProductById(UUID productId) {
        Product productToDelete = productRepository.findById(productId).orElse(null);
        if(productToDelete!=null){
            productRepository.deleteById(productId);
        }
        else throw new EntityNotFoundException(NO_PRODUCT_IS_FOUND);
    }

}
