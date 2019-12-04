package com.tmallshopping.controller.admin;

import com.tmallshopping.entity.Admin;
import com.tmallshopping.entity.Order;
import com.tmallshopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/17 21:54
 */
@Controller
@RequestMapping("/admin")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/listOrder")
    public String list(Model model, HttpSession session) {
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin==null) {
            return "admin/loginPage";
        }
        List<Order> orders = orderService.getAll();
        model.addAttribute("orders", orders);
        return "admin/listOrder";
    }

    @RequestMapping("updateOrder")
    public String update(Order order) {
        orderService.update(order);
        return "redirect:/admin/listOrder";
    }

    @RequestMapping("orderDelivery")
    public String delivery(Integer order_id) {
        Order order = orderService.get(order_id);
        order.setDelivery_date(new Date());
        order.setStatus(OrderService.waitConfirm);
        orderService.update(order);
        return "redirect:/admin/listOrder";
    }


}
