package com.ecom.repository;

import com.ecom.mapper.CategoryMapper;
import com.ecom.mapper.VendorMapper;
import com.ecom.model.Vendor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VendorRepository {

    private final JdbcTemplate jdbcTemplate;
    private final VendorMapper vendorMapper;

    public VendorRepository(JdbcTemplate jdbcTemplate, VendorMapper vendorMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.vendorMapper = vendorMapper;
    }

    public Vendor getVendorById(int vendorId) {
        String sql = "select * from vendor where id = ?";
        return jdbcTemplate.query(sql,vendorMapper, vendorId).getFirst();

    }
}
