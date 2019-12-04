package com.tmallshopping.service;

import com.tmallshopping.entity.Review;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/16 22:56
 */
public interface ReviewService {
    /**
     * 返回当前产品下评论的数量
     *
     * @param product_id
     * @return
     */
    int getCount(Integer product_id);

    /**
     * 根据product_id来返回当前产品下的所有评论
     *
     * @param product_id
     * @return
     */
    List<Review> listByProductId(Integer product_id);

    /**
     * 增加一条评论
     *
     * @param review
     */
    void add(Review review);
}
