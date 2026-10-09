package com.ecom.mapper;

import com.ecom.dto.ProductDto;
import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.model.Vendor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductMapper implements RowMapper<Product> {
    @Nullable
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("category_id"));
        category.setName(rs.getString("category_name"));

        Vendor vendor = new Vendor();
        vendor.setId(rs.getInt("vendor_id"));
        vendor.setName(rs.getString("vendor_name"));

        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getDouble("price"));
        product.setStockQuantity(rs.getInt("stockQuantity"));
        product.setCategory(category);
        product.setVendor(vendor);
        return product;
    }


    public static ProductDto convertProductToDto(Product product) {
        return new ProductDto(
                product.getName(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCategory().getName(),
                product.getVendor().getName()
        );
    }


}
