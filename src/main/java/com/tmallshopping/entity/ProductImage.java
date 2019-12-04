package com.tmallshopping.entity;

public class ProductImage {
    private Integer id;

    private Integer product_id;

    private String path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public ProductImage(Integer id, Integer product_id, String path) {
        this.id = id;
        this.product_id = product_id;
        this.path = path;
    }
}