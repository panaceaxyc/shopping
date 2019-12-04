package com.tmallshopping.controller.common;

import com.tmallshopping.entity.Carousel;
import com.tmallshopping.entity.Category;
import com.tmallshopping.entity.ReferalLink;
import com.tmallshopping.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/18 15:42
 */
@Controller
public class MainController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ReferalLinkService referalLinkService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;


    @Autowired
    private CarouselService carouselService;


    /**
     * 首页访问方法，给首页的JSP页面添加以下数据：
     *
     * @param model
     * @return
     */
    @RequestMapping("/home")
    public String home(Model model) {
        List<Category> categories = categoryService.list();
        productService.fill(categories);
        productService.fillByRow(categories);
        List<ReferalLink> links = referalLinkService.listAll();
        List<Carousel> carousel = carouselService.getAllCarousel();
        model.addAttribute("carousels",carousel);
        model.addAttribute("categories", categories);
        model.addAttribute("links", links);
        return "index";
    }
    /*@RequestMapping("/b")
    public String b() {
        return "b";
    }*/
}
