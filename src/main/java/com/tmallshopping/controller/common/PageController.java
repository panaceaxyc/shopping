package com.tmallshopping.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/17 11:47
 */
@Controller
public class PageController {
    @RequestMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    @RequestMapping("/registerPage")
    public String registerPage() {
        return "registerPage";
    }

    @RequestMapping("/registerSuccessPage")
    public String registerSuccessPage(){
        return "/registerSuccessPage";
    }

    @RequestMapping("/payPage")
    public String payPage() {
        return "alipay";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin/loginPage";
    }
}
