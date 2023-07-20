package ro.msg.learning.shop.controller;

import io.micrometer.common.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.service.ProductCategoryService;


@RestController
@RequestMapping("/ProductCategory")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping
    public ResponseEntity<ProductCategory> createProductCategory(@RequestParam @NonNull String name, @RequestParam @NonNull String description) {
        ProductCategory productCategory = productCategoryService.createProductCategory(name, description);
        return new ResponseEntity<>(productCategory, HttpStatus.CREATED);
    }


}
