package com.enva.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan({"com.enva.web.controllers","com.enva.web.config"})
@PropertySource("classpath:application.properties")
public class WebMvcConfig implements WebMvcConfigurer {

    private final Environment env;

    @Autowired
    public WebMvcConfig(Environment env) {
        this.env = env;
    }

    /**
     * Static resource handler where we store the css or js and images
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(env.getProperty("resources.handler"))
                .addResourceLocations(env.getProperty("resources.location"))
                .setCachePeriod(0);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
