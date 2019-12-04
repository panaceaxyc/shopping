package com.tmallshopping.service.impl;

import com.tmallshopping.entity.*;
import com.tmallshopping.mapper.ProductImageMapper;
import com.tmallshopping.mapper.ProductMapper;
import com.tmallshopping.service.ProductImageService;
import com.tmallshopping.service.ProductService;
import com.tmallshopping.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/16 22:50
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    ReviewService reviewService;

    @Autowired
    ProductImageService productImageService;

    @Autowired
    ProductImageMapper productImageMapper;
     @Override
    public void fill(List<Category> categories) {
        for (Category category : categories) {
            fill(category);
        }
    }
    public void fill(Category category) {
        List<Product> products = list(category.getId());
        category.setProducts(products);
    }

    @Override
    public void fillByRow(List<Category> categories) {
        int productNumberOfEachRow = 8;
        for (Category category : categories) {
            List<Product> products = category.getProducts();
            List<List<Product>> productByRow = new ArrayList<>();
            for (int i = 0; i < products.size(); i += productNumberOfEachRow) {
                int size = i + productNumberOfEachRow;
                size = size > products.size() ? products.size() : size;
                List<Product> productsOfEachRow = products.subList(i, size);
                productByRow.add(productsOfEachRow);
            }
            category.setProductByRow(productByRow);
        }
    }

    @Override
    public void setReviewCount(Product product) {
        int reviewCount = reviewService.getCount(product.getId());
        product.setReviewCount(reviewCount);
    }

    @Override
    public List<Product> search(String keyword) {
        ProductExample example = new ProductExample();
        example.or().andNameLike("%"+keyword+"%");
        example.setOrderByClause("id desc");
        return productMapper.selectByExample(example);
    }

    @Override
    public Product get(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(Integer id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKey(product);
    }

    @Override
    public List<Product> selectByExampleLimit(ProductExample digGoodsExample) {
        return productMapper.selectByExample(digGoodsExample);
    }

    @Override
    public List<ProductImage> findImagePath(Integer product_id) {
        ProductImageExample example = new ProductImageExample();
        example.or().andProduct_idEqualTo(product_id);
        return productImageMapper.selectByExample(example);
    }

    public List<Product> list(Integer category_id) {
        ProductExample example = new ProductExample();
        example.or().andCategory_idEqualTo(category_id);
        List<Product> products = productMapper.selectByExample(example);
        /*List<Product> productAndImage = new ArrayList<>();
        for(Product product :products) {
            ProductImageExample example1 = new ProductImageExample();
            example1.or().andProduct_idEqualTo(product.getId());
            List<ProductImage> images = productImageMapper.selectByExample(example1);
            product.setImages(images);
            productAndImage.add(product);
        }*/
        return products;
    }
}
