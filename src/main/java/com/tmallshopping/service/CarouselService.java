package com.tmallshopping.service;

import com.tmallshopping.entity.Carousel;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/9/21 17:52
 */
public interface CarouselService {
    List<Carousel> getAllCarousel();

    int updateCarousel(Carousel carousel);
}
