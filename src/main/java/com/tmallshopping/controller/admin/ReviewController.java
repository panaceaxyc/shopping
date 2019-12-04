package com.tmallshopping.controller.admin;

import com.tmallshopping.entity.Review;
import com.tmallshopping.entity.User;
import com.tmallshopping.service.ReviewService;
import com.tmallshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/19 11:45
 */
@Controller
@RequestMapping("/admin")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @RequestMapping("/Reviewsave")
    public String save(Review review,
                       @RequestParam("imageCode") String imageCode,
                       @RequestParam("product_id") Integer product_id,
                       @RequestParam("content") String content,
                       HttpSession session,
                       Model model){

        User user = (User) session.getAttribute("user");
        if(user==null) {
            return "loginPage";
        }
        String code = (String) session.getAttribute("certCode");
        if(!code.equals(imageCode)){
            model.addAttribute("msg","验证码错误");
            return "redirect:/showProduct?product_id="+product_id;
        }
        review.setUser_id(user.getId());
        review.setCreateDate(new Date());
        review.setProduct_id(product_id);
        review.setContent(content);
        reviewService.add(review);
        userService.updatePoints(user.getId(),user.getPoints());
        return "redirect:/showProduct?product_id="+product_id;
    }
}
