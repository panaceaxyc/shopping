package com.tmallshopping.service.impl;

import com.tmallshopping.entity.Carousel;
import com.tmallshopping.mapper.CarouselMapper;
import com.tmallshopping.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/9/21 17:52
 */
@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> getAllCarousel() {
        return carouselMapper.selAllCarousel();
    }

    @Override
    public int updateCarousel(Carousel carousel) {
        return carouselMapper.updateCarousel(carousel);
    }
}
