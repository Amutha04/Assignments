package com.ecom.repository;


import com.ecom.mapper.ProductCountByVendorMapper;
import com.ecom.mapper.ProductMapper;
import com.ecom.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ProductMapper productMapper;
    private final ProductCountByVendorMapper productCountByVendorMapper;

    public ProductRepository(JdbcTemplate jdbcTemplate, ProductMapper productMapper, ProductCountByVendorMapper productCountByVendorMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.productMapper = productMapper;
        this.productCountByVendorMapper = productCountByVendorMapper;
    }

    public void insertProduct(Product product) {
        String sql = "insert into product (name, price, stockQuantity, category_id, vendor_id) values (?,?,?,?,?)";
        Object[] values = new Object[]{ product.getName(), product.getPrice(), product.getStockQuantity(), product.getCategory().getId(), product.getVendor().getId()};
        jdbcTemplate.update(sql, values);
    }

    public Optional<Product> findById(int id) {
        String sql = """
select p.id, p.name, p.price, p.stockQuantity,c.id as category_id, c.name as category_name,v.id as vendor_id, v.name as vendor_name
from product p
JOIN category c ON p.category_id = c.id
JOIN vendor v ON p.vendor_id = v.id
where p.id = ?""";
         List<Product> list = jdbcTemplate.query(sql, productMapper, id);
         return list
                 .stream().findFirst();
    }

    public void updateStock(int productId, int newQuantity) {
        String sql = "update product set stockQuantity = ? where id = ?";
        Object[] values = new Object[]{newQuantity, productId};
        jdbcTemplate.update(sql, values);
    }

    public Map<String, Integer> countProductsByVendor() {
        String sql = "select v.name as vendor_name, count(p.id) as product_count from product p join vendor v on p.vendor_id = v.id group by v.name";
        return jdbcTemplate.query(sql, productCountByVendorMapper).getFirst();
    }
}
