package com.enva.repository.impl;

import com.enva.model.Product;
import com.enva.model.ProductRowMapper;
import com.enva.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product saveProduct(Product product) {

        String sql = "INSERT INTO product_table(`category`,`name`,`description`,`price`) VALUES(?,?,?,?)";
        PreparedStatementCreator psc = connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getCategory());
            ps.setString(2, product.getName());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getPrice());
            return ps;
        };

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);

        int productID = keyHolder.getKey().intValue();
        return new Product.Builder().setId(productID).setName(product.getName()).setCategory(product.getCategory()).setDescription(product.getDescription()).setPrice(product.getPrice()).build();

    }

    @Override
    public Product updateProduct(Product product) {

        String sql = "UPDATE product_table SET `category`=?,`name`=?,`description`=?,`price`=? WHERE id = ?";
        PreparedStatementCreator psc = connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getCategory());
            ps.setString(2, product.getName());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getPrice());
            ps.setInt(5, product.getId());
            return ps;
        };
        
        jdbcTemplate.update(psc);
        return product;
    }


    @Override
    public List<Product> saveProducts(List<Product> products) {
        List<Product> savedProducts = new ArrayList<>();
        products.forEach(product -> savedProducts.add(saveProduct(product)));
        return savedProducts;
    }

    @Override
    public Product getProduct(Long productId) {
        String sql = "SELECT * FROM product_table WHERE id = ? ";
        return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), productId);
    }

    @Override
    public boolean deleteProduct(Product product) {
        String sql = "DELETE FROM product_table WHERE id = ? ";
        jdbcTemplate.update(sql, product.getId());
        return true;
    }

    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM product_table ";
        RowMapper<Product> rowMapper = (rs, numRow) -> new Product.Builder().setId(rs.getInt("id")).setName(rs.getString("name")).setCategory(rs.getString("category")).setDescription(rs.getString("description")).setPrice(rs.getDouble("price")).build();
        return jdbcTemplate.query(sql, rowMapper);
    }


}
