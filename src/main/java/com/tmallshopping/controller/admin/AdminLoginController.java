package com.tmallshopping.controller.admin;

import com.tmallshopping.entity.Admin;
import com.tmallshopping.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/20 10:46
 */
@Controller
@RequestMapping("/admin")
public class AdminLoginController {


    @Autowired
    private AdminService adminService;

    /*@RequestMapping("/")
    public String admin(HttpSession session){
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin==null) {
            return "/admin/loginPage";
        }else{
            return "/admin/listCategory";
        }
    }*/

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session){
        Admin admin = adminService.selByLoginCheck(username,password);
        if(admin==null) {
            model.addAttribute("errorInfo","用户名或者密码错误");
            return "admin/loginPage";
        }else{
            session.setAttribute("admin",admin);
            return "redirect:/admin/listCategory";
        }
    }
}
