package com.tmallshopping.controller.admin;

import com.tmallshopping.entity.Admin;
import com.tmallshopping.entity.Category;
import com.tmallshopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/17 21:20
 */
@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 分类管理 查询所有的分类信息
     * @param model
     * @return
     */
    @RequestMapping("/listCategory")
    public String list(Model model, HttpSession session) {
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin==null) {
            return "redirect:/admin/login";
        }
        List<Category> categories = categoryService.list();
        model.addAttribute("categories",categories);
        return "/admin/listCategory";
    }


    @RequestMapping("/editCategory")
    public String edit(Category category,Model model) {
        model.addAttribute("category",category);
        return "/admin/editCategory";
    }

    @RequestMapping("/updateCategory")
    public String update(Category category) {
        categoryService.update(category);
        return "redirect:/admin/listCategory";
    }
}
