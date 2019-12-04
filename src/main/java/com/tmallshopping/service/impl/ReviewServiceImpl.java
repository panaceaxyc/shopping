package com.tmallshopping.service.impl;

import com.tmallshopping.entity.Review;
import com.tmallshopping.entity.ReviewExample;
import com.tmallshopping.entity.User;
import com.tmallshopping.mapper.ReviewMapper;
import com.tmallshopping.service.ReviewService;
import com.tmallshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/16 22:56
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    UserService userService;

    @Override
    public int getCount(Integer product_id) {
        return listByProductId(product_id).size();
    }

    @Override
    public List<Review> listByProductId(Integer product_id) {
        ReviewExample example = new ReviewExample();
        example.or().andProduct_idEqualTo(product_id);
        example.setOrderByClause("id desc");
        List<Review> reviews = reviewMapper.selectByExample(example);
        setUser(reviews);
        return reviews;
    }

    @Override
    public void add(Review review) {
        reviewMapper.insert(review);
    }

    public void setUser(List<Review> reviews){
        for(Review review :reviews){
            setUser(review);
        }
    }

    private void setUser(Review review){
        int user_id = review.getUser_id();
        User user = userService.get(user_id);
        review.setUser(user);
    }
}
