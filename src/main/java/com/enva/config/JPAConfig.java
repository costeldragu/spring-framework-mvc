package com.enva.config;

import com.enva.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URISyntaxException;

@Configuration
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
public class JPAConfig {


    private final Environment env;

    @Autowired
    public JPAConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("driverClassName"));
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setUsername(env.getProperty("username"));
        dataSource.setPassword(env.getProperty("password"));

        return dataSource;

    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) throws IOException, URISyntaxException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.execute(FileUtils.getFileContextFromResource(this.getClass(), "db/sql/create-db.sql"));
        jdbcTemplate.execute(FileUtils.getFileContextFromResource(this.getClass(), "db/sql/insert-data.sql"));

        return jdbcTemplate;
    }
}
