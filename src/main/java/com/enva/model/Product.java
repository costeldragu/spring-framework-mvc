package com.enva.model;

public class Product {
    private Integer id;
    private String category;
    private String name;
    private String description;
    private Double price;

    public Product() {

    }

    private Product(Builder builder) {
        id = builder.id;
        category = builder.category;
        name = builder.name;
        description = builder.description;
        price = builder.price;
    }


    public Integer getId() {
        return id;
    }

    public Product setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Product setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Product setPrice(Double price) {
        this.price = price;
        return this;
    }

    public static class Builder {
        private Integer id;
        private String category;
        private String name;
        private String description;
        private Double price;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(Double price) {
            this.price = price;
            return this;
        }


        public Product build() {
            return new Product(this);
        }
    }

}
