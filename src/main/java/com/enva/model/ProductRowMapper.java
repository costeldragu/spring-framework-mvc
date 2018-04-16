package com.enva.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int i) throws SQLException {
        return new Product.Builder().setId(rs.getInt("id")).setName(rs.getString("name")).setCategory(rs.getString("category")).setDescription(rs.getString("description")).setPrice(rs.getDouble("price")).build();
    }
}
