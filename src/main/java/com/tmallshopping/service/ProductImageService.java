package com.tmallshopping.service;

import com.tmallshopping.entity.ProductImage;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/18 9:18
 */
public interface ProductImageService {
    /**
     * 添加
     * @param productImage
     */
    void add(ProductImage productImage);

    /**
     * 在删除产品的时候将对应的 5 个 ProductImage 数据也删除了
     * @param product_id
     */
    void deleteByProductId(Integer product_id);

    /**
     * 根据产品ID来获取 当下5个.图片
     * @param product_id
     * @return
     */
    List<ProductImage> list(Integer product_id);


}
