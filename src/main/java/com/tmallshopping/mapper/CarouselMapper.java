package com.tmallshopping.mapper;

import com.tmallshopping.entity.Carousel;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/9/21 17:53
 */
public interface CarouselMapper {

    List<Carousel> selAllCarousel();

    int updateCarousel(Carousel carousel);


}
