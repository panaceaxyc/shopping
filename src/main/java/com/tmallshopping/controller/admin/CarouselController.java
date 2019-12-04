package com.tmallshopping.controller.admin;

import com.tmallshopping.entity.Carousel;
import com.tmallshopping.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/9/21 17:58
 */
@Controller
@RequestMapping("/admin")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;


    @RequestMapping("/carousel")
    public String list(Model model) {
        List<Carousel> carousel = carouselService.getAllCarousel();
        model.addAttribute("cc",carousel);
        return "admin/carousel";
    }


    @RequestMapping("/editCarouseImages")
    public String editCarouseImages(Model model) {
        List<Carousel> carousel = carouselService.getAllCarousel();
        model.addAttribute("cc",carousel);
        return "admin/editcarouselimages";
    }

}
