package com.tmallshopping.controller.admin;

import com.tmallshopping.entity.Category;
import com.tmallshopping.entity.Property;
import com.tmallshopping.service.CategoryService;
import com.tmallshopping.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/18 10:00
 */
@Controller
@RequestMapping("/admin")
public class PropertyController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PropertyService propertyService;

    @RequestMapping("/listProperty")
    public String list(Model model, Integer category_id) {
        List<Property> properties = propertyService.list(category_id);
        model.addAttribute("properties", properties);
        Category category = categoryService.get(category_id);
        model.addAttribute("category", category);
        return "admin/listProperty";
    }

    @RequestMapping("/addProperty")
    public String add(Property property) {
        propertyService.add(property);
        return "redirect:/admin/listProperty?category_id=" + property.getCategory_id();
    }

    @RequestMapping("/deleteProperty")
    public String delete(Integer id) {
        int category_id = propertyService.get(id).getCategory_id();
        propertyService.delete(id);
        return "redirect:/admin/listProperty?category_id=" + category_id;
    }

    @RequestMapping("/editProperty")
    public String edit(Integer id, Model model) {
        Property property = propertyService.get(id);
        model.addAttribute("property", property);
        Category category = categoryService.get(property.getId());
        model.addAttribute("category", category);
        return "admin/editProperty";
    }

    @RequestMapping("/updateProperty")
    public String updateProperty(Property property){
        propertyService.update(property);
        return "redirect:/admin/listProperty?category_id=" + property.getCategory_id();
    }
}
