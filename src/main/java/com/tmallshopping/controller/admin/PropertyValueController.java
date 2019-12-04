package com.tmallshopping.controller.admin;

import com.tmallshopping.entity.Product;
import com.tmallshopping.entity.Property;
import com.tmallshopping.entity.PropertyValue;
import com.tmallshopping.service.ProductService;
import com.tmallshopping.service.PropertyService;
import com.tmallshopping.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/18 9:54
 */
@Controller
@RequestMapping("/admin")
public class PropertyValueController {

    @Autowired
    PropertyValueService propertyValueService;

    @Autowired
    ProductService productService;

    @Autowired
    PropertyService propertyService;

    @RequestMapping("/listPropertyValue")
    public String list(Model model, Integer product_id, Integer category_id) {
        List<PropertyValue> propertyValues = propertyValueService.listByProductId(product_id);
        model.addAttribute("propertyValues", propertyValues);
        Product product = productService.get(product_id);
        model.addAttribute("product", product);
        List<Property> properties = propertyService.list(category_id);
        model.addAttribute("properties", properties);
        return "admin/listPropertyValue";
    }

    @RequestMapping("/addPropertyValue")
    public String add(PropertyValue propertyValue) {
        int product_id = propertyValue.getProduct_id();
        int category_id = productService.get(product_id).getCategory_id();
        propertyValueService.add(propertyValue);
        return "redirect:/admin/listPropertyValue?product_id=" + product_id + "&category_id=" + category_id;
    }


    @RequestMapping("/deletePropertyValue")
    public String delete(Integer id) {
        int product_id = propertyValueService.get(id).getProduct_id();
        int category_id = productService.get(product_id).getCategory_id();
        propertyValueService.delete(id);
        return "redirect:/admin/listPropertyValue?product_id=" + product_id + "&category_id=" + category_id;
    }

    @RequestMapping("/editPropertyValue")
    public String edit(Integer id, Model model) {
        PropertyValue propertyValue = propertyValueService.get(id);
        model.addAttribute("propertyValue", propertyValue);
        Product product = productService.get(propertyValue.getProduct_id());
        model.addAttribute("product", product);
        return "admin/editPropertyValue";
    }

    @RequestMapping("/updatePropertyValue")
    public String update(PropertyValue propertyValue) {
        int product_id = propertyValue.getProduct_id();
        int category_id = productService.get(product_id).getCategory_id();
        propertyValueService.update(propertyValue);
        return "redirect:/admin/listPropertyValue?product_id=" + product_id + "&category_id=" + category_id;
    }
}
