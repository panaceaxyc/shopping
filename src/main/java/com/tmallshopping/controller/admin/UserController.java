package com.tmallshopping.controller.admin;

import com.tmallshopping.entity.User;
import com.tmallshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/17 21:43
 */
@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/listUser")
    public String list(Model model) {
        List<User> users = userService.list();
        model.addAttribute("users", users);
        return "admin/listUser";
    }

    @RequestMapping("/editUser")
    public String edit(Model model, Integer id) {
        User user = userService.get(id);
        model.addAttribute("user",user);
        return "/admin/editUser";
    }

    @RequestMapping("/updateUser")
    public String update(Integer id, String password) {
        userService.updatePassword(id, password);
        return "redirect:/admin/listUser";
    }

}
