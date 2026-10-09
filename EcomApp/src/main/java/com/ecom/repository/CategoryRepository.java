package com.ecom.repository;

import com.ecom.mapper.CategoryMapper;
import com.ecom.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository {
    private final JdbcTemplate jdbcTemplate;
    private final CategoryMapper categoryMapper;

    public CategoryRepository(JdbcTemplate jdbcTemplate, CategoryMapper categoryMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.categoryMapper = categoryMapper;
    }

    public Category getCategoryById(int categoryId) {
        String sql = "select * from category where id = ?";
        return jdbcTemplate.query(sql,categoryMapper, categoryId).getFirst();
    }
}
