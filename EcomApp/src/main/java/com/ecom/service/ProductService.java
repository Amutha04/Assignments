package com.ecom.service;

import com.ecom.dto.ProductDto;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.mapper.ProductMapper;
import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.model.Vendor;
import com.ecom.repository.CategoryRepository;
import com.ecom.repository.ProductRepository;
import com.ecom.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }


    public ProductDto findById(int id) {
        Optional<Product> optional = productRepository.findById(id);
        if(optional.isEmpty())
            throw new ResourceNotFoundException("Product not found");
        Product product = optional.get();
        return ProductMapper.convertProductToDto(product);
    }

    public void insertProduct(String name, double price, int stockQuantity, int categoryId, int vendorId) {
        // Step 1 : Prepare category and vendor objects
        Category category = categoryRepository.getCategoryById(categoryId);
        Vendor vendor = vendorRepository.getVendorById(vendorId);

        // Step 2 : Add these objects into Product
        Product product = new Product(name, price, stockQuantity, category, vendor);
        productRepository.insertProduct(product);
    }

    public void updateStock(int productId, int newQuantity) {
        Optional<Product> optional = productRepository.findById(productId);
        if(optional.isEmpty())
            throw new ResourceNotFoundException("Product not found");
        productRepository.updateStock(productId, newQuantity);
    }

    public Map<String, Integer> countProductsByVendor() {
        return productRepository.countProductsByVendor();
    }
}
